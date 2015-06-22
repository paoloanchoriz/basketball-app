package com.panchoriz.myapp.repositories.documents;

import java.util.Date;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.panchoriz.myapp.constants.AgeGroup;
import com.panchoriz.myapp.constants.GameType;

@Document(collection="gameSchedule")
@TypeAlias("gameSchedule")
public class GameScheduleDocument extends AbstractDocument<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5051965903958584041L;
	
	private VenueDocument venue;
	private int minPlayers;
	private int maxPlayers;
	private Date gameTime;
	private double duration; // 24 hour format
	private GameType gameType;
	private AgeGroup ageGroup;
	
	//add restriction in the future
	//private Restriction restriction;
	
	private boolean isCancelled;
	
	private String description;

	public VenueDocument getVenue() {
		return venue;
	}

	public void setVenue(VenueDocument venue) {
		this.venue = venue;
	}

	public int getMinPlayers() {
		return minPlayers;
	}

	public void setMinPlayers(int minPlayers) {
		this.minPlayers = minPlayers;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public Date getGameTime() {
		return gameTime;
	}

	public void setGameTime(Date gameTime) {
		this.gameTime = gameTime;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(double duration) {
		this.duration = duration;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public AgeGroup getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(AgeGroup ageGroup) {
		this.ageGroup = ageGroup;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
