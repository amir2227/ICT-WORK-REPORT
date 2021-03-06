package com.javasampleapproach.jqueryboostraptable.repository;



import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.jqueryboostraptable.model.State;

@Repository
@Transactional
public interface StateRepository extends JpaRepository<State, Integer> {
  
	List<State> findByname(String name);
	

	@Query(value = "SELECT * FROM state where user_job like %?1%",nativeQuery = true)
	public List<State> searchJob(String job);
}
