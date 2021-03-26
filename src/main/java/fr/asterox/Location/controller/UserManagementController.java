package fr.asterox.Location.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.asterox.Location.dto.LocationDTO;
import fr.asterox.Location.proxy.UserManagementProxy;

@RestController
public class UserManagementController {
	@Autowired
	UserManagementProxy userManagementProxy;

	@RequestMapping("/getLastLocation")
	public LocationDTO getLastLocation(@RequestParam String userName) {
		return userManagementProxy.getLastLocation(userName);
	};

	@RequestMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName) {
		return userManagementProxy.getUserId(userName);
	}
}
