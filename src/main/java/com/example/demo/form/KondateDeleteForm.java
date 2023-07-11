package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 献立情報削除に対応するフォームです。
 * @author sakurai
 * @version 1.0
 */

@Data
public class KondateDeleteForm {
	
	@NotBlank(message = "献立名を入力してください。")
	private String menuName;
	
	private Integer menuCd;
}
