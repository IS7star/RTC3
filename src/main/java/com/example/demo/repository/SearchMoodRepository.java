package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Mood;


/**
 * 気分テーブル情報を検索するインターフェース
 * @author 櫻井樹
 * @version 1.0
 */

public interface SearchMoodRepository extends JpaRepository<Mood, Integer> {

	/**
	 * 気分コードから気分(名)を検索する
	 * @param moodCd
	 * @return mood
	 */

	@Query(value = "SELECT mood "
			+ " FROM T_MOOD "
			+ " WHERE mood_cd=:moodCd", nativeQuery = true)
	String findMoodByMoodcd(@Param("moodCd") Integer moodCd);

}

