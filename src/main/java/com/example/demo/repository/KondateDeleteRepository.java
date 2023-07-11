package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.ReshipiDelete;

/**
 * 献立削除機能を実行するためのインターフェース
 * @author 櫻井樹
 * @version 1.0
 */

public interface KondateDeleteRepository extends JpaRepository<ReshipiDelete, Integer> {
	
	/**
	 * 入力された献立名に対応した、献立一覧が存在するかを確認するための処理
	 * @param menuName
	 * @return 献立名に対応する、献立一覧の取得
	 */
	
	@Query(value = "SELECT * "
			+ " FROM T_RESHIPI "
			+ " WHERE menu_name=:menuName", nativeQuery = true)
	List<ReshipiDelete> checkReshipiByMenuName(String menuName);
	
	/**
	 * 入力された献立名に対応する、献立一覧を取得、表示するための処理
	 * @param menuName
	 * @return 献立名に対応する、献立一覧の取得
	 */
	
	@Query(value = "SELECT * "
			+ " FROM T_RESHIPI "
			+ " WHERE menu_name=:menuName", nativeQuery = true)
	List<ReshipiDelete> findByMenuName(String menuName);
	
	/**
	 * 選択された献立に対して、その献立・列を削除するための処理
	 * @param menuCd
	 * @return 選択された献立の削除
	 */
	
	public  List<ReshipiDelete> deleteByMenuCd
	(Integer menuCd);
}