package com.medeirosdaniel.XSSM.Controller;

import com.medeirosdaniel.XSSM.Entity.UserEntity;
import com.medeirosdaniel.XSSM.Entity.PersonForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class ErrorController implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }
    @GetMapping("/a")
    public String checkForm(PersonForm personForm) {
    
    	return "form";
    }
    
    @PostMapping("/a")
    public String checkConsistence(@Valid PersonForm personForm, BindingResult result) {
    	if(result.hasErrors()) {
    		return "form";
    	}
    	return "index";
    	
    }
    }

