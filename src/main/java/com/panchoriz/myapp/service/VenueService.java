package com.panchoriz.myapp.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.NumberPath;
import com.mysema.query.types.path.StringPath;
import com.panchoriz.myapp.model.VenueDTO;
import com.panchoriz.myapp.repositories.VenueRepository;
import com.panchoriz.myapp.repositories.documents.QVenueDocument;
import com.panchoriz.myapp.repositories.documents.VenueDocument;
import com.panchoriz.myapp.utils.DateTimeUtil;

@Service("venueService")
public class VenueService {
	
	@Autowired private VenueRepository  venueRepository;

	public VenueDTO findById(String id) {
		return getTransferObject(venueRepository.findOne(id));
	}
	
	public void addVenue(VenueDTO venue) {
		venue.setVenueId(DateTimeUtil.getStringTodaysDateWithTime());
		save(getDocument(venue));
	}
	
	public void updateVenue(VenueDTO venue) {
		VenueDocument document = venueRepository.findOne(venue.getVenueId());
		// Check if document exists
		if(document != null) {
			document = getDocument(venue);
			save(document);
		}
	}
	
	public void deleteVenue(String venueId) {
		venueRepository.delete(venueId);
	}
	
	private void save(VenueDocument document) {
		venueRepository.save(document);
	}

	private VenueDTO getTransferObject(VenueDocument document) {
		VenueDTO venue = new VenueDTO();
		venue.setCity(document.getCity());
		venue.setContact(document.getContact());
		venue.setFlooringType(document.getFlooringType());
		venue.setVenueId(document.getDocumentId());
		venue.setCourtType(document.getCourtType());
		venue.setLatitude(document.getLatitude());
		venue.setLongitude(document.getLongitude());
		venue.setProvince(document.getProvince());
		venue.setRate(document.getRate());
		venue.setStreetAddress(document.getStreetAddress());
		venue.setVenueName(document.getVenueName());
		return venue;
	}
	
	private VenueDocument getDocument(VenueDTO venue) {
		VenueDocument document = new VenueDocument();
		document.setDocumentId(venue.getVenueId());
		document.setCity(venue.getCity());
		document.setContact(venue.getContact());
		document.setFlooringType(venue.getFlooringType());
		document.setCourtType(venue.getCourtType());
		document.setLatitude(venue.getLatitude());
		document.setLongitude(venue.getLongitude());
		document.setProvince(venue.getProvince());
		document.setRate(venue.getRate());
		document.setStreetAddress(venue.getStreetAddress());
		document.setVenueName(venue.getVenueName());
		return document;
	}
	
	public Page<VenueDTO> getVenues(VenueSearch venueSearch, int pageNo) {
		Predicate searchPredicate = getPredicate(venueSearch);
		return venueRepository.findAll(searchPredicate,
					new PageRequest(pageNo, 10, new Sort(Sort.Direction.ASC, "venueName"))
				).map(new Converter<VenueDocument, VenueDTO>() {
			@Override
			public VenueDTO convert(VenueDocument source) {
				return getTransferObject(source);
			}
		});
	}
	
	private void setMainSearch(BooleanBuilder mainPredicate, QVenueDocument venueQ, String searchConditions) {
		BooleanBuilder streetAddress = new BooleanBuilder();
		mainPredicate.or(streetAddress);
		String[] sampleSearch = searchConditions.split(" ");
		for(String searchStr:sampleSearch) {
			if(StringUtils.isNotBlank(searchStr)) {
				mainPredicate.or(venueQ.venueName.matches(searchStr));
				streetAddress.or(venueQ.streetAddress.matches(searchStr));
			}
		}
	}
	
	private Predicate getAndPredicate(StringPath stringPath, String condition) {
		return new BooleanBuilder(stringPath.equalsIgnoreCase(condition));
	}
	
	private Predicate getPredicate(VenueSearch searchConditions) {
		QVenueDocument venueQuery = QVenueDocument.venueDocument;
		BooleanBuilder mainQuery = new BooleanBuilder();
		if(StringUtils.isNotBlank(searchConditions.search)) {
			setMainSearch(mainQuery, venueQuery, searchConditions.search);
		}
		
		if(StringUtils.isNotBlank(searchConditions.province)) {
			mainQuery.and(getAndPredicate(venueQuery.province, searchConditions.province));
			
			// City should only be available if there is a province search condition.
			if(StringUtils.isNotBlank(searchConditions.city)) {
				mainQuery.and(getAndPredicate(venueQuery.city, searchConditions.city));
			}
		}
		
		if(searchConditions.courtType.length > 0) {
			mainQuery.and(getInPredicate(venueQuery.courtType, searchConditions.courtType));
		}
		
		if(searchConditions.floorType.length > 0) {
			mainQuery.and(getInPredicate(venueQuery.flooringType, searchConditions.floorType));
		}
		
		return mainQuery;
	}

	private BooleanBuilder getInPredicate(NumberPath<Integer> numberPath,
			Number[] conditions) {
		return new BooleanBuilder(numberPath.in(conditions));
	}
	
	private class VenueSearch {
		String search;
		String province;
		String city;
		Number[] courtType;
		Number[] floorType;
	}
	
	public class VenueSearchBuilder {
		VenueSearch venueSearch;
		
		public VenueSearchBuilder() {
			this.venueSearch = new VenueSearch();
		}
		public VenueSearchBuilder searchCondition(String search) {
			venueSearch.search = search;
			return this;
		}
		public VenueSearchBuilder province(String province) {
			venueSearch.province = province;
			return this;
		}
		public VenueSearchBuilder city(String city) {
			venueSearch.city = city;
			return this;
		}
		public VenueSearchBuilder courtType(Number[] courtType) {
			venueSearch.courtType = courtType;
			return this;
		}
		public VenueSearchBuilder floorType(Number[] floorType) {
			venueSearch.floorType = floorType;
			return this;
		}
		public VenueSearch build() {
			return venueSearch;
		}
		
	}
}
