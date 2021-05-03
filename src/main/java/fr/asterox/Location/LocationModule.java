package fr.asterox.Location;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gpsUtil.GpsUtil;

@Configuration
public class LocationModule {

	@Bean
	public GpsUtil getGpsUtil() {
		return new GpsUtil();
	}

}
