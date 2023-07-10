package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class KondateDeleteForm {
	
	@NotBlank(message = "献立名を入力してください。")
	private String menuName;
	
	private Integer menuCd;
}