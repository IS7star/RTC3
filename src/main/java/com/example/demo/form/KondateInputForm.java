package com.example.demo.form;

import com.example.demo.entity.ReshipiInput;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 登録画面の入力値を保持するFormクラス
 * @author 松永翔
 * @version 1.0
 */

@Data
public class KondateInputForm {

	@NotBlank(message = "献立名を入力してください。")
	private String menuName;

	private Integer moodCd;
	private Integer emotionCd;

	@NotBlank(message = "画像名を入力して下さい。")
	private String menuImg;

	@NotBlank(message = "材料を入力して下さい。")
	@Size(max = 500, message = "材料は500文字以下で入力して下さい。")
	private String food;

	@NotBlank(message = "料理手順を入力して下さい。")
	@Size(max = 1000, message = "材料は1000文字以下で入力して下さい。")
	private String cookMethod;

	@NotNull(message = "カロリーを入力して下さい。")
	private Integer calorie;

	@NotNull(message = "調理時間を入力して下さい。")
	private Integer cookTime;

	@NotNull(message = "費用を入力して下さい。")
	private Integer cost;

	public ReshipiInput getEntity() {
		ReshipiInput reshipi = new ReshipiInput();
		reshipi.setMenuName(menuName);
		reshipi.setMoodCd(moodCd);
		reshipi.setEmotionCd(emotionCd);
		reshipi.setMenuImg(menuImg);
		reshipi.setFood(food);
		reshipi.setCookMethod(cookMethod);
		reshipi.setCalorie(calorie);
		reshipi.setCookTime(cookTime);
		reshipi.setCost(cost);
		return reshipi;
	}

}
