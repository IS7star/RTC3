package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Reshipi;

/**
 * 入力画面で入力された献立情報を、t_reshipiテーブルに登録するためのインターフェース
 * @author 松永翔
 * @version 1.0
 */

public interface KondateInputRepository extends JpaRepository<Reshipi, Integer> {

	/**
	 * レシピ情報テーブルより、シーケンスされたメニューコードを取得
	 * @return menuCd   メニューコード
	 */

	@Query(value = "SELECT nextval('t_reshipi_menu_cd_seq')", nativeQuery = true)
	Integer getMenuCd();

	/**
	 * 献立入力画面で入力された値などを、t_reshipiテーブルに登録するためのメソッド
	 * @param getMC        メニューコード
	 * @param menuName		献立名
	 * @param moodCd  		気分コード
	 * @param menuImg      画像名
	 * @param food   		材料
	 * @param cookMethod	料理手順
	 * @param calorie		カロリー
	 * @param cookTime		調理時間
	 * @param cost			費用
	 * @param emotionCd	感情コード
	 */

	@Modifying
	@Query(value = "INSERT INTO t_reshipi(menu_cd, menu_name, mood_cd, menu_img, food, cook_method, calorie, cook_time, cost, emotion_cd)"
			+ " VALUES (:getMC, :menuName, :moodCd, :menuImg, :food, :cookMethod, :calorie, :cookTime, :cost, :emotionCd)", nativeQuery = true)
	void saveMenu(
			@Param("getMC") Integer getMC,
			@Param("menuName") String menuName,
			@Param("moodCd") Integer moodCd,
			@Param("menuImg") String menuImg,
			@Param("food") String food,
			@Param("cookMethod") String cookMethod,
			@Param("calorie") Integer calorie,
			@Param("cookTime") Integer cookTime,
			@Param("cost") Integer cost,
			@Param("emotionCd") Integer emotionCd);

}
