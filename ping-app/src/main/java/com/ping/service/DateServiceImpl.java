package com.ping.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
public class DateServiceImpl implements DateService {

	@Override
	public LocalDateTime getCurDateTime() {
		LocalDateTime dt = LocalDateTime.now();
		return dt;
	}

	@Override
	public LocalDateTime getLastMinuteDateTime(LocalDateTime initDT) {
		LocalDateTime lastMinute = initDT.minusMinutes(1);
		return lastMinute;
	}

	@Override
	public boolean isWithinLastMinute(LocalDateTime compareDate, LocalDateTime lastMinute) {
		boolean isWithinLastMinute = (compareDate.isAfter(lastMinute)) ? true : false;
		return isWithinLastMinute;
	}
	
	

}
