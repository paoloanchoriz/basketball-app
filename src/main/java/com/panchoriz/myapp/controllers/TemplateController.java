package com.panchoriz.myapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.panchoriz.myapp.utils.ProvinceAndCitiesUtility;

@RequestMapping("/view")
@Controller
public class TemplateController {
	/***
	 * This is where all the templates are mapped.
	 * 
	 */
	
	@RequestMapping({"/venue/add", "/venue/edit"})
	public String getVenueForm() {
		System.out.println(ProvinceAndCitiesUtility.getLocationMap());
		return "/venue/form";
	}
	
	@RequestMapping("/venue/list")
	public String getVenueList() {
		return "/venue/list";
	}
	
	@RequestMapping("/venue/detail")
	public String getVenueDetail() {
		return "/venue/detail";
	}
}
