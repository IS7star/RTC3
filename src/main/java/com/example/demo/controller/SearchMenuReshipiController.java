package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.ReshipiForm;
import com.example.demo.service.SearchReshipiService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


/**
 * 献立提案システムの画面遷移時の処理用コントローラー
 * @author 中嶋教陽  櫻井樹 hirono
 * @version 1.1
 */

@AllArgsConstructor
@Controller
public class SearchMenuReshipiController {

	HttpSession session;
	private final SearchReshipiService searchReshipiService;
 
	/**
	 * 取得したmoodCdをもとに対象となる献立一覧を表示するメソッド
	 * @param moodCd
	 * @param mv
	 * @return menu (献立一覧画面)
	 */
	@GetMapping("/SearchReshipi")
	public ModelAndView showReshipi(@RequestParam("moodCd") Integer moodCd, ModelAndView mv) {

		//次画面のURLをセットする
		mv.setViewName("menu");
		//ReshipiFormからmoodCd(気分コード)をreshipiFormに格納し、"moodCd"キーのセッションに格納
		ReshipiForm reshipiForm = new ReshipiForm();
		reshipiForm.setMoodCd(moodCd);
		session.setAttribute("moodCd", reshipiForm);
		
		//献立情報を取得・セッションに格納するサービスクラスのメソッドを実行
		searchReshipiService.getMenunameByMoodcd();
		//気分名を取得・セッションに格納するサービスクラスのメソッドを実行
		searchReshipiService.getMoodByMoodcd();
		//mvを返す
		return mv;
	}
	
	/**
	 * 取得したmenuCdをもとに対象となる献立情報詳細を表示するメソッド
	 * @param menuCd
	 * @param mv
	 * @return reshipi (献立情報詳細画面)
	 */
	
	@GetMapping("/ShowReshipiDetail")
	public ModelAndView showReshipiDetail(@RequestParam("menuCd") Integer menuCd,ModelAndView mv) {

		//次画面のURLをセットする
		mv.setViewName("reshipi");
		//ReshipiFormからmenuCd(献立コード)をreshipiFormに格納し、"menuCd"キーのセッションに格納
		ReshipiForm reshipiForm = new ReshipiForm();
		reshipiForm.setMenuCd(menuCd);
		session.setAttribute("menuCd", reshipiForm);
		
		//献立情報を取得・セッションに格納するサービスクラスのメソッドを実行
		searchReshipiService.getReshipiInfoByMenucd();

		//mvを返す
		return mv;
	}
}

