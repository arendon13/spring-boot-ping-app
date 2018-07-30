package com.ping.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.ping.PingApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PingApp.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class DateServiceTest {
	
	private static final Logger logger = getLogger(DateServiceTest.class);
	
	@Autowired
	private DateService dateService;
	
	@Test
	public void testGetLastMinute() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime oneMinuteAgo = now.minusMinutes(1);
		
		logger.info("Date Service {} = Test {}", dateService.getLastMinuteDateTime(now), oneMinuteAgo);
		assertTrue(dateService.getLastMinuteDateTime(now).equals(oneMinuteAgo));
	}
	
	@Test
	public void testIsWithinMinute() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime oneMinuteAgo = now.minusMinutes(1);
		LocalDateTime fiveSecondsAgo = now.minusSeconds(5);
		LocalDateTime oneMinuteFiveSecondsAgo = now.minusSeconds(5).minusMinutes(1);
		
		logger.info("Is {} within last minute? {}", fiveSecondsAgo, dateService.isWithinLastMinute(fiveSecondsAgo, oneMinuteAgo));
		assertTrue(dateService.isWithinLastMinute(fiveSecondsAgo, oneMinuteAgo));
		logger.info("Is {} within last minute? {}", oneMinuteFiveSecondsAgo, dateService.isWithinLastMinute(oneMinuteFiveSecondsAgo, oneMinuteAgo));
		assertFalse(dateService.isWithinLastMinute(oneMinuteFiveSecondsAgo, oneMinuteAgo));
	}

}
