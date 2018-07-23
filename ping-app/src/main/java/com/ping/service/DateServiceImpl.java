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
	
	

}
