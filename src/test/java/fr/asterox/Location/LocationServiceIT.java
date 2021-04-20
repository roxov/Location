package fr.asterox.Location;

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
import fr.asterox.Location.service.LocationService;
import gpsUtil.GpsUtil;

@ExtendWith(MockitoExtension.class)
public class LocationServiceIT {

	LocationService locationService;

	@BeforeEach
	public void setUp() {
		Locale.setDefault(Locale.US);
		GpsUtil gpsUtil = new GpsUtil();
		locationService = new LocationService();
	}

	@Test
	public void givenAUser_whenTrackUserLocation_thenReturnUserIdOnVisitedLocation() {
		// GIVEN
//		UUID userId = UUID.randomUUID();
//		VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(14.4, 25.5), new Date());
//		when(gpsUtil.getUserLocation(userId)).thenReturn(visitedLocation);
//		when(userManagementController.getUserId("jon")).thenReturn(userId);
//		doNothing().when(userManagementController).addVisitedLocation("jon", visitedLocation);
//		doNothing().when(rewardsCentralController).calculateRewards("jon");
//
//		// WHEN
//		VisitedLocation result = locationService.trackUserLocation("jon");
////		VisitedLocation visitedLocation = gpsUtil.getUserLocation(userManagementController.getUserId(userName));
////		userManagementController.addVisitedLocations(userName, visitedLocation);
////		rewardsCentralController.calculateRewards(userName);
//		// THEN
//		assertTrue(visitedLocation.equals(result));
	}

	@Test
	public void givenALocation_whenGetFiveNearbyAttractions_thenReturnAListOfFiveAttractions() {
		// GIVEN
		LocationDTO location = new LocationDTO(-120.4, 114.5);

		// WHEN
		List<NearbyAttractionDTO> attractions = locationService.getFiveNearbyAttractions(location, UUID.randomUUID());

		// THEN
		assertEquals(5, attractions.size());
	}

}
