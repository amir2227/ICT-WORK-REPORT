package com.javasampleapproach.jqueryboostraptable.repository;


import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;


import com.javasampleapproach.jqueryboostraptable.model.Report;


@Repository
@Transactional
public interface ReportRepository extends JpaRepository<Report, Integer> {
	
	@Query(value = "SELECT * FROM gozaresh where d_date BETWEEN ?1 AND ?2",nativeQuery = true)
	public List<Report> search(String keywordfrom,String keywordto);
	
	@Query(value = "SELECT * FROM gozaresh where state like %?1%",nativeQuery = true)
	public List<Report> searchState(String state);
	
	@Query(value = "SELECT * FROM gozaresh where d_date BETWEEN ?1 AND ?2 AND state like %?3%",nativeQuery = true)
	public List<Report> searchMulti(String from,String to, String state);
	
	@Query(value = "SELECT * FROM gozaresh where d_date BETWEEN ?1 AND ?2 AND state like %?3% AND user_job like %?4%",nativeQuery = true)
	public List<Report> searchMultijob(String from,String to, String state, String job);
	
	@Query(value = "SELECT * FROM gozaresh where d_date BETWEEN ?1 AND ?2 AND user_job like %?3%",nativeQuery = true)
	public List<Report> searchjobDate(String from,String to, String job);
	
	@Query(value = "SELECT * FROM gozaresh where user_job like %?1% AND state like %?2%",nativeQuery = true)
	public List<Report> searchJobState(String job, String state);
	
	@Query(value = "SELECT * FROM gozaresh where user_job like %?1%",nativeQuery = true)
	public List<Report> searchJob(String job);
	
	
}
