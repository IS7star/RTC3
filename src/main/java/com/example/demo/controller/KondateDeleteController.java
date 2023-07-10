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

@AllArgsConstructor
@Controller
public class KondateDeleteController {

	private final HttpSession session;
	private final KondateDeleteService kondateDeleteService;

	@GetMapping("/EZcs2XdFBSyfsnI5Vf1wWarVgHF0qEsFfQtLD3OdMavFC6PKeH123789")
	public ModelAndView kondateDeleteInput(ModelAndView mv, KondateDeleteForm kondateDeleteForm) {
		//次画面のURL("kondate-delete")をセットする
		mv.setViewName("kondate-delete");
		//mvを返す
		return mv;
	}

	@PostMapping("kondateDelete")
	public String kondateDeleteList(@ModelAttribute @Validated KondateDeleteForm kondateDeleteForm,
			BindingResult result) {
		
		kondateDeleteService.checkReshipi(kondateDeleteForm, result);

		if (!result.hasErrors()) {
			//resultが空でなかった場合
			//kondateDeleteFormを、"kondateDeleteForm"キーのセッションに格納
			session.setAttribute("kondateDeleteForm", kondateDeleteForm);

			//献立名から献立一覧表示を実行
			kondateDeleteService.getReshipiByMenuName();

			//次画面("kondate-delete-confirm")に遷移する
			return "kondate-delete-confirm";
		} else {
			//resultが空である場合
			//次画面("kondate-delete")に遷移する
			return "kondate-delete";
		}
	}
	
	@GetMapping("/ShowReshipiDetailForDelete")
	public ModelAndView kondateDeleteComplete(@RequestParam("menuCd") Integer menuCd, ModelAndView mv) {
		//RequestParamのmenuCdを
		KondateDeleteForm kondateDeleteMenuCd = new KondateDeleteForm();
		kondateDeleteMenuCd.setMenuCd(menuCd);
		session.setAttribute("kondateDeleteMenuCd", kondateDeleteMenuCd);
		
		kondateDeleteService.deleteReshipiByMenuCd();
		
		session.removeAttribute("kondateDeleteForm");
		session.removeAttribute("deleteReshipiList");
		mv.setViewName("redirect:/kondateDeleteComplete");
		return mv;
	}
	
	@GetMapping("kondateDeleteComplete")
	public String moveKondateDeleteComplete() {
		return "kondate-delete-complete";
	}
}