package com.ping.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ping.entity.Ping;
import com.ping.entity.PingSummary;
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

	@Override
	public PingSummary getPingSummary() {
		List<Ping> pings = pingRepo.findAllWithinHour();
		PingSummary summary = calcPingSummary(pings);
		
		return summary;
	}
	
	public PingSummary calcPingSummary(List<Ping> pings) {
		LocalDateTime now = dateService.getCurDateTime();
		LocalDateTime lastMinute = dateService.getLastMinuteDateTime(now);
		
		int lastMinuteCount = 0;
		int lastHourCount = 0;	
		for(Ping p: pings) {
			if(dateService.isWithinLastMinute(p.getPing(), lastMinute)) {
				lastMinuteCount++;
				lastHourCount++;
			}
			else {
				lastHourCount++;
			}
		}
		
		PingSummary summary = new PingSummary(lastMinuteCount, lastHourCount);
		
		return summary;
	}

}
