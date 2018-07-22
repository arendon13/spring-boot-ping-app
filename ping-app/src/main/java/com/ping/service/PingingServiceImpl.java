package com.ping.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.entity.Ping;
import com.ping.repository.PingRepository;

@Service
public class PingingServiceImpl implements PingingService {
	
	@Autowired
	private PingRepository pingRepo;
	
	// date service is charge of all Date related actions via DateServiceImpl.java
	@Autowired
	private DateService dateService;

	@Override
	public void createPing() {
		Date dt = dateService.getCurrentDate();
		Ping ping = new Ping(dt);
		pingRepo.save(ping);
	}

}
