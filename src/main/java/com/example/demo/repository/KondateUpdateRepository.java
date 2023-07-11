package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Reshipi;

/**
 * 更新画面で入力された献立情報を、t_reshipiテーブルに登録するためのインターフェース
 * @author 松永翔
 * @version 1.0
 */

public interface KondateUpdateRepository extends JpaRepository<Reshipi, Integer>{

	/**
	 * メニューコードからレシピ情報を検索するメソッド
	 * @param menuCd
	 * @return レシピ情報
	 */
	
	public Optional<Reshipi> findByMenuCd(Integer MenuCd);
	
	
}
