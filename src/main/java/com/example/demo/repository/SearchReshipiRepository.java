package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Reshipi;


/**
 * レシピテーブル情報を検索するインターフェース
 * @author 櫻井樹,hirono
 * @version 1.1
 */

public interface SearchReshipiRepository extends JpaRepository<Reshipi, Integer> {

	/**
	 * 気分コードからレシピテーブル情報を検索する
	 * @param moodCd
	 * @return moodCdが同一列の献立名一覧の検索
	 */

	@Query(value = "SELECT * "
			+ " FROM T_RESHIPI "
			+ " WHERE mood_cd=:moodCd", nativeQuery = true)
	List<Reshipi> findMenunameByMoodcd(@Param("moodCd") Integer moodCd);

	/**
	 * 献立コードからレシピテーブル情報を検索する
	 * @param menuCd
	 * @return レシピテーブルのmenuCdが一致する全カラム
	 */
	
	@Query(value = "SELECT * "
			+ " FROM T_RESHIPI "
			+ " WHERE menu_cd=:menuCd", nativeQuery = true)
	List<Reshipi> findByMenucd(Integer menuCd);

	/**
	 * 感情コードからレシピテーブル情報を検索する
	 * @param emotionCd
	 * @return レシピテーブルのemotionCdが一致する全カラム
	 */
	
	@Query(value = "SELECT * "
			+ " FROM T_RESHIPI "
			+ " WHERE emotion_cd=:emotionCd", nativeQuery = true)
	List<Reshipi> findByEmotioncd(@Param("emotionCd")Integer emotionCd);

}
	


