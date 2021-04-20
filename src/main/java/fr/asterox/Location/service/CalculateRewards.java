package fr.asterox.Location.service;

import org.springframework.beans.factory.annotation.Autowired;

import fr.asterox.Location.controller.RewardsCentralController;

public class CalculateRewards implements Runnable {
	@Autowired
	RewardsCentralController rewardsCentralController;

	String userName;

	public CalculateRewards(String userName) {
		super();
		this.userName = userName;
	}

	@Override
	public void run() {
		rewardsCentralController.calculateRewards(userName);
	}
}
