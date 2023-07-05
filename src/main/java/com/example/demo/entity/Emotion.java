package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 感情識別テーブルのエンティティ
 * @author 櫻井樹
 * @version 1.0
 */

@Entity
@Table(name = "T_EMOTION")
@Data
public class Emotion {
	
	@Id
	@Column(name = "emotion_cd")
	private Integer emotionCd;
	
	@Column(name = "emotion")
	private String emotion;

}
