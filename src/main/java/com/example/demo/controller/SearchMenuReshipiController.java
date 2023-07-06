package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

@RestController
@AllArgsConstructor
@Controller
public class SearchMenuReshipiController {

	HttpSession session;
	private final SearchReshipiService searchReshipiService;
 
	/**
	 * 取得したmoodCdをもとに対象となる献立一覧を表示するメソッド
	 * 気分選択による献立提案システムで利用
	 * @param moodCd
	 * @param mv
	 * @return menu (献立一覧画面)
	 */
	@GetMapping("/SearchReshipi")
	public ModelAndView showReshipi(@RequestParam("moodCd") Integer moodCd, ModelAndView mv) {

		//事前にレシピ検索していた場合、セッションの削除
		session.removeAttribute("mood");
		session.removeAttribute("menuInfo");
		session.removeAttribute("menuCd");
		session.removeAttribute("reshipiLiset");
		
		//次画面のURLをセットする
		mv.setViewName("menu");
		//ReshipiFormからmoodCd(気分コード)をreshipiFormに格納し、"moodCd"キーのセッションに格納
		ReshipiForm reshipiForm = new ReshipiForm();
		reshipiForm.setMoodCd(moodCd);
		session.setAttribute("moodCd", reshipiForm);
		
		//判定用にmoodTextに値を入れる ※1であれば献立検索時の文章を表示
		mv.addObject("moodText", 1);

		//献立情報を取得・セッションに格納するサービスクラスのメソッドを実行
		searchReshipiService.getMenunameByMoodcd();
		//気分名を取得・セッションに格納するサービスクラスのメソッドを実行
		searchReshipiService.getMoodByMoodcd();
		//mvを返す
		return mv;
	}
	
	/**
	 * emotionCd(感情コード)をもとに対象となる献立一覧を表示するメソッド
	 * 感情分析による献立提案システムで利用
	 * @param emotionCd
	 * @param mv
	 * @return menu (献立一覧画面)
	 */ 
	
	@PostMapping("/emotionInput")
	public ModelAndView showForm(@RequestParam("emotion") String emotion,ModelAndView mv) {
		
		//事前にレシピ検索していた場合、セッションの削除
		session.removeAttribute("mood");
		session.removeAttribute("menuInfo");
		session.removeAttribute("menuCd");
		session.removeAttribute("reshipiLiset");

		//次画面のURLをセットする
		mv.setViewName("menu");
		
		//ReshipiFormからemotion(WebAPIで取得した感情名)をreshipiFormに格納し、"emotion"キーのセッションに格納
		ReshipiForm reshipiForm = new ReshipiForm();
		reshipiForm.setEmotion(emotion);
		session.setAttribute("emotion", reshipiForm);

		//判定用にmoodTextに値を入れる ※nullであれば献立検索時の文章を表示
		mv.addObject("moodText", null);

		//レシピ情報を取得・セッションに格納するサービスクラスのメソッドを実行
		searchReshipiService.getReshipiEmotioncdByEmotion();
		
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


