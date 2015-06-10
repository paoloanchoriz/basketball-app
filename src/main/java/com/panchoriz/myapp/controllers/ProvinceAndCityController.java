package com.panchoriz.myapp.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/locationsMap")
public class ProvinceAndCityController {
	
	
	private static final Map<String, List<String>> LOCATION_MAP;
	static {
		Map<String, List<String>> locMap = new TreeMap<String,List<String>>();
		try {
			Resource resource = new ClassPathResource("/location.properties");
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			Set<Object> propKeys = props.keySet();
			for(Object prop:propKeys) {
				String key = (String) prop;
				String provinceCityPair = props.getProperty(key);
				if(StringUtils.isNotBlank(provinceCityPair)) {
					String[] provCityPairArray = provinceCityPair.split("=");
					String province = provCityPairArray[0];
					List<String> cityList = getCities(provCityPairArray[1]);
					locMap.put(province, cityList);
				}
			}
		} catch (IOException e) {
			System.out.println("Failed to load location.properties. Need to restart");
			e.printStackTrace();
		}
		LOCATION_MAP = Collections.unmodifiableMap(locMap);
	}
	
	private static Map<String, List<String>> getConstantLocationMap() {
		return LOCATION_MAP;
	}

	private static List<String> getCities(String citiesString) {
		List<String> cities = new ArrayList<String>();
		String[] citiesArray = citiesString.split(",");
		if(citiesArray.length > 0) {
			for(String city:citiesArray) {
				cities.add(city);
			}
			Collections.sort(cities);
		}
		return cities;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, List<String>> getLocationMap() {
		return getConstantLocationMap();
	}
}
