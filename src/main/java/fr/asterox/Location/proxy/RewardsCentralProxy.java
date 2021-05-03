package fr.asterox.Location.proxy;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "RewardsCentral", url = "localhost:9003")
public interface RewardsCentralProxy {

	@GetMapping("/getAttractionRewardPoints")
	public int getAttractionRewardPoints(@RequestParam UUID attractionId, @RequestParam UUID userId);

}
