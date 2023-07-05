package com.example.demo.form;

import lombok.Data;

@Data
public class ReshipiForm {

//　献立一覧
	private Integer menuCd;

	private String menuName;

	private Integer moodCd;
		
	private String menuImg;

	private String food;

	private String cookMethod;

	private Integer calorie;

	private Integer cookTime;

	private Integer cost;

	private String mood;
	
//	感情分析
	private Integer emotionCd;
	
	private String emotion;

}


