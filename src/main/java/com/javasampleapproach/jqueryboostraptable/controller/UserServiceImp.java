package com.javasampleapproach.jqueryboostraptable.controller;

import com.javasampleapproach.jqueryboostraptable.model.User;
import com.javasampleapproach.jqueryboostraptable.repository.RoleRepository;
import com.javasampleapproach.jqueryboostraptable.repository.UserRepository;
import com.javasampleapproach.jqueryboostraptable.controller.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;


@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
   // @Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private WebConfiguration webconfiguration;
        
    @Override
    public void save(User user , Integer e) {
    	if(!user.getPass().isEmpty()) {
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
	@Override
	public void saveprofile(User user, Integer e, MultipartFile file) {
	    if(!user.getPass().isEmpty()) {
		 user.setPass(webconfiguration.passwordEncoder().encode(user.getPass()));
	    }
	        if(e==0) {
	        user.setRoles(roleRepository.findByname("ADMIN"));
	        }else{
	        	 user.setRoles(roleRepository.findByname("USER"));
	        }
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if(fileName.contains("..")) {
				System.out.println("not a valid file");
			}
	try {
		user.setProfile(Base64.getEncoder().encodeToString(file.getBytes()));
	} catch (IOException er) {
		System.out.println("file ");
		er.printStackTrace();
	}
	        userRepository.save(user);
		
	}
	@Override
	public void savepro(int user_id, MultipartFile file) {
		User user = userRepository.findById(user_id).get();
		
		 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			if(fileName.contains("..")) {
				System.out.println("not a valid file");
			}
	try {
		user.setProfile(Base64.getEncoder().encodeToString(file.getBytes()));
	} catch (IOException er) {
		System.out.println("file ");
		er.printStackTrace();
	}
	        userRepository.save(user);
		
	}
	
}