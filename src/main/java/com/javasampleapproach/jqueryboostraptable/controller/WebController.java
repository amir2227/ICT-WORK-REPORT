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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.javasampleapproach.jqueryboostraptable.model.Base;
import com.javasampleapproach.jqueryboostraptable.model.Jobs;
import com.javasampleapproach.jqueryboostraptable.model.Report;
import com.javasampleapproach.jqueryboostraptable.model.State;
import com.javasampleapproach.jqueryboostraptable.model.User;
import com.javasampleapproach.jqueryboostraptable.repository.BaseRepository;
import com.javasampleapproach.jqueryboostraptable.repository.JobRepository;
import com.javasampleapproach.jqueryboostraptable.repository.ReportRepository;
import com.javasampleapproach.jqueryboostraptable.repository.Roozh;
import com.javasampleapproach.jqueryboostraptable.repository.StateRepository;
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
	
	@Autowired
	private StateRepository sRepo;
	
	@Autowired
	private JobRepository jRepo;
	
	  @GetMapping("/")
	  public String home() {
		  return "redirect:/ViewBases";
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
			model.addAttribute("userName", user.getFullname() + " (" + user.getPersonalId() + ")");
			
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
			model.addAttribute("userName", user.getFullname() + " (" + user.getPersonalId() + ")");
			model.addAttribute("reports",base.getReports());
			model.addAttribute("date",base.getD_date());
			model.addAttribute("baseId",base.getId());
			model.addAttribute("curentUser",user);
			model.addAttribute("baseUser",base.getUser());
			model.addAttribute("allUser",userRepo.findAll());
			if(user.getRoles().get(0).getName() == "PROUSER") {
				model.addAttribute("states",sRepo.findAll());
			}else {
				List<State> stateList = sRepo.searchJob(user.getJob());
				model.addAttribute("states",stateList);
			}
	  return "ReportPage";
	  }

	  @GetMapping("/report")
	   public String Reports(Model model,String keyword,String from,String to,String job){
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", user.getFullname() + " (" + user.getPersonalId() + ")");
			model.addAttribute("states", sRepo.findAll());
			model.addAttribute("jobs", jRepo.findAll());
			int bad = 0;
			if(keyword==null && from==null && to==null && job==null) {
				List<Report> rp = rRepo.findAll();
				model.addAttribute("reports",rp);
				model.addAttribute("total",rp.size());
				for(Report r : rp) {
					if(r.getIs_complete() == null) {
						bad++;
					}
				}
				model.addAttribute("bad",bad);
				model.addAttribute("right",rp.size() - bad);
			}else {
			if(keyword != "") {
				if(from !="" && to != "") {
					from = dateserialaze(from);
					to = dateserialaze(to);
					List<Report> mrp = new ArrayList<Report>();
					if(job != "") {
						mrp = 	rRepo.searchMultijob(from, to, keyword,job);
					}else {
						mrp = 	rRepo.searchMulti(from, to, keyword);
					}
				model.addAttribute("reports",mrp);
				model.addAttribute("total",mrp.size());
				for(Report r : mrp) {
					if(r.getIs_complete() == null) {
						bad++;
					}
				}
				model.addAttribute("bad",bad);
				model.addAttribute("right",mrp.size() - bad);
				
				}else {
					List<Report> mrp = new ArrayList<Report>();
					if(job !="") {
						mrp = 	rRepo.searchJobState(job, keyword);
					}else {
						mrp = 	rRepo.searchState(keyword);	
					}
					
					model.addAttribute("reports",mrp);
					model.addAttribute("total",mrp.size());
					for(Report r : mrp) {
						if(r.getIs_complete() == null) {
							bad++;
						}
					}
					model.addAttribute("bad",bad);
					model.addAttribute("right",mrp.size() - bad);
				}
	 		}else {
	 			if(from !="" && to != "") {
	 				from = dateserialaze(from);
	 				to = dateserialaze(to);
	 				List<Report> mrp = new ArrayList<Report>();
					if(job !="") {
						mrp = rRepo.searchjobDate(from, to, job);
					}else {
						mrp = rRepo.search(from, to);	
					}
					model.addAttribute("reports",mrp);
					model.addAttribute("total",mrp.size());
					for(Report r : mrp) {
						if(r.getIs_complete() == null) {
							bad++;
						}
					}
					model.addAttribute("bad",bad);
					model.addAttribute("right",mrp.size() - bad);
	 				
	 			}else {
	 				List<Report> mrp = new ArrayList<Report>();
					if(job !="") {
						mrp = rRepo.searchJob(job);
					}else {
						mrp = rRepo.findAll();	
					}
	 			 
	 			model.addAttribute("reports",mrp);
				model.addAttribute("total",mrp.size());
				for(Report r : mrp) {
					if(r.getIs_complete() == null) {
						bad++;
					}
				}
				model.addAttribute("bad",bad);
				model.addAttribute("right",mrp.size() - bad);
	 			}
	 		}
			}
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
	   @GetMapping("/states")
	   public String viewState(Model model){
	 		
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", user.getFullname() + " (" + user.getPersonalId() + ")");
			model.addAttribute("states",sRepo.findAll());
			model.addAttribute("jobs", jRepo.findAll());
			
	  return "states";
	  }
	  
	  @GetMapping("/access-denied")
	   public String access(Model model){
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			model.addAttribute("userName", user.getFullname() + " (" + user.getPersonalId() + ")");
	 		 
	 		 return "access-denied";
	     }
	  
	  @PostMapping("/saveUser")
		public String save(User u) {
			 
			 if(u.getFinger().contentEquals("0")) {
				 userService.save(u,0);
			 }else if(u.getFinger().contentEquals("1")) {
				 userService.save(u, 3);
			 }
			 else {
				 userService.save(u,1);
			 }
			
			return "redirect:/members";
		}
	  @PostMapping("/saveUserPro")
		public String savepro(User u) {
		  System.out.println( "User profile ======="+u);
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findByUsername(auth.getName());
			if(!u.getPass().isEmpty()) {
				user.setPass(u.getPass());
				if(user.getRoles().get(0).getName().contentEquals("ADMIN")) {
					 userService.save(user,0);
				 }else if(user.getRoles().get(0).getName().contentEquals("PROUSER")) {
					 userService.save(user,3);
				 }
				 else {
					 userService.save(user,1);
				 }
			}
			 
			
			return "redirect:/profile";
		}
	  @PostMapping("/saveState")
		public String savest(State s) { 
				 sRepo.save(s);
			return "redirect:/states";
		}
	  @PostMapping("/saveJobs")
		public String savejob(Jobs job) { 
				 jRepo.save(job);
			return "redirect:/states";
		}
	  @GetMapping("/findOneState")
	  @ResponseBody
	  	public Optional<State> findOneState(Integer id){
		  
		  return sRepo.findById(id);
	  }
	  @GetMapping("/findOneJob")
	  @ResponseBody
	  	public Optional<Jobs> findOneJob(Integer id){
		  
		  return jRepo.findById(id);
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
		    	user.setFinger("2");
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
		@GetMapping("/findOneRep")
		@ResponseBody
		public Optional<Report> findOnesub(Integer id) {
			return rRepo.findById(id);
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
			String day , month ="";
			if(jCal.getMonth()<10) {
				month = "0"+jCal.getMonth();
			}else {
				month =""+jCal.getMonth();
			}
			if(jCal.getDay()<10) {
				day = "0"+jCal.getDay();
			}else {
				day =""+jCal.getDay();
			}
			String cal = jCal.getYear()+"/"+month+"/"+day;
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
			String day , month ="";
			if(jCal.getMonth()<10) {
				month = "0"+jCal.getMonth();
			}else {
				month =""+jCal.getMonth();
			}
			if(jCal.getDay()<10) {
				day = "0"+jCal.getDay();
			}else {
				day =""+jCal.getDay();
			}
			String cal = jCal.getYear()+"/"+month+"/"+day;
			if(!bases.isPresent()) {
				return "errorPage";
			}
			Base base = bases.get();
			if(!cal.contentEquals(base.getD_date())) {
				return "errorPage";
			}
			String t = LocalTime.now().getHour()+":"+LocalTime.now().getMinute();
			r.setBase(base);
			r.setD_time(t);
			r.setD_date(cal);
			System.out.println("user role ------->"+ user.getRoles());
			if(user.getRoles().get(0).getName().contentEquals("PROUSER")) {
				System.out.println("in the if");
				User u = userRepo.findBypersonalId(r.getUsername()).get(0);
				System.out.println(u);
				r.setUser_job(u.getJob());
				r.setUsername(u.getFullname());
			}else {
				r.setUser_job(user.getJob());
				r.setUsername(user.getFullname());
			}
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
		@PostMapping("/editRport")
		public String editToBase(Report r,int baseid) {
			Report rp = rRepo.findById(r.getId()).get();
			rp.setClient(r.getClient());
			rp.setDescription(r.getDescription());
			rp.setIs_complete(r.getIs_complete());
			rp.setLocation(r.getLocation());
			rp.setState(r.getState());
			rp.setType(r.getType());
			rp.setUsername(r.getUsername());
			rRepo.save(rp);
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
		public List<Report> searchDat(String from, String to){ 
			
			if(from.length() != 10) {
			
			if(from.charAt(6) == '/') { // modify month
				from = from.substring(0,5)+"0"+from.substring(5);
				
			} 
			if(from.length() !=10) { // modify day
				from = from.substring(0,8)+"0"+from.substring(8);
				
			}
			
			}
			if(to.length() != 10) {
				
				if(to.charAt(6) == '/') { // modify month
					to = to.substring(0,5)+"0"+to.substring(5);
					
				} 
				if(to.length() !=10) { // modify day
					to = to.substring(0,8)+"0"+to.substring(8);
					
				}
				
				}
			
			return rRepo.search(from,to);
		}
		@GetMapping("/search/state")
		@ResponseBody
		public List<Report> searchState(String state){
			return rRepo.searchState(state);
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
		@GetMapping("/uncheckstatus")
		public String change(int id,int bid) {
			Optional<Report> rep = rRepo.findById(id);
			if(rep.isPresent()) {
				Report report = rep.get();
				report.setIs_complete(null);
				rRepo.save(report);
			}
			return "redirect:/ViewReports/?bid="+bid;
		}
		
		@GetMapping("/deleteState")
		public String del(int id) {
			try {
				sRepo.deleteById(id);
				
			} catch (Exception e) {
				return "errorPage";
			}
			return "redirect:/states";
		}
		@GetMapping("/deleteJob")
		public String delj(int id) {
			try {
				jRepo.deleteById(id);
				
			} catch (Exception e) {
				return "errorPage";
			}
			return "redirect:/states";
		}
		@GetMapping("/deleteReport")
		public String delr(int id,int bid) {
			try {
				rRepo.deleteById(id);
				
			} catch (Exception e) {
				return "errorPage";
			}
			return "redirect:/ViewReports/?bid="+bid;
		}
		
		public String dateserialaze(String date) {
			if(date.length() != 10) {
				if(date.charAt(6) == '/') { // modify month
					date = date.substring(0,5)+"0"+date.substring(5);
				} 
				if(date.length() !=10) { // modify day
					date = date.substring(0,8)+"0"+date.substring(8);	
				}
				}
				return date;
		}
	}


