package fr.asterox.Location.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.asterox.Location.dto.LocationDTO;
import fr.asterox.Location.dto.NearbyAttractionDTO;
import gpsUtil.GpsUtil;
import gpsUtil.location.VisitedLocation;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {
	LocationService locationService;

	@BeforeEach
	public void setUp() {
		Locale.setDefault(Locale.US);
		GpsUtil gpsUtil = new GpsUtil();
		locationService = new LocationService(gpsUtil);
	}

	@Test
	public void givenAUser_whenTrackUserLocation_thenReturnUserIdOnVisitedLocation() {
		// GIVEN
//		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
//mocker les rep
		// WHEN
		VisitedLocation visitedLocation = locationService.trackUserLocation("user");
//		VisitedLocation visitedLocation = gpsUtil.getUserLocation(userManagementController.getUserId(userName));
//		userManagementController.addVisitedLocations(userName, visitedLocation);
//		rewardsCentralController.calculateRewards(userName);
		// THEN
		// assertTrue(visitedLocation.userId.equals(user.getUserId()));
	}

	@Test
	public void givenALocation_whenGetFiveNarbyAttractions_thenReturnAListOfFiveAttractions() {
		// GIVEN
		LocationDTO location = new LocationDTO(-120.4, 114.5);

		// WHEN
		List<NearbyAttractionDTO> attractions = locationService.getFiveNearbyAttractions(location, UUID.randomUUID());

		// THEN
		assertEquals(5, attractions.size());
	}

}
