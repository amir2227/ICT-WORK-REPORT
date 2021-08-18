package com.javasampleapproach.jqueryboostraptable.repository;


import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.javasampleapproach.jqueryboostraptable.model.Report;


@Repository
@Transactional
public interface ReportRepository extends JpaRepository<Report, Integer> {
	
	@Query(value = "SELECT * FROM gozaresh where d_date BETWEEN ?1 AND ?2",nativeQuery = true)
	public List<Report> search(String keywordfrom,String keywordto);
	
	@Query(value = "SELECT * FROM gozaresh where state like %:state%",nativeQuery = true)
	public List<Report> searchState(@Param("state") String state);
}
