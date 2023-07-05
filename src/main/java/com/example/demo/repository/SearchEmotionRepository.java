

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Emotion;


/**
 * 感情テーブル情報を検索するインターフェース
 * @author 櫻井樹
 * @version 1.0
 */

public interface SearchEmotionRepository extends JpaRepository<Emotion, Integer> {

	/**
	 * 感情コードから気分(名)を検索する
	 * @param emotion_cd
	 * @return emotion
	 */

	@Query(value = "SELECT emotion_cd"
			+ " FROM T_EMOTION "
			+ " WHERE emotion=:emotion", nativeQuery = true)
	Integer findEmotioncdByEmotion(@Param("emotion") String emotion);

}

