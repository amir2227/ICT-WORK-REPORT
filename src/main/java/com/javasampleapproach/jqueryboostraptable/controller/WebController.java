/***
 * @author amir-reza abbasi
 */
package com.javasampleapproach.jqueryboostraptable.controller;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javasampleapproach.jqueryboostraptable.model.Base;
import com.javasampleapproach.jqueryboostraptable.model.Report;
import com.javasampleapproach.jqueryboostraptable.model.User;
import com.javasampleapproach.jqueryboostraptable.repository.BaseRepository;
import com.javasampleapproach.jqueryboostraptable.repository.ReportRepository;
import com.javasampleapproach.jqueryboostraptable.repository.Roozh;
import com.javasampleapproach.jqueryboostraptable.repository.UserRepository;

// @CrossOrigin(origins = "http://localhost:3000") this annotation for react.js
@Service
@Controller
public class WebController {
	

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BaseRepository baseRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReportRepository rRepo;
	
	  @GetMapping("/")
	  public String home() {
		  return "Welcome";
	  }
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

	  @GetMapping("/ViewBases")
	   public String viewBases(Model model){
	 		//data have devices information
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "Welcome " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
			
			if(user.getRoles().get(0).getName().contentEquals("ADMIN")) {
				model.addAttribute("bases",baseRepo.findAll());
			}else {
				model.addAttribute("bases",baseRepo.findByUser(user));
			}
			System.out.println();
	  return "basePage";
	  }
	  
	  @GetMapping("/ViewReports")
	   public String viewReports(Model model, int bid){
	 		
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			Base base = baseRepo.findById(bid).get();
			
			
			model.addAttribute("userName", "Welcome " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
			model.addAttribute("reports",base.getReports());
			model.addAttribute("date",base.getD_date());
			model.addAttribute("baseId",base.getId());
			
	  return "ReportPage";
	  }

	  @GetMapping("/report")
	   public String Reports(Model model){
	 		
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "Welcome " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
			
			if(user.getRoles().get(0).getName().contentEquals("ADMIN")) {
				model.addAttribute("bases",baseRepo.findAll());
			}else {
				model.addAttribute("bases",baseRepo.findByUser(user));
			}
			System.out.println();
	  return "report";
	  }

	  @GetMapping("/profile")
	  public String profile(Model model) {
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", "خوش آمدید " + user.getFName() + " " + user.getLname() + " (" + user.getPersonalId() + ")");
			model.addAttribute("user",user);
		  return "userprofile";
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
			 
			 if(u.getFinger().contentEquals("0")) {
				 userService.save(u,0);
			 }else {
				 userService.save(u,1);
			 }
			
			return "redirect:/members";
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
	
		@GetMapping("/findAllReports")
		@ResponseBody
		public List<Base> findAllReport() {
			return baseRepo.findAll();
		}
		
		@GetMapping("/findOneReport")
		@ResponseBody
		public Optional<Base> findOneReport(Integer id) {
			return baseRepo.findById(id);
		}
	
		@GetMapping("/findReportByUser")
		@ResponseBody
		public List<Base> findReportByUser(int id) {
			Optional<User> user = userRepo.findById(id);
			return baseRepo.findByUser(user.get());
		}
		@PostMapping("/saveBase")
		public String saveBase() {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			List<Base> bases = baseRepo.findByUser(user);
			Base b = new Base();
			Roozh jCal = new Roozh();
			jCal.gregorianToPersian(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
			String cal = jCal.getYear()+"/"+jCal.getMonth()+"/"+jCal.getDay();
			b.setD_date(cal);
			b.setUser(user);
			for(Base base : bases) {
				if(base.getD_date().contentEquals(cal)) {
					return "errorPage";
				}
			}
			baseRepo.save(b);
			return "redirect:/ViewBases";
		}
		@PostMapping("/saveRportToBase")
		public String saveToBase(Report r,int baseid) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			Optional<Base> bases = baseRepo.findById(baseid);
			Roozh jCal = new Roozh();
			jCal.gregorianToPersian(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
			String cal = jCal.getYear()+"/"+jCal.getMonth()+"/"+jCal.getDay();
			if(!bases.isPresent()) {
				return "errorPage";
			}
			Base base = bases.get();
			if(!cal.contentEquals(base.getD_date())) {
				return "errorPage";
			}
			r.setBase(base);
			r.setUsername(user.getFullname());
			r.setD_time(LocalTime.now().toString());
			r.setD_date(cal);
			rRepo.save(r);
			System.out.println("report ----->"+r);
			Boolean b = base.getReports().add(r);
			System.out.println("base reports after add----------->"+base.getReports());
			
			if(!b) {
				return "errorPage";
			}
			baseRepo.save(base);
			return "redirect:/ViewReports/?bid="+baseid;
		}
		@GetMapping("/search/dateAndUser")
		@ResponseBody
		public List<Base> searchDate(String keyword,int uid){
		 List<Base> baseList = baseRepo.search(keyword);
		 Optional<User> users = userRepo.findById(uid);
		 List<Base> userBases = new ArrayList<Base>();
		 if(users.isPresent()) {
			 for(int i=0; i<baseList.size(); i++) {
				 if(baseList.get(i).getUser().equals(users.get())) {
					userBases.add(baseList.get(i));
				 }
			 }
			 
			 return userBases;
		 }else if(baseList.isEmpty()) {
			 if(users.isPresent()) {
			 return baseRepo.findByUser(users.get());
			 }else {
				 return null;
			 }
		 }else {
			 
			 return baseList;
		 }
		}
		@GetMapping("/search/date")
		@ResponseBody
		public List<Report> searchDat(String keyword){
			return rRepo.search(keyword);
		}
		@GetMapping("/changestatus")
		public String changeS(int id,int bid) {
			Optional<Report> rep = rRepo.findById(id);
			if(rep.isPresent()) {
				Report report = rep.get();
				report.setIs_complete(true);
				rRepo.save(report);
			}
			return "redirect:/ViewReports/?bid="+bid;
		}
	}


