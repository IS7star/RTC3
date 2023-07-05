package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.ReshipiForm;
import com.example.demo.service.SearchReshipiService;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class EmotionSerchController {

	HttpSession session;
	private final SearchReshipiService searchReshipiService;
	
	@PostMapping("/emotionInput")
	public ModelAndView showForm(@RequestParam("emotion") String emotion,ModelAndView mv) {
		
		
		//気分検索と感情分析でレシピ検索していた場合のため、セッションの削除
		session.removeAttribute("reshipiLiset");
		session.removeAttribute("mood");
		session.removeAttribute("reshipiLisetEmotion");
		
		mv.setViewName("menu");
		ReshipiForm reshipiForm = new ReshipiForm();
		reshipiForm.setEmotion(emotion);
		session.setAttribute("emotion", reshipiForm);
		
		//献立情報を取得・セッションに格納するサービスクラスのメソッドを実行
		searchReshipiService.getEmotioncdByEmotion();
		//気分名を取得・セッションに格納するサービスクラスのメソッドを実行
		searchReshipiService.getReshipiInfoByEmotioncd();
	
		
//		String name = (String)reshipiForm.getEmotion();
//		System.out.println("------------------------");
//		System.out.println(name);
//		System.out.println("------------------------");	
		
		return mv;
	}
}
