package com.javasampleapproach.jqueryboostraptable.repository;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.jqueryboostraptable.model.Report;




@Repository
@Transactional
public interface ReportRepository extends JpaRepository<Report, Integer> {

		
}
