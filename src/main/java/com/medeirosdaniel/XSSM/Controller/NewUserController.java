package com.medeirosdaniel.XSSM.Controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.medeirosdaniel.XSSM.Entity.PersonForm;
import com.medeirosdaniel.XSSM.Entity.UserEntity;
@Controller
public class NewUserController{
	
//	@GetMapping("/adduser")
//    public String checkForm(UserEntity userEntity) {
//    
//    	return "adduser";
//    }
//    
//    @PostMapping("/adduser")
//    public String checkConsistence(@Valid UserEntity userEntity, BindingResult result) {
//    	if(result.hasErrors()) {
//    		return "adduser";
//    	}
//    	return "index";
//    	
//    }

}
