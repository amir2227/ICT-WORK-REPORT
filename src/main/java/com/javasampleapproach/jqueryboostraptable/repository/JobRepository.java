package com.javasampleapproach.jqueryboostraptable.repository;



import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.javasampleapproach.jqueryboostraptable.model.Jobs;


@Repository
@Transactional
public interface JobRepository extends JpaRepository<Jobs, Integer> {
  
	List<Jobs> findByname(String name);
	
}
