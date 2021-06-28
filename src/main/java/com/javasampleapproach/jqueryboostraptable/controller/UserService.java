package com.javasampleapproach.jqueryboostraptable.controller;



import com.javasampleapproach.jqueryboostraptable.model.User;

public interface UserService {
    void save(User user , Integer e);
    User findByUsername(String username);
 
  
}