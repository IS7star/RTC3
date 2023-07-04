package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 気分テーブルのエンティティ
 * @author 櫻井樹
 * @version 1.0
 */

@Entity
@Table(name = "T_MOOD")
@Data
public class Mood {
	
	@Id
	@Column(name = "mood_cd")
	private Integer moodCd;
	
	@Column(name = "mood")
	private String mood;

}
