package fr.asterox.Location.proxy;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.asterox.Location.dto.LocationDTO;

@FeignClient(name = "UserManagement", url = "localhost:9001")
public interface UserManagementProxy {

	@RequestMapping("/getLastLocation")
	public LocationDTO getLastLocation(@RequestParam String userName);

	@RequestMapping("/getUserId")
	public UUID getUserId(@RequestParam String userName);
}
