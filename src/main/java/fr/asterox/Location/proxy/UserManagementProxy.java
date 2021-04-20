package fr.asterox.Location.proxy;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import fr.asterox.Location.dto.LocationDTO;
import gpsUtil.location.VisitedLocation;

@FeignClient(name = "UserManagement", url = "localhost:9001")
public interface UserManagementProxy {

	@GetMapping("/getLastLocation")
	public LocationDTO getLastLocation(@RequestParam String userName);

	@GetMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName);

	@PutMapping("/addVisitedLocation")
	public void addVisitedLocation(@RequestParam String userName, @RequestBody VisitedLocation visitedLocation);
}
