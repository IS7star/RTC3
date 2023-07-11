package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.KondateDeleteForm;
import com.example.demo.service.KondateDeleteService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

/** 
 * 献立情報を削除するためのコントローラークラス
 * @author 櫻井樹
 * @version 1.0 
 */

@AllArgsConstructor
@Controller
public class KondateDeleteController {

	private final HttpSession session;
	private final KondateDeleteService kondateDeleteService;
	
	/**
	 * 献立削除検索画面へ遷移するためのメソッド
	 * @param mv
	 * @param kondateDeleteForm
	 * @return 献立削除検索画面への遷移
	 */

	@GetMapping("/EZcs2XdFBSyfsnI5Vf1wWarVgHF0qEsFfQtLD3OdMavFC6PKeH123789")
	public ModelAndView kondateDeleteInput(ModelAndView mv, KondateDeleteForm kondateDeleteForm) {
		//次画面のURL("kondate-delete")をセット
		mv.setViewName("kondate-delete");
		//mvを返す
		return mv;
	}
	
	/**
	 * 献立削除検索画面から献立削除一覧・確認画面へ遷移するためのメソッド
	 * @param kondateDeleteForm
	 * @param result
	 * @return 献立削除一覧・確認画面への遷移
	 */

	@PostMapping("kondateDelete")
	public String kondateDeleteList(@ModelAttribute @Validated KondateDeleteForm kondateDeleteForm,
			BindingResult result) {
		
		//献立が存在しないとき、エラーを出すためのサービスクラス・メソッドの呼び出し
		kondateDeleteService.checkReshipi(kondateDeleteForm, result);

		if (!result.hasErrors()) {
			//resultが空でなかった場合
			//kondateDeleteFormを、"kondateDeleteForm"キーのセッションに格納
			session.setAttribute("kondateDeleteForm", kondateDeleteForm);

			//献立名から献立一覧表示を実行
			kondateDeleteService.getReshipiByMenuName();

			//次画面("kondate-delete-confirm")への遷移
			return "kondate-delete-confirm";
		} else {
			//resultが空である場合
			//次画面("kondate-delete")への遷移
			return "kondate-delete";
		}
	}
	
	/**
	 * 献立削除完了画面へリダイレクトさせるためのメソッド
	 * @param menuCd
	 * @param mv
	 * @return 献立削除完了画面へのリダイレクト
	 */
	
	@GetMapping("/ShowReshipiDetailForDelete")
	public ModelAndView kondateDeleteComplete(@RequestParam("menuCd") Integer menuCd, ModelAndView mv) {
		//RequestParamのmenuCdをKondateDeleteFormに当てはめ、セッションにセット
		KondateDeleteForm kondateDeleteMenuCd = new KondateDeleteForm();
		kondateDeleteMenuCd.setMenuCd(menuCd);
		session.setAttribute("kondateDeleteMenuCd", kondateDeleteMenuCd);
		
		//献立をDB上から削除する、サービスクラス・メソッドの呼び出し
		kondateDeleteService.deleteReshipiByMenuCd();
		
		//セッションの削除
		session.removeAttribute("kondateDeleteMenuCd");
		session.removeAttribute("kondateDeleteForm");
		session.removeAttribute("deleteReshipiList");
		mv.setViewName("redirect:/kondateDeleteComplete");
		return mv;
	}
	
	/** 
	 * リダイレクトから献立削除完了画面へ遷移するためのメソッド
	 * @return 献立削除完了画面への遷移
	 */
	
	@GetMapping("kondateDeleteComplete")
	public String moveKondateDeleteComplete() {
		return "kondate-delete-complete";
	}
}