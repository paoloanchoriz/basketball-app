package com.panchoriz.myapp.utils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ProvinceAndCitiesUtility {
	
	
	private static final Map<String, List<String>> LOCATION_MAP;
	static {
		Map<String, List<String>> locMap = new TreeMap<String,List<String>>();
		try {
			Resource resource = new ClassPathResource("/location.properties");
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			System.out.println(props.keySet());
		} catch (IOException e) {
			System.out.println("Failed to load location.properties. Need to restart");
			e.printStackTrace();
//			LOCATION_MAP = new HashMap<String, List<String>>();
		}
		LOCATION_MAP = Collections.unmodifiableMap(locMap);
	}
	
	public static Map<String, List<String>> getLocationMap() {
		return LOCATION_MAP;
	}
}
