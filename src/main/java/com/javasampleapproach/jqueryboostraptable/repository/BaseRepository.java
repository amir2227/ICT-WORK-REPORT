package com.javasampleapproach.jqueryboostraptable.repository;


import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javasampleapproach.jqueryboostraptable.model.Base;
import com.javasampleapproach.jqueryboostraptable.model.User;




@Repository
@Transactional
public interface BaseRepository extends JpaRepository<Base, Integer> {

	List<Base> findByUser(User user);
	
	@Query(value = "SELECT * FROM base where d_date like %:keyword%",nativeQuery = true)
	public List<Base> search(@Param("keyword") String keyword);
}
