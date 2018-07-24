package com.ping.service;

import java.time.LocalDateTime;

public interface DateService {
	
	LocalDateTime getCurDateTime();
	
	LocalDateTime getLastMinuteDateTime(LocalDateTime initDT);
	
	boolean isWithinLastMinute(LocalDateTime compareDate, LocalDateTime lastMinute);

}
