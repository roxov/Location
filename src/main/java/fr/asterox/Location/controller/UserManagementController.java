package fr.asterox.Location.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.asterox.Location.dto.LocationDTO;
import fr.asterox.Location.proxy.UserManagementProxy;
import gpsUtil.location.VisitedLocation;

@RestController
public class UserManagementController {
	@Autowired
	UserManagementProxy userManagementProxy;

	private Logger logger = LoggerFactory.getLogger(UserManagementController.class);

	@GetMapping("/getLastLocation")
	public LocationDTO getLastLocation(@RequestParam String userName) {
		logger.debug("sending request to UserManagement microservice to get last location of user :" + userName);
		return userManagementProxy.getLastLocation(userName);
	};

	@GetMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName) {
		logger.debug("sending request to UserManagement microservice to get userId of user :" + userName);
		return userManagementProxy.getUserId(userName);
	}

	@PutMapping("/addVisitedLocation")
	public void addVisitedLocation(@RequestParam String userName, @RequestBody VisitedLocation visitedLocation) {
		logger.debug("sending request to UserManagement microservice to add visited location to user :" + userName);
		userManagementProxy.addVisitedLocation(userName, visitedLocation);
	}
}
