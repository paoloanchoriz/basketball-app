package com.panchoriz.myapp.controllers;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.panchoriz.myapp.model.GameScheduleDTO;
import com.panchoriz.myapp.service.GameScheduleService;

@RestController
@RequestMapping("/gameSchedule")
public class GameScheduleController {
	
	@Autowired
	private GameScheduleService gameScheduleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<GameScheduleDTO> getList() {
		return gameScheduleService.getGameSchedules();
	}
	
	@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public GameScheduleDTO getDocument(@PathVariable("id")String id) {
		GameScheduleDTO venue = null;
		if(StringUtils.isNotBlank(id)) {
			venue = gameScheduleService.findById(id);
		}
		return venue;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public boolean saveDocument(@RequestBody GameScheduleDTO gameSchedule) {
		boolean successful = true;
		try{
			gameScheduleService.addSchedule(gameSchedule);
		} catch (Exception e) {
			successful = false;
		} 
		return successful;
	}
	
	@RequestMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
	public boolean saveDocument(@PathVariable("id") String gameScheduleId, @RequestBody GameScheduleDTO gameSchedule) {
		boolean successful = true;
		try { 
			if(StringUtils.isNotBlank(gameScheduleId)) {
				gameScheduleService.updateGameSchedule(gameSchedule);
			} else {
				throw new Exception();
			}			
		} catch(Exception e) {
			successful = false;
		}
		return successful;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteDocument(@PathVariable("id") String gameScheduleId) {
		boolean successful = true;
		try{
			if(StringUtils.isNotBlank(gameScheduleId)) {
				gameScheduleService.deleteVenue(gameScheduleId);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			successful = false;
		}
		return successful;
	}
}
