/***
 * @author amir-reza abbasi
 */
package com.javasampleapproach.jqueryboostraptable.controller;


import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import com.javasampleapproach.jqueryboostraptable.model.User;

import com.javasampleapproach.jqueryboostraptable.repository.UserRepository;

// @CrossOrigin(origins = "http://localhost:3000") this annotation for react.js
@Service
@Controller
public class WebController {
	

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService;
	
	  @GetMapping("/members")
	   public String viewMembers(Model model,String keyword){
	 		//data have devices information
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "Welcome " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
	 		
	 		 
	 		if(keyword != null) {
	 			model.addAttribute("members",userRepo.search(keyword));
	 			
	 		}else {
	 		
	 		model.addAttribute("members",userRepo.findAll());
	 		
	 		}
	 		return "membersPage"; 
	     }
	  
	  

	  @GetMapping("/profile")
	  public String profile(Model model) {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "خوش آمدید " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
			model.addAttribute("user",user);
		  return "userprofile";
	  }
	  @GetMapping("/adminprofile")
	  public String aprofile(Model model,Integer id) {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			User u = userRepo.findById(id).get();
			model.addAttribute("userName", "خوش آمدید   " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
			model.addAttribute("user",u);
			model.addAttribute("cuser",user);
		  return "adminuserprofile";
	  }
	  @GetMapping("/access-denied")
	   public String access(Model model){
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "Welcome " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
	 		 
	 		 return "access-denied";
	     }
	  
	  @PostMapping("/saveUser")
		public String save(User u) {
			 System.out.println(u.getFinger() =="1");
			 if(u.getFinger().contentEquals("1")) {
				 userService.save(u,0);
			 }else {
				 userService.save(u,1);
			 }
			
			return "redirect:/members";
		}
	  
	  // this is used for user profile
	  @PostMapping("/saveUser")
		public String profile(User u, @RequestParam("file") MultipartFile file) {
		  User user = userRepo.findById(u.getId()).get();
		  System.out.println("User--------->"+user);
		  if(u.getPass() != null) {
			user.setPass(u.getPass());
		  }
		  if(u.getFullname() != null) {
			  user.setFullname(u.getFullname());
		  }
		  if(!file.isEmpty()) {
			  userService.savepro(user.getId(), file);
		  }else {
			  userRepo.save(user);
		  }
 
			return "redirect:/profile";
		}
		
		@RequestMapping(value={ "/login"}, method = RequestMethod.GET)
		public ModelAndView login(){
		    ModelAndView modelAndView = new ModelAndView();
		    modelAndView.setViewName("login");
		    return modelAndView;
		}
		
		@RequestMapping(value="/registration", method = RequestMethod.GET)
		public ModelAndView registration(){
		    ModelAndView modelAndView = new ModelAndView();
		    User user = new User();
		    modelAndView.addObject("user", user);
		    modelAndView.setViewName("registration");
		    return modelAndView;
		}
		@RequestMapping(value = "/registration", method = RequestMethod.POST)
		public ModelAndView createNewUser(@Valid  User user, BindingResult bindingResult) {
		    ModelAndView modelAndView = new ModelAndView();
		    List<User> us = userRepo.findBypersonalId(user.getPersonalId());
		    
		    if (!us.isEmpty()) {
		        bindingResult
		                .rejectValue("personalId", "error.user",
		                        "هم اکنون کاربری با این شماره کارمندی موجود است");
		    }
		    if (bindingResult.hasErrors()) {
		        modelAndView.setViewName("registration");
		    } else {	
		    	 userService.save(user,1); // 1 means user Role is simple user and 0 means role is Admin
		    }
		    return modelAndView;
		}
		@GetMapping("/deleteUser")
		public String deleteUser(Integer id) {
			try {
				userRepo.deleteById(id);
				
			} catch (Exception e) {
				return "errorPage";
			}
		
		return "redirect:/members";
		}
		@GetMapping("/findOneUser")
		@ResponseBody
		public Optional<User> findOneUser(Integer id) {
			return userRepo.findById(id);
		}
		@GetMapping("/findAllUser")
		@ResponseBody
		public List<User> findAllUser() {
			return userRepo.findAll();
		}
	
		

	
	
	
	}


