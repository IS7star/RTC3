package com.example.demo.form;

import com.example.demo.entity.Reshipi;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 更新画面の入力値を保持するFormクラス
 * @author 松永翔
 * @version 1.0
 */

@Data
public class KondateUpdateForm {
	
	@NotNull(message = "メニュー番号を入力して下さい。")
	private Integer menuCd;
	
	@NotBlank(message = "メニュー名を入力して下さい。")
	private String menuName;
	
	@NotBlank(message = "画像名を入力して下さい。")
	private String menuImg;
	
	public Reshipi getEntity() {
		Reshipi reshipiUpdate = new Reshipi();
		reshipiUpdate.setMenuCd(menuCd);
		reshipiUpdate.setMenuName(menuName);
		reshipiUpdate.setMenuImg(menuImg);
		return reshipiUpdate;
		
	}

}
