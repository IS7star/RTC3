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
import com.example.demo.form.KondateUpdateForm;
import com.example.demo.repository.KondateInputRepository;
import com.example.demo.repository.KondateUpdateRepository;
import com.example.demo.service.KondateInputService;
import com.example.demo.service.KondateUpdateService;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

/**
 * 献立情報を登録、更新するコントローラークラス
 * @author 松永翔
 * @version 1.0
*/

@AllArgsConstructor
@Controller
public class KondateController {

	private final HttpSession session;
	private final KondateInputRepository kondateInputRepository;
	private final KondateInputService kondateInputService;
	private final KondateUpdateService kondateUpdateService;
	private final KondateUpdateRepository kondateUpdateRepository;

	/**
	 * URLから管理者メニュー画面に遷移するためのメソッド
	 * @param EZcs2XdFBSyfsnI5Vf1wWarVgHF0qEsFfQtLD3OdMavFC6PKeH12345
	 * @return  メニュー画面への遷移
	 */

	@GetMapping("/EZcs2XdFBSyfsnI5Vf1wWarVgHF0qEsFfQtLD3OdMavFC6PKeH12345")
	public String ryouriInput(KondateInputForm kondateInputForm) {
		//次画面("kondate-administrator")に遷移する
		return "kondate-administrator";

	}

	@GetMapping("/register")
	public String kondateRegister(KondateInputForm kondateInputForm) {
		//次画面("kondate-register")に遷移する
		return "kondate-register";
	}

	@GetMapping("/update")
	public String kondateUpdateshitai(KondateUpdateForm kondateUpdateForm) {
		//次画面（"kondate-update-input"）に遷移する
		return "kondate-update";
	}

	@GetMapping("/admin-menu")
	public String kondateAdministrator(KondateUpdateForm kondateUpdateForm) {
		//次画面（"kondate-administrator"）に遷移する
		return "kondate-administrator";
	}

	/**
	 * 献立登録確認画面に遷移するするためのメソッド
	 * @param kondateInput
	 * @return 献立登録確認画面への遷移
	 */

	@PostMapping("kondateInput")
	public String ryouriInputConfirm(@ModelAttribute @Validated KondateInputForm kondateInputForm,
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

			//次画面("kondate-register-confirm")に遷移する
			return "kondate-register-confirm";
		} else {
			//resultが空である場合
			//次画面("kondate-register")に遷移する
			return "kondate-register";
		}
	}

	/**
	 * 献立登録画面で入力された値をデータベースに保存するするためのメソッド
	 * @param kondateInputComplete
	 * @return 献立登録完了画面への遷移
	 */

	@Transactional
	@PostMapping("kondateInputComplete")
	public ModelAndView ryouriInputComplete(@ModelAttribute @Validated ModelAndView mv,
			KondateInputConfirmForm kondateInputConfirmForm,
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

		//次画面のURL("kondate-register-complete")をセットする
		mv.setViewName("kondate-register-complete");
		//mvを返す
		return mv;
	}


	/**
	 * 更新画面で入力された値をデータベースで更新するするためのメソッド
	 * @param KondateUpdate
	 * @return 更新完了画面への遷移
	 */

	@PostMapping("KondateUpdate")
	public String kondateUpdate(@ModelAttribute @Validated KondateUpdateForm kondateUpdateForm,
			BindingResult result) {

		//KondateUpdateServiceクラスでエラーを確認
		Reshipi originalReshipi = kondateUpdateService.getByMenuCd(kondateUpdateForm, result);
		kondateUpdateService.checkMenuName(kondateUpdateForm, result);
		kondateUpdateService.checkMenuImg(kondateUpdateForm, result);

		if (!result.hasErrors()) {
			//resultが空でなかった場合
			//newReshipiにKondateUpdateFormのEntityを格納
			Reshipi newReshipi = kondateUpdateForm.getEntity();

			//newReshipiにoriginalReshipiの値を格納
			newReshipi.setMoodCd(originalReshipi.getMoodCd());
			newReshipi.setEmotionCd(originalReshipi.getEmotionCd());
			newReshipi.setFood(originalReshipi.getFood());
			newReshipi.setCookMethod(originalReshipi.getCookMethod());
			newReshipi.setCalorie(originalReshipi.getCalorie());
			newReshipi.setCookTime(originalReshipi.getCookTime());
			newReshipi.setCost(originalReshipi.getCost());

			//UPDATE文を実行
			kondateUpdateRepository.saveAndFlush(newReshipi);

			//次画面("kondate-update-complete")に遷移する
			return "kondate-update-complete";
		} else {
			//resultが空である場合
			//次画面("kondate-update-input")に遷移する
			return "kondate-update";
		}

	}

}
