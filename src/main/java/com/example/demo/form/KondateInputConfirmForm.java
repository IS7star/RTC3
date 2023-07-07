package com.example.demo.form;

import lombok.Data;

/**
 * 献立登録確認画面から、献立登録完了画面への遷移する際のFormクラス
 * @author 松永翔
 * @version 1.0
 */

@Data
public class KondateInputConfirmForm {

	private Integer menuCd;
	private String menuName;
	private Integer moodCd;
	private Integer emotionCd;
	private String menuImg;
	private String food;
	private String cookMethod;
	private Integer calorie;
	private Integer cookTime;
	private Integer cost;

}
