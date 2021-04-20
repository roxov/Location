package fr.asterox.Location.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.asterox.Location.controller.RewardsCentralController;
import fr.asterox.Location.controller.UserManagementController;
import gpsUtil.GpsUtil;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;

@ExtendWith(MockitoExtension.class)
public class TrackLocationServiceTest {

	@Mock
	UserManagementController userManagementController;

	@Mock
	RewardsCentralController rewardsCentralController;

	@Mock
	RewardCentral rewardsCentral;

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
		locationService.trackUserLocation("jon");

		// THEN
		verify(userManagementController, Mockito.times(1)).getUserId(anyString());
		verify(gpsUtil, Mockito.times(1)).getUserLocation(userId);
		verify(userManagementController, Mockito.times(1)).addVisitedLocation("jon", visitedLocation);
		verify(rewardsCentralController, Mockito.times(1)).calculateRewards(anyString());
	}
}
