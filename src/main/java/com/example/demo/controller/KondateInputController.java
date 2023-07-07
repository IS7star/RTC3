package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Reshipi;
import com.example.demo.entity.ReshipiInput;
import com.example.demo.form.KondateInputConfirmForm;
import com.example.demo.form.KondateInputForm;
import com.example.demo.repository.KondateInputRepository;
import com.example.demo.service.KondateInputService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
/**
 * 献立情報を登録するコントローラークラス
 * @author 松永翔
 * @version 1.0
*/

public class KondateInputController {

	private final HttpSession session;
	private final KondateInputRepository kondateInputRepository;
	private final KondateInputService kondateInputService;

	/**
	 * URLから献立登録画面に遷移するためのメソッド
	 * @param EZcs2XdFBSyfsnI5Vf1wWarVgHF0qEsFfQtLD3OdMavFC6PKeH12345
	 * @return  献立登録画面への遷移
	 */

	@GetMapping("/EZcs2XdFBSyfsnI5Vf1wWarVgHF0qEsFfQtLD3OdMavFC6PKeH12345")
	public ModelAndView ryouriInput(ModelAndView mv, KondateInputForm kondateInputForm) {
		//次画面のURL("kondate-input")をセットする
		mv.setViewName("kondate-input");
		//mvを返す
		return mv;
	}

	/**
	 * 献立登録確認画面に遷移するするためのメソッド
	 * @param ryouriInput
	 * @return 献立登録確認画面への遷移
	 */

	@PostMapping("kondateInput")
	public String ryouriInputConfirm(@ModelAttribute @Validated  KondateInputForm kondateInputForm, 
			BindingResult result) {
		
		//kondateInputServiceサービスクラスにて、エラー情報の確認、取得
		kondateInputService.checkMenuName(kondateInputForm, result);
		kondateInputService.checkMenuImg(kondateInputForm, result);
		kondateInputService.checkFood(kondateInputForm, result);
		kondateInputService.checkCookMethod(kondateInputForm, result);
		

		if (!result.hasErrors()) {
			//resultが空でなかった場合
			//kondateInputFormを、"kondateInputForm"キーのセッションに格納
			session.setAttribute("kondateInputForm", kondateInputForm);
			ReshipiInput newReshipi = kondateInputForm.getEntity();

			//気分名、感情名を取得・セッションに格納するサービスクラスのメソッドを実行
			kondateInputService.getEmotionByEmotionCd();
			kondateInputService.getMoodByMoodCd();

			//newReshipiに、セッションから取得した気分名、感情名を格納
			newReshipi.setEmotion((String) session.getAttribute("emotion"));
			newReshipi.setMood((String) session.getAttribute("mood"));

			//newReshipiを、"newReshipi"キーのセッションに格納
			session.setAttribute("newReshipi", newReshipi);

			//次画面("kondate-input-confirm")に遷移する
			return "kondate-input-confirm";
		} else {
			//resultが空である場合
			//次画面("kondate-input")に遷移する
			return "kondate-input";
		}
	}

	/**
	 * 献立登録画面で入力された値をデータベースに保存するするためのメソッド
	 * @param ryouriInputComplete
	 * @return 献立登録完了画面への遷移
	 */

	@Transactional
	@PostMapping("kondateInputComplete")
	public ModelAndView ryouriInputComplete(@ModelAttribute @Validated ModelAndView mv, KondateInputConfirmForm kondateInputConfirmForm,
			BindingResult result) {
		
		//ReshipiInputに格納されている情報をsessionスコープから取得
		ReshipiInput newReshipi = (ReshipiInput) session.getAttribute("newReshipi");

		//t_reshipiテーブルに登録するためのmenuCdの取得
		Integer getMC = kondateInputRepository.getMenuCd();
		newReshipi.setMenuCd(getMC);

		//リポジトリ・DB処理の引数を指定
		kondateInputRepository.saveMenu(
				getMC, newReshipi.getMenuName(), newReshipi.getMoodCd(), newReshipi.getMenuImg(), newReshipi.getFood(),
				newReshipi.getCookMethod(), newReshipi.getCalorie(), newReshipi.getCookTime(), newReshipi.getCost(),
				newReshipi.getEmotionCd());

		//t_reshipiテーブルに登録する値をryouriInputに格納
		Reshipi ryouriInput = new Reshipi();
		ryouriInput.setMenuCd(getMC);
		ryouriInput.setMenuName(newReshipi.getMenuName());
		ryouriInput.setMoodCd(newReshipi.getMoodCd());
		ryouriInput.setMenuImg(newReshipi.getMenuImg());
		ryouriInput.setFood(newReshipi.getFood());
		ryouriInput.setCookMethod(newReshipi.getCookMethod());
		ryouriInput.setCalorie(newReshipi.getCalorie());
		ryouriInput.setCookTime(newReshipi.getCookTime());
		ryouriInput.setCost(newReshipi.getCost());
		ryouriInput.setEmotionCd(newReshipi.getEmotionCd());

		//ryouriInputにセットした値を、インサート（リポジトリ・DBの実行）
		kondateInputRepository.saveAndFlush(ryouriInput);
		
		//不要なセッションの削除
		session.removeAttribute("newReshipi");
		session.removeAttribute("mood");
		session.removeAttribute("emotion");
		
		//次画面のURL("kondate-input-complete")をセットする
		mv.setViewName("kondate-input-complete");
		//mvを返す
		return mv;
	}

}
