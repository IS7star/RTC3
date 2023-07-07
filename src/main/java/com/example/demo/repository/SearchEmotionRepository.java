

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Emotion;


/**
 * 感情テーブル情報を検索するインターフェース
 * @author 櫻井樹、松永翔
 * @version 1.0
 */

public interface SearchEmotionRepository extends JpaRepository<Emotion, Integer> {

	/**
	 * 感情(名)から感情コードを検索する
	 * @param emotion_cd
	 * @return emotion
	 */

	@Query(value = "SELECT emotion_cd"
			+ " FROM T_EMOTION "
			+ " WHERE emotion=:emotion", nativeQuery = true)
	Integer findEmotionCdByEmotion(@Param("emotion") String emotion);
	
	/**
	 * 感情コードから感情(名)を検索する
	 * @param emotionCd
	 * @return emotion
	 */

	@Query(value = "SELECT emotion "
			+ " FROM t_emotion"
			+ " WHERE emotion_cd=:emotionCd", nativeQuery = true)
	String findEmotionByEmotionCd(@Param("emotionCd") Integer emotionCd);

}

