package com.ping.service;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DateServiceImpl implements DateService {

	@Override
	public Date getCurrentDate() {
		Date dt = new Date();
		return dt;
	}

}
