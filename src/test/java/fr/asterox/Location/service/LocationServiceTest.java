package fr.asterox.Location.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.asterox.Location.controller.RewardsCentralController;
import fr.asterox.Location.controller.UserManagementController;
import gpsUtil.GpsUtil;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

	@Mock
	UserManagementController userManagementController;

	@Mock
	RewardsCentralController rewardsCentralController;

	@Mock
	GpsUtil gpsUtil;

	@InjectMocks
	LocationService locationService;

	@BeforeEach
	public void setUp() {
		Locale.setDefault(Locale.US);
	}

	@Test
	public void givenAUser_whenTrackUserLocation_thenReturnUserIdOnVisitedLocation() {
		// GIVEN
		UUID userId = UUID.randomUUID();
		VisitedLocation visitedLocation = new VisitedLocation(userId, new Location(14.4, 25.5), new Date());
		when(userManagementController.getUserId("jon")).thenReturn(userId);
		when(gpsUtil.getUserLocation(userId)).thenReturn(visitedLocation);

		doNothing().when(userManagementController).addVisitedLocation("jon", visitedLocation);
		doNothing().when(rewardsCentralController).calculateRewards("jon");

		// WHEN
		VisitedLocation result = locationService.trackUserLocation("jon");

		// THEN
		assertEquals(visitedLocation, result);
	}

//	@Test
//	public void givenALocation_whenGetFiveNearbyAttractions_thenReturnAListOfFiveAttractions() {
//		// GIVEN
//		LocationDTO location = new LocationDTO(-120.4, 114.5);
//
//		// WHEN
//		List<NearbyAttractionDTO> attractions = locationService.getFiveNearbyAttractions(location, UUID.randomUUID());
//
//		// THEN
//		assertEquals(5, attractions.size());
//	}

}
