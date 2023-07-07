package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * 献立登録情報テーブルのエンティティクラス
 * @author 松永翔
 * @version 1.0
 */

@Entity
@Table(name = "T_RESHIPI")
@Data
public class ReshipiInput {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "menu_cd")
	private Integer menuCd;

	@Column(name = "menu_name")
	private String menuName;

	@Column(name = "mood_cd")
	private Integer moodCd;

	@Column(name = "mood")
	private String mood;

	@Column(name = "menu_img")
	private String menuImg;

	@Column(name = "food")
	private String food;

	@Column(name = "cook_method")
	private String cookMethod;

	@Column(name = "calorie")
	private Integer calorie;

	@Column(name = "cook_time")
	private Integer cookTime;

	@Column(name = "cost")
	private Integer cost;

	@Column(name = "emotion_cd")
	private Integer emotionCd;

	@Column(name = "emotion")
	private String emotion;
}
