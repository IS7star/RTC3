package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.ReshipiDelete;

public interface KondateDeleteRepository extends JpaRepository<ReshipiDelete, Integer> {
	
	@Query(value = "SELECT * "
			+ " FROM T_RESHIPI "
			+ " WHERE menu_name=:menuName", nativeQuery = true)
	List<ReshipiDelete> checkReshipiByMenuName(String menuName);
	
	@Query(value = "SELECT * "
			+ " FROM T_RESHIPI "
			+ " WHERE menu_name=:menuName", nativeQuery = true)
	List<ReshipiDelete> findByMenuName(String menuName);
	
//	@Query(value = "DELETE "
//			+ " FROM T_RESHIPI "
//			+ " WHERE menu_cd=:menuCd", nativeQuery = true)
	
	
//	@Query(value = "SELECT menu_cd "
//			+ " FROM T_RESHIPI "
//			+ " WHERE menu_name=:menuName", nativeQuery = true)
//	Integer findMenuCdByMenuName(String menuName);
	
	public  List<ReshipiDelete> deleteByMenuCd
	(Integer menuCd);
}