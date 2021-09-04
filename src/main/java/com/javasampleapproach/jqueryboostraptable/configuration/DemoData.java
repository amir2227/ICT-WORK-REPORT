package com.javasampleapproach.jqueryboostraptable.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.javasampleapproach.jqueryboostraptable.model.Role;
import com.javasampleapproach.jqueryboostraptable.repository.RoleRepository;


@Component
public class DemoData {

	@Autowired
	private RoleRepository roleRepo;
	
	
	 @EventListener
	    public void appReady(ApplicationReadyEvent event) {
		 roleRepo.save(new Role(1L, "ADMIN"));
		 roleRepo.save(new Role(2L, "USER")); 
		 roleRepo.save(new Role(3L, "PROUSER"));
		 
	    }
	
}
