package fr.asterox.Location.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.asterox.Location.proxy.RewardsCentralProxy;

@RestController
public class RewardsCentralController {
	@Autowired
	RewardsCentralProxy rewardsCentralProxy;

	private Logger logger = LoggerFactory.getLogger(RewardsCentralController.class);

	@PutMapping("/calculateRewards")
	public void calculateRewards(@RequestParam String userName) {
		logger.debug("sending request to rewardsCentral microservice to calculate rewards of user :" + userName);
		rewardsCentralProxy.calculateRewards(userName);
	}

}
