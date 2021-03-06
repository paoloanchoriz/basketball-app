package com.panchoriz.myapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/bbapp")
	public String getIndex() {
		return "index";
	}
	
	@RequestMapping("/")
	public String getSampleGoogleMaps() {
		return "sampleGoogleMaps";
	}
	
	@RequestMapping("/sampleCustomSelect")
	public String getSampleCustomSelect() {
		return "sampleCustomSelect";
	}
}
