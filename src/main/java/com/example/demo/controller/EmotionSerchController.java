package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.ReshipiForm;

@Controller
public class EmotionSerchController {
	
	@PostMapping("/emotionInput")
	public ModelAndView showForm(@RequestParam("emotion") String emotion,  ModelAndView mv) {
		
		ReshipiForm reshipiform = new ReshipiForm();
		reshipiform.setEmotion(emotion);
		String name = (String)reshipiform.getEmotion();
		System.out.println("------------------------");
		System.out.println(name);
		System.out.println("------------------------");
		
		mv.setViewName("menu");
		return mv;
	}
}
