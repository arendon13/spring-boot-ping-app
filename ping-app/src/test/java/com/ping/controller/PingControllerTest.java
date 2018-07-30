package com.ping.controller;

import static org.junit.Assert.assertTrue;
import static org.slf4j.LoggerFactory.getLogger;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.ping.PingApp;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PingApp.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
public class PingControllerTest {

	private static final Logger logger = getLogger(PingControllerTest.class);

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Before
	@After
	public void clearData() {
		clearTestData();
	}
	
	@Test
	public void testPerformPing() throws InterruptedException, SQLException, ClassNotFoundException {
		String url = "http://localhost:" + port + "/adrian-rendon/ping";
		
		ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(String.class), String.class);
		
		logger.info("perform ping response is: " + resp.getBody());
		
		assertTrue(resp.getBody().equals("{\"success\":true,\"message\":\"Ping Performed!\"}"));
	}

	@Test
	public void testGetPingSummary() throws InterruptedException, SQLException, ClassNotFoundException {
		setUpTestData();

		String url = "http://localhost:" + port + "/adrian-rendon/ping/summary";

		ResponseEntity<String> resp = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(String.class), String.class);

		logger.info("ping summary response is: " + resp.getBody());

		assertTrue(resp.getBody().equals("{\"pingsInLastMinute\":" + 2 +",\"pingsInLastHour\":" + 3 +"}"));
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

		jdbcTemplate.update("INSERT INTO tbl_Pings (ping) VALUES (?)",
				oneMinuteFiveSecondsAgo);
		jdbcTemplate.update("INSERT INTO tbl_Pings (ping) VALUES (?)",
				tenSecondsAgo);
		jdbcTemplate.update("INSERT INTO tbl_Pings (ping) VALUES (?)",
				fiveSecondsAgo);
		logger.info("inserted test data");
	}



}
