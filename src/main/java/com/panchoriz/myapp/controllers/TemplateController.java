package com.panchoriz.myapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/view")
@Controller
public class TemplateController {
	/***
	 * This is where all the templates are mapped.
	 * 
	 */
	
	@RequestMapping({"/venue/add", "/venue/edit"})
	public String getVenueForm() {
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
	
	@RequestMapping("/gameSchedule/add")
	public String getGameScheduleForm() {
		return "/gameSchedule/form";
	}
}
