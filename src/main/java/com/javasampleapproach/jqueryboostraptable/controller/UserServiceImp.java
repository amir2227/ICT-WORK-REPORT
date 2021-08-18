package com.javasampleapproach.jqueryboostraptable.controller;

import com.javasampleapproach.jqueryboostraptable.model.User;
import com.javasampleapproach.jqueryboostraptable.repository.RoleRepository;
import com.javasampleapproach.jqueryboostraptable.repository.UserRepository;
import com.javasampleapproach.jqueryboostraptable.controller.UserService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private WebConfiguration webconfiguration;
        
    @Override
    public void save(User user , Integer e) {
    	if(user.getPass().isEmpty()) {
    		
    		Optional<User> uo = userRepository.findById(user.getId());
        	if(uo.isPresent()) {
        		user.setPass(uo.get().getPass());
        	}
        	
    }else {
    	
    	user.setPass(webconfiguration.passwordEncoder().encode(user.getPass()));
    }
        user.setFullname(user.getFName() + " "+ user.getLname());
        if(e==0) {
        user.setRoles(roleRepository.findByname("ADMIN"));
        }else {
        user.setRoles(roleRepository.findByname("USER"));
        }
        userRepository.save(user);
    }
      @Override
    public User findByUsername(String username) {
        return userRepository.findBypersonalId(username).get(0);
    }
		
}