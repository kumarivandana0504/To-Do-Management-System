package springbootproject.todolist.controller;

import java.util.logging.Logger;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springbootproject.todolist.createuser.CreateUser;
import springbootproject.todolist.entity.User;
import springbootproject.todolist.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger=Logger.getLogger(getClass().getName());
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true); 
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	
	}
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model theModel) {
		theModel.addAttribute("createUser",new CreateUser());
		return "registration-form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("createUser") CreateUser theCreateUser,
			BindingResult theBindingResult,
			Model theModel) {
		String username=theCreateUser.getUserName();
		logger.info(" processing registration form for :"+username);
	
		if(theBindingResult.hasErrors()) {
			return "registration-form";
		}
		
		User existing =userService.findByUserName(username);
		
		if(existing!=null) {
			theModel.addAttribute("createUser", new CreateUser());
			theModel.addAttribute("registrationError", "User name already exist");
			logger.warning("user name already exist");
			return "registration-form";
		}
		
		userService.save(theCreateUser);
		logger.info("successfully created user");
		
		return "registration-confirmation";
	}
	
	

}
