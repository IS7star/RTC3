package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.entity.ReshipiDelete;
import com.example.demo.form.KondateDeleteForm;
import com.example.demo.repository.KondateDeleteRepository;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

/**
 * 献立削除におけるデータベース処理を実行するためのサービスクラス
 * @author 櫻井樹
 * @version 1.0
 */

@AllArgsConstructor
@Service
public class KondateDeleteService {

	private final HttpSession session;
	private final KondateDeleteRepository kondateDeleteRepository;
	
	/**
	 * 入力された献立名がデータベース上に存在しない場合に、エラーを出すためのメソッド
	 * @param kondateDeleteForm
	 * @param result
	 */

	public void checkReshipi(KondateDeleteForm kondateDeleteForm, BindingResult result) {
		//KondateDeleteForm kondateDeleteForm = new KondateDeleteForm();
		List<ReshipiDelete> optionlReshipi = kondateDeleteRepository
				.checkReshipiByMenuName(kondateDeleteForm.getMenuName());
		if (optionlReshipi.isEmpty()) {
			result.addError(new FieldError(
					result.getObjectName(),
					"menuName", "献立情報がございません。"));
		}
	}
	
	/**
	 * 入力された献立名から献立一覧を取得・表示するためのメソッド
	 */

	public void getReshipiByMenuName() {
		//セッション"KondateDeleteForm"をsearchMenuNameに格納
		KondateDeleteForm searchMenuName = (KondateDeleteForm) session.getAttribute("kondateDeleteForm");
		//kondateDeleteFormにあるmenuNameで、Reshipi一覧を検索して格納
		List<ReshipiDelete> deleteReshipiList = kondateDeleteRepository.findByMenuName(searchMenuName.getMenuName());
		//検索されたReshipi一覧を、セッション"deleteReshipiListに格納
		session.setAttribute("deleteReshipiList", deleteReshipiList);
	}
	
	/**
	 * 選択された献立を、データベース上から削除するためのメソッド
	 */

	public void deleteReshipiByMenuCd() {
		//セッション"kondateDeleteMenuCd"を取得し、searchMenuNameに格納
		KondateDeleteForm searchMenuName = (KondateDeleteForm) session.getAttribute("kondateDeleteMenuCd");
		//セッション"kondateDeleteMenuCd"内の、menuCdのキャスト
		Integer menuCd = (Integer) searchMenuName.getMenuCd();
		//Delete文を実行
		kondateDeleteRepository.deleteById(menuCd);
	}
}