package fr.asterox.Location.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@RequestMapping("/getNearbyAttractions")
	public List<NearbyAttractionDTO> getNearbyAttractions(@RequestParam String userName) {
		LocationDTO visitedLocation = userManagementController.getLastLocation(userName);
		UUID userId = userManagementController.getUserId(userName);
		return locationService.getFiveNearbyAttractions(visitedLocation, userId);
	}
}
