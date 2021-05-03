package fr.asterox.Location.service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.asterox.Location.dto.LocationDTO;
import fr.asterox.Location.dto.NearbyAttractionDTO;
import fr.asterox.Location.proxy.RewardsCentralProxy;
import fr.asterox.Location.proxy.UserManagementProxy;
import gpsUtil.GpsUtil;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@Service
public class LocationService implements ILocationService {
	@Autowired
	private GpsUtil gpsUtil;

	@Autowired
	private RewardsCentralProxy rewardsCentralProxy;

	@Autowired
	private UserManagementProxy userManagementProxy;

	private Logger logger = LoggerFactory.getLogger(LocationService.class);
	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

	public LocationService() {
		super();
	}

	@Override
	public String trackUserLocation(String userName) {
		Thread t1 = new Thread(() -> this.calculateUserLocation(userName));
		t1.start();
		return "The calculation of your location is on process";
	}

	@Override
	public void calculateUserLocation(String userName) {
		VisitedLocation visitedLocation = gpsUtil.getUserLocation(userManagementProxy.getUserId(userName));
		logger.debug("adding visited location to user :" + userName);
		userManagementProxy.addVisitedLocation(userName, visitedLocation);
	}

	@Override
	public List<NearbyAttractionDTO> getFiveNearbyAttractions(LocationDTO visitedLocation, UUID userId) {
		Comparator<Location> distanceComparator = Comparator
				.comparing(location -> this.getDistance(visitedLocation, location));
		logger.debug("getting five nearest attractions for user with userId :" + userId);
		return gpsUtil.getAttractions().stream().sorted(distanceComparator).limit(5)
				.map(attraction -> new NearbyAttractionDTO(attraction.attractionName, attraction.latitude,
						attraction.longitude, visitedLocation.latitude, visitedLocation.longitude,
						this.getDistance(visitedLocation, attraction),
						rewardsCentralProxy.getAttractionRewardPoints(attraction.attractionId, userId)))
				.collect(Collectors.toList());
	}

	private double getDistance(LocationDTO loc1, Location loc2) {
		double lat1 = Math.toRadians(loc1.latitude);
		double lon1 = Math.toRadians(loc1.longitude);
		double lat2 = Math.toRadians(loc2.latitude);
		double lon2 = Math.toRadians(loc2.longitude);

		double angle = Math
				.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

		double nauticalMiles = 60 * Math.toDegrees(angle);
		double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
		return statuteMiles;
	}

}
