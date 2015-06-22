package com.panchoriz.myapp.service;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.panchoriz.myapp.model.GameScheduleDTO;
import com.panchoriz.myapp.repositories.GameScheduleRepository;
import com.panchoriz.myapp.repositories.documents.GameScheduleDocument;
import com.panchoriz.myapp.repositories.documents.VenueDocument;
import com.panchoriz.myapp.utils.DateTimeUtil;

@Service("gameScheduleService")
public class GameScheduleService {
	
	private static final SimpleDateFormat SCHEDULE_FORMAT = new SimpleDateFormat("ddMMMyyyy HH:mm");
	
	@Autowired private GameScheduleRepository gameScheduleRepository;
	
	public GameScheduleDTO findById(String id) {
		return getTransferObject(gameScheduleRepository.findOne(id));
	}
	
	public void addSchedule(GameScheduleDTO gameSchedule) {
		gameSchedule.setScheduleId(DateTimeUtil.getStringTodaysDateWithTime());
		save(getDocument(gameSchedule));
	}
	
	public void updateGameSchedule(GameScheduleDTO gameSchedule) {
		GameScheduleDocument document = gameScheduleRepository.findOne(gameSchedule.getScheduleId());
		// Check if document exists
		if(document != null) {
			document = getDocument(gameSchedule);
			save(document);
		}
	}
	
	public void deleteVenue(String venueId) {
		gameScheduleRepository.delete(venueId);
	}
	
	private void save(GameScheduleDocument document) {
		gameScheduleRepository.save(document);
	}

	private GameScheduleDTO getTransferObject(GameScheduleDocument document) {
		GameScheduleDTO gameSchedule = new GameScheduleDTO();
		
		gameSchedule.setScheduleId(document.getDocumentId());
		gameSchedule.setAgeGroup(document.getAgeGroup());
		
		gameSchedule.setDate(DateTimeUtil.getFormattedDate(document.getGameTime()));
		gameSchedule.setTime(DateTimeUtil.getFormattedTime(document.getGameTime()));
		
		//TODO: Format to a readable form
		gameSchedule.setDuration(document.getDuration());
		
		gameSchedule.setGameType(document.getGameType());
		gameSchedule.setDescription(document.getDescription());
		gameSchedule.setMaxPlayers(document.getMaxPlayers());
		gameSchedule.setMinPlayers(document.getMinPlayers());
		gameSchedule.setTime(String.valueOf(document.getDuration()));
		
		//TODO: Set status here by comparing min and max players
		// against the number of players that already joined
		// CANCEL status is when the is Cancelled Flag is true
		//gameSchedule.setStatus();
		
		
		VenueDocument venueDocument = document.getVenue();
		if(venueDocument != null) {
			gameSchedule.setCity(venueDocument.getCity());
			gameSchedule.setProvince(venueDocument.getProvince());
			gameSchedule.setVenueName(venueDocument.getVenueName());
			gameSchedule.setVenueId(venueDocument.getDocumentId());
		}
		
		return gameSchedule;
	}
	
	private GameScheduleDocument getDocument(GameScheduleDTO gameSchedule) {
		GameScheduleDocument document = new GameScheduleDocument();
		document.setAgeGroup(gameSchedule.getAgeGroup());
		document.setDescription(gameSchedule.getDescription());
		document.setDocumentId(gameSchedule.getScheduleId());
		document.setDuration(gameSchedule.getDuration());
		
		String formattedTime = new StringBuilder()
			.append(gameSchedule.getDate())
			.append(" ")
			.append(gameSchedule.getTime()).toString();
		document.setGameTime(DateTimeUtil.getParsedDate(formattedTime, SCHEDULE_FORMAT));
		
		document.setGameType(gameSchedule.getGameType());
		document.setMaxPlayers(gameSchedule.getMaxPlayers());
		document.setMinPlayers(gameSchedule.getMinPlayers());
		
		getVenueDocument(gameSchedule, document);
		return null;
	}

	private void getVenueDocument(GameScheduleDTO gameSchedule,
			GameScheduleDocument document) {
		VenueDocument venueDocument = new VenueDocument();
		venueDocument.setVenueName(gameSchedule.getVenueName());
		venueDocument.setDocumentId(gameSchedule.getVenueId());
		venueDocument.setCity(gameSchedule.getCity());
		venueDocument.setProvince(gameSchedule.getProvince());
		document.setVenue(venueDocument);
	}
	
	public Page<GameScheduleDTO> getGameSchedules() {
		return gameScheduleRepository
				.findAll(new PageRequest(0, 10, new Sort(Sort.Direction.DESC, "gameTime")))
				.map(new Converter<GameScheduleDocument, GameScheduleDTO>() {
						@Override
						public GameScheduleDTO convert(GameScheduleDocument source) {
							return getTransferObject(source);
						}
					});
	}

}
