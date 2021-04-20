package fr.asterox.Location.controller;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.asterox.Location.dto.LocationDTO;
import fr.asterox.Location.dto.NearbyAttractionDTO;
import fr.asterox.Location.service.LocationService;

@RestController
public class LocationController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private UserManagementController userManagementController;

	private Logger logger = LoggerFactory.getLogger(LocationController.class);

	@GetMapping("/trackLocation")
	public String trackLocation(@RequestParam String userName) {
		logger.debug("tracking location of user :" + userName);
		return locationService.trackUserLocation(userName);
	}

	@GetMapping("/getNearbyAttractions")
	public List<NearbyAttractionDTO> getNearbyAttractions(@RequestParam String userName) {
		logger.debug("getting nearby attractions for user :" + userName);
		LocationDTO visitedLocation = userManagementController.getLastLocation(userName);
		UUID userId = userManagementController.getUserId(userName);
		return locationService.getFiveNearbyAttractions(visitedLocation, userId);
	}
}
