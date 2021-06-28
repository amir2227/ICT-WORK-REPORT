package com.javasampleapproach.jqueryboostraptable.repository;


import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.javasampleapproach.jqueryboostraptable.model.Report;
import com.javasampleapproach.jqueryboostraptable.model.User;




@Repository
@Transactional
public interface ReportRepository extends JpaRepository<Report, Integer> {

	List<Report> findByUsers(List<User> users);
}
