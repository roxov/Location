package fr.asterox.Location.service;

import org.springframework.beans.factory.annotation.Autowired;

import fr.asterox.Location.proxy.RewardsCentralProxy;

public class CalculateRewards implements Runnable {
	@Autowired
	RewardsCentralProxy rewardsCentralProxy;

	String userName;

	public CalculateRewards(String userName) {
		super();
		this.userName = userName;
	}

	@Override
	public void run() {
		rewardsCentralProxy.calculateRewards(userName);
	}
}
