package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Reshipi;
import com.example.demo.form.ReshipiForm;
import com.example.demo.repository.SearchEmotionRepository;
import com.example.demo.repository.SearchMoodRepository;
import com.example.demo.repository.SearchReshipiRepository;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;


/**
 * レシピテーブル情報と気分情報テーブルから値を取得するためのサービスクラス
 * @author hirono
 * @version 1.0
 */

@AllArgsConstructor
@Service
public class SearchReshipiService {

	private final SearchMoodRepository searchMoodRepository;
	private final SearchEmotionRepository searchEmotionRepository;
	private final SearchReshipiRepository searchReshipiRepository;
	private final HttpSession session;

	/**
	 * 気分情報テーブルの気分(名)を取得してセッションに格納するメソッド
	 * 気分コードで検索
	 */

	public void getMoodByMoodcd() {
		//セッション"MoodCd"のmoodCd(気分コード)をsearchMoodに格納
		ReshipiForm searchMood = (ReshipiForm) session.getAttribute("moodCd");
		//searchMoodにあるmoodCdで、mood(気分名)を検索して格納
		String mood = searchMoodRepository.findMoodByMoodcd(searchMood.getMoodCd());
		//取得した値を"Mood"キーのセッションに格納
		session.setAttribute("mood", mood);
	}
	
	/**
	 * レシピテーブル情報の全カラムを取得してセッションに格納するメソッド
	 * 気分コードで検索
	 */
	
	public void getMenunameByMoodcd() {
		//セッション"moodCd"のmoodCd(気分コード)をsearchMenunameに格納
		ReshipiForm searchMenuname = (ReshipiForm) session.getAttribute("moodCd");
		//searchMenunameにあるmoodCdで、レシピテーブル情報を検索して格納
		List<Reshipi> menu = searchReshipiRepository.findMenunameByMoodcd(searchMenuname.getMoodCd());
		//取得した値を"menuInfo"キーのセッションに格納
		session.setAttribute("menuInfo", menu);
	}
	
	/**
	 * レシピテーブル情報の全カラムを取得してセッションに格納するメソッド
	 * 献立コードで検索
	 */
	
	public void getReshipiInfoByMenucd() {
		//セッション"menuInfo"のmenuCd(献立コード)をsearchReshipiInfoに格納
		ReshipiForm searchReshipiInfo = (ReshipiForm) session.getAttribute("menuCd");
		//searchReshipiInfoにあるmenuCdで、レシピテーブル情報を検索して格納
		List<Reshipi> reshipiList = searchReshipiRepository.findByMenucd(searchReshipiInfo.getMenuCd());
		//取得した値を"reshipiList"キーのセッションに格納
		session.setAttribute("reshipiList", reshipiList);
	}
	
	/**
	 * レシピテーブル情報の全カラムを取得してセッションに格納するメソッド
	 * ①WebAPIで取得したemotion(感情名)で、感情識別テーブルの感情コードを検索
	 * ②①で取得した感情コードで、献立情報テーブルの全カラムを検索
	 */
	
	public void getReshipiEmotioncdByEmotion() {
		//セッション"emotion"のemotion(感情名)をsearchEmotionCdに格納
		ReshipiForm searchEmotionCd = (ReshipiForm) session.getAttribute("emotion");
		//searchEmotionCdにあるemotionで、emotionCd(感情コード)を検索して格納
		Integer emotionCd = searchEmotionRepository.findEmotioncdByEmotion(searchEmotionCd.getEmotion());
		//emotionCdにあるemotionCdで、レシピテーブル情報を検索して格納
		List<Reshipi> reshipiList = searchReshipiRepository.findByEmotioncd(emotionCd);
		//取得した値を"menuInfo"キーのセッションに格納
		session.setAttribute("menuInfo", reshipiList);
	}
}


