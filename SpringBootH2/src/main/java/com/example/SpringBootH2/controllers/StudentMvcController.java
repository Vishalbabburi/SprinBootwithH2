package com.example.SpringBootH2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentMvcController {
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}

}
