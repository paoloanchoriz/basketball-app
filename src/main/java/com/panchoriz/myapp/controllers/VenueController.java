package com.panchoriz.myapp.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.panchoriz.myapp.model.VenueDTO;
import com.panchoriz.myapp.service.VenueService;
import com.panchoriz.myapp.service.VenueService.VenueSearchBuilder;

@RestController
@RequestMapping("/venue")
public class VenueController {
	
	@Autowired
	private VenueService venueService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<VenueDTO> getList(@RequestParam(value = "pageNo", required = true)int pageNo, 
			@RequestParam(value = "searchCondition", required = false)String searchCondition, 
			@RequestParam(value = "province", required = false)String province, 
			@RequestParam(value = "city", required = false)String city, 
			@RequestParam(value = "courtType", required = false)Number[] courtType, 
			@RequestParam(value = "floorType", required = false)Number[] floorType) {
		
		VenueSearchBuilder searchBuilder = venueService.new VenueSearchBuilder();
		searchBuilder.searchCondition(searchCondition).province(province)
			.city(city).courtType(courtType).floorType(floorType);
		return venueService.getVenues(searchBuilder.build(), pageNo);
	}
	
	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public VenueDTO getDocument(@PathVariable("id")String id) {
		VenueDTO venue = null;
		if(StringUtils.isNotBlank(id)) {
			venue = venueService.findById(id);
		}
		return venue;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public boolean saveDocument(@RequestBody VenueDTO venue) {
		boolean successful = true;
		try{
			venueService.addVenue(venue);
		} catch (Exception e) {
			successful = false;
		} 
		return successful;
	}
	
	@RequestMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public boolean saveDocument(@PathVariable("id") String venueId, @RequestBody VenueDTO venue) {
		boolean successful = true;
		try { 
			if(StringUtils.isNotBlank(venueId)) {
				venueService.updateVenue(venue);
			} else {
				throw new Exception();
			}			
		} catch(Exception e) {
			successful = false;
		}
		return successful;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteDocument(@PathVariable("id") String venueId) {
		boolean successful = true;
		try{
			if(StringUtils.isNotBlank(venueId)) {
				venueService.deleteVenue(venueId);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			successful = false;
		}
		return successful;
	}
}
