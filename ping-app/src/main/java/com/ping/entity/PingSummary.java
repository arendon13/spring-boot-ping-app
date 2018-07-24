package com.ping.entity;

public class PingSummary {
	
	private int pingsInLastMinute;
	
	private int pingsInLastHour;
	
	public PingSummary() {}
	
	public PingSummary(int pingsInLastMinute, int pingsInLastHour) {
		this.pingsInLastMinute = pingsInLastMinute;
		this.pingsInLastHour = pingsInLastHour;
	}

	public int getPingsInLastMinute() {
		return pingsInLastMinute;
	}

	public int getPingsInLastHour() {
		return pingsInLastHour;
	}

}
