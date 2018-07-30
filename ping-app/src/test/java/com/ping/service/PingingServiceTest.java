package com.ping.service;

import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.ping.PingApp;
import com.ping.entity.Ping;
import com.ping.entity.PingSummary;
import com.ping.repository.PingRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PingApp.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PingingServiceTest {
	
	private static final Logger logger = getLogger(PingingServiceTest.class);
	
	@Autowired
	private PingingService pingingService;
	
	@Autowired
	private PingRepository pingRepo;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Before
	@After
	public void clearData() {
		clearTestData();
	}
	
	
	@Test
	public void testCreatePing() {
		pingingService.createPing();
		logger.info("Ping created");
		
		List<Ping> pings = selectAll();
		logger.info("Number of pings received from select: {}",pings.size());
		logger.info("Ping received from DB: ({},{})",pings.get(0).getId(),pings.get(0).getPing());
		
		assertTrue(pings.size() == 1);
	}
	
	@Test
	public void testPingsWithinHour() {
		setUpTestData();
		
		List<Ping> pings = pingRepo.findAllWithinHour();
		logger.info("Pings returned from ping repository: {}",pings.size());
		assertTrue(pings.size() == 3);
	}
	
	@Test public void testCalcPingSummary() {
		setUpTestData();
		
		PingSummary summary = pingingService.getPingSummary();
		logger.info("Calculated pings in last minute: {}",summary.getPingsInLastMinute());
		logger.info("Calculated pings in last hour: {}",summary.getPingsInLastHour());
		PingSummary expected = new PingSummary(2,3);
		
		assertTrue(summary.getPingsInLastMinute() == expected.getPingsInLastMinute());
		assertTrue(summary.getPingsInLastHour() == expected.getPingsInLastHour());
	}
	
	public void clearTestData() {
		jdbcTemplate.execute("DROP TABLE IF EXISTS tbl_Pings");
		jdbcTemplate.execute("CREATE TABLE tbl_Pings(" +
				"id int AUTO_INCREMENT, ping datetime, UNIQUE KEY id (id))");
		logger.info("cleared data");
	}

	public void setUpTestData() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime fiveSecondsAgo = now.minusSeconds(5);
		LocalDateTime tenSecondsAgo = now.minusSeconds(10);
		LocalDateTime oneMinuteFiveSecondsAgo = now.minusSeconds(5).minusMinutes(1);
		LocalDateTime oneHourOneMinuteAgo = now.minusHours(1).minusMinutes(1);
		
		jdbcTemplate.update("INSERT INTO tbl_Pings (ping) VALUES (?)",
				oneHourOneMinuteAgo);
		jdbcTemplate.update("INSERT INTO tbl_Pings (ping) VALUES (?)",
				oneMinuteFiveSecondsAgo);
		jdbcTemplate.update("INSERT INTO tbl_Pings (ping) VALUES (?)",
				tenSecondsAgo);
		jdbcTemplate.update("INSERT INTO tbl_Pings (ping) VALUES (?)",
				fiveSecondsAgo);
		logger.info("inserted test data");
	}
	
	public List<Ping> selectAll(){
		String sql = "SELECT * FROM tbl_Pings";
		List<Ping> pings = new ArrayList<>();
		
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		for(Map<?, ?> row: rows) {
			Ping ping = new Ping();
			ping.setId((Integer)row.get("id"));
			Timestamp ts = (Timestamp)row.get("ping");
			ping.setPing(ts.toLocalDateTime());
			pings.add(ping);
		}
		
		return pings;
	}

}
