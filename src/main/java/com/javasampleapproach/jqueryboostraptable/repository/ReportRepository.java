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
	
	@Query(value = "SELECT * FROM gozaresh where d_date like %:keyword%",nativeQuery = true)
	public List<Report> search(@Param("keyword") String keyword);
}
