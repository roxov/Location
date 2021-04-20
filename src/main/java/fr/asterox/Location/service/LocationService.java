package fr.asterox.Location.service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.asterox.Location.controller.RewardsCentralController;
import fr.asterox.Location.controller.UserManagementController;
import fr.asterox.Location.dto.LocationDTO;
import fr.asterox.Location.dto.NearbyAttractionDTO;
import gpsUtil.GpsUtil;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;

@Service
public class LocationService implements ILocationService {
	@Autowired
	private GpsUtil gpsUtil;

	@Autowired
	private RewardCentral rewardsCentral;

	@Autowired
	private UserManagementController userManagementController;

	@Autowired
	private RewardsCentralController rewardsCentralController;

	private Logger logger = LoggerFactory.getLogger(LocationService.class);
	private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

	public LocationService() {
		super();
	}

	public VisitedLocation trackUserThreadMethod(String userName) throws InterruptedException, ExecutionException {
		// A mettre dans le main ou juste remplacer trackUserLocation ci-dessous ?
		// Faut-il éclater les méthodes (controller appelés) dans le service ?
		Callable<VisitedLocation> visitedLocationOfUser = () -> gpsUtil
				.getUserLocation(userManagementController.getUserId(userName));
		FutureTask<VisitedLocation> getVisitedLocationOfUser = new FutureTask<>(visitedLocationOfUser);

		Thread t1 = new Thread(getVisitedLocationOfUser);
		Thread t2 = new Thread(() -> rewardsCentralController.calculateRewards(userName));

		t1.start();
		t2.start();

		return getVisitedLocationOfUser.get();
	}

	@Override
	public VisitedLocation trackUserLocation(String userName) {
		VisitedLocation visitedLocation = gpsUtil.getUserLocation(userManagementController.getUserId(userName));
		logger.debug("adding visited location to user :" + userName);
		userManagementController.addVisitedLocation(userName, visitedLocation);
		logger.debug("calculating rewards for user :" + userName);
		rewardsCentralController.calculateRewards(userName);
		return visitedLocation;
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
						rewardsCentral.getAttractionRewardPoints(attraction.attractionId, userId)))
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
