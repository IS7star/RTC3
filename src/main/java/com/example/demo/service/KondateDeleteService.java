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

@AllArgsConstructor
@Service
public class KondateDeleteService {
	
	private final HttpSession session;
	private final KondateDeleteRepository kondateDeleteRepository;
	
	
	public void checkReshipi(KondateDeleteForm kondateDeleteForm, BindingResult result) {
		//KondateDeleteForm kondateDeleteForm = new KondateDeleteForm();
		List<ReshipiDelete> optionlReshipi =
				kondateDeleteRepository.checkReshipiByMenuName(kondateDeleteForm.getMenuName());
			if(optionlReshipi.isEmpty()) {
				result.addError(new FieldError(
						result.getObjectName(),
						"menuName","献立情報がございません。"));
			}
		}

	public void getReshipiByMenuName() {
		//セッション"KondateDeleteForm"をsearchMenuNameに格納
		KondateDeleteForm searchMenuName = (KondateDeleteForm) session.getAttribute("kondateDeleteForm");
		//kondateDeleteFormにあるmenuNameで、Reshipi一覧を検索して格納
		List<ReshipiDelete> deleteReshipiList = kondateDeleteRepository.findByMenuName(searchMenuName.getMenuName());
		//検索されたReshipi一覧を、セッション"deleteReshipiListに格納
		session.setAttribute("deleteReshipiList", deleteReshipiList);
	}
	
	
	public void deleteReshipiByMenuCd() {
		//セッション"deleteReshipiList"をにdeleteReshipiに格納
		//ReshipiForm deleteReshipi = (ReshipiForm) session.getAttribute("deleteReshipiList");
		
		KondateDeleteForm searchMenuName = (KondateDeleteForm) session.getAttribute("kondateDeleteMenuCd");
		
		Integer menuCd = (Integer) searchMenuName.getMenuCd();
		
		//Delete文を実行
		kondateDeleteRepository.deleteById(menuCd);
	}
}