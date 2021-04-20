package fr.asterox.Location.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fr.asterox.Location.dto.LocationDTO;
import fr.asterox.Location.dto.NearbyAttractionDTO;
import gpsUtil.GpsUtil;
import rewardCentral.RewardCentral;

@SpringBootTest
public class LocationServiceTest {
	@Autowired
	LocationService locationService;

	@Autowired
	GpsUtil gpsUtil;

	@Autowired
	RewardCentral rewardsCentral;

	@Test
	public void givenALocation_whenGetFiveNearbyAttractions_thenReturnAListOfFiveAttractions() {
		// GIVEN
		UUID userId = UUID.fromString("333e4bf3-ee62-4a67-b7d7-b0dc06989c6e");
		LocationDTO location = new LocationDTO(-120.4, 114.5);

		// WHEN
		List<NearbyAttractionDTO> attractions = locationService.getFiveNearbyAttractions(location, userId);

		// THEN
		assertEquals(5, attractions.size());
	}

}
