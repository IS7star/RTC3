package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.entity.Reshipi;
import com.example.demo.form.KondateUpdateForm;
import com.example.demo.repository.KondateUpdateRepository;

import lombok.AllArgsConstructor;

/**
 * 更新情報の記載を確認するサービスクラス
 * @author 松永翔
 * @version 1.0
 */

@AllArgsConstructor
@Service
public class KondateUpdateService {

	private final KondateUpdateRepository kondateUpdateRepository;

	//"menuCd"が存在するか確認するメソッド
	public Reshipi getByMenuCd(KondateUpdateForm kondateUpdateForm, BindingResult result) {
		Optional<Reshipi> reshipiList = kondateUpdateRepository.findByMenuCd(kondateUpdateForm.getMenuCd());
		if (reshipiList.isEmpty()) {
			result.addError(new FieldError(
					result.getObjectName(),
					"menuCd",
					"存在しないメニュー番号です。"));
			return null;
		}
		return reshipiList.get();
	}

	//"menuName"の記載が正しいか確認するメソッド
	public boolean checkMenuName(KondateUpdateForm kondateUpdateForm, BindingResult result) {
		boolean ret = true;
		String menuName = kondateUpdateForm.getMenuName();
		if (menuName != null && !"".equals(menuName)) {
			if (menuName.strip().length() == 0) {
				result.addError(new FieldError(
						result.getObjectName(),
						"menuName",
						"メニュー名が全角スペースです。"));
				ret = false;
			}
		}
		return ret;
	}

	//"menuImg"の記載が正しいか確認するメソッド
	public boolean checkMenuImg(KondateUpdateForm kondateUpdateForm, BindingResult result) {
		boolean ret = true;
		String menuImg = kondateUpdateForm.getMenuName();
		if (menuImg != null && !"".equals(menuImg)) {
			if (menuImg.strip().length() == 0) {
				result.addError(new FieldError(
						result.getObjectName(),
						"menuImg",
						"画像名が全角スペースです。"));
				ret = false;
			}
		}
		return ret;
	}
}
