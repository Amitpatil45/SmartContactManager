package com.start.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.start.dao.UserRepository;
import com.start.entities.User;
import com.start.helper.Message;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/home")
	public String home(Model model) {
		model.addAttribute("title", "Home - Smart contact manager");
		return "home";
	}

	@RequestMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "About - Smart contact manager");
		return "about";
	}

	@RequestMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("title", "Register - Smart contact manager");
		model.addAttribute("user", new User());
		return "signup";
	}

	// handler for registering user
	@RequestMapping(value = "/do_register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") User user,
	        @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,HttpSession session) {
	  
	       try {
	    	   if (!agreement) {
		           System.out.println("you have not Aggred terms and condition");
		           throw new Exception("you have not Aggred terms and condition");
		        } 
		        user.setRole("Role_User");
	            user.setEnabled(true);
	            user.setImageUrl("default.png");
	            
	            System.out.println("Agreement"+agreement);
	            System.out.println("User"+user);
	            User result = this.userRepository.save(user);
		        
	            model.addAttribute("user",new Message("Succesfully register", "alert-success"));
	            return "signup";
			} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("user",user);
			session.setAttribute("message",new Message("Something Went wrong"+e.getMessage(),"alert-error" ) );											
			 return "signup";
		}
            
           
	        }

	
}
