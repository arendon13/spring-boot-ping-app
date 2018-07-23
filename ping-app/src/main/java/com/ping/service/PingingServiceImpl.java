package com.ping.service;

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
		Ping ping = new Ping(dateService.getCurDateTime());
		pingRepo.save(ping);
	}

}
