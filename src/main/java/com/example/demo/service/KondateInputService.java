package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.form.KondateInputForm;
import com.example.demo.repository.SearchEmotionRepository;
import com.example.demo.repository.SearchMoodRepository;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

/**
 * 献立情報の記載を確認するサービスクラス
 * @author 松永翔
 * @version 1.0
 */

@AllArgsConstructor
@Service
public class KondateInputService {

	private final HttpSession session;
	private final SearchMoodRepository searchMoodRepository;
	private final SearchEmotionRepository searchEmotionRepository;

	/**
	 * 入力した献立情報が正しいか確認するサービス
	 * @author 松永翔
	 * @version 1.0
	 * 
	 */

	//"menuName"の記載が正しいか確認するメソッド
	public boolean checkMenuName(KondateInputForm kondateInputForm, BindingResult result) {
		boolean ret = true;
		String menuName = kondateInputForm.getMenuName();
		if (menuName != null && !"".equals(menuName)) {
			if (menuName.strip().length() == 0) {
				result.addError(new FieldError(
						result.getObjectName(),
						"menuName",
						"献立名が全角スペースです。"));
				ret = false;
			}
		}
		return ret;
	}

	//"menuImg"の記載が正しいか確認するメソッド
	public boolean checkMenuImg(KondateInputForm kondateInputForm, BindingResult result) {
		boolean ret = true;
		String menuImg = kondateInputForm.getMenuImg();
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

	//"food"の記載が正しいか確認するメソッド
	public boolean checkFood(KondateInputForm kondateInputForm, BindingResult result) {
		boolean ret = true;
		String food = kondateInputForm.getFood();
		if (food != null && !"".equals(food)) {
			if (food.strip().length() == 0) {
				result.addError(new FieldError(
						result.getObjectName(),
						"food",
						"材料が全角スペースです。"));
				ret = false;
			}
		}
		return ret;
	}

	//"cookMethod"の記載が正しいか確認するメソッド
	public boolean checkCookMethod(KondateInputForm kondateInputForm, BindingResult result) {
		boolean ret = true;
		String cookMethod = kondateInputForm.getFood();
		if (cookMethod != null && !"".equals(cookMethod)) {
			if (cookMethod.strip().length() == 0) {
				result.addError(new FieldError(
						result.getObjectName(),
						"cookMethod",
						"作り方が全角スペースです。"));
				ret = false;
			}
		}
		return ret;
	}

	//追加したい献立の入力時、気分コードから気分名を取得するためのメソッドです。
	public void getMoodByMoodCd() {
		KondateInputForm kondateInputForm = (KondateInputForm) session.getAttribute("kondateInputForm");
		String mood = searchMoodRepository.findMoodByMoodcd(kondateInputForm.getMoodCd());
		session.setAttribute("mood", mood);
	}

	//追加したい献立の入力時、感情コードから感情名を取得するためのメソッドです。
	public void getEmotionByEmotionCd() {
		KondateInputForm kondateInputForm = (KondateInputForm) session.getAttribute("kondateInputForm");
		String emotion = searchEmotionRepository.findEmotionByEmotionCd(kondateInputForm.getEmotionCd());
		session.setAttribute("emotion", emotion);
	}
}
