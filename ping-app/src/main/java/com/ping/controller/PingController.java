package com.ping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ping.entity.PingSummary;
import com.ping.payload.ApiResponse;
import com.ping.service.PingingService;

@RestController
@RequestMapping("/adrian-rendon")
public class PingController {
	
	// handles creation of via com.ping.service.PingingServiceImpl.java file
	@Autowired
	private PingingService pingingService;
	
	@PostMapping("/ping")
	public ResponseEntity<?> performPing(){
		pingingService.createPing();
		return ResponseEntity.created(null).body(new ApiResponse(true, "Ping Performed!"));
	}
	
	@GetMapping("/ping/summary")
	public PingSummary getPingSummary(){
		PingSummary summary = pingingService.getPingSummary();
		return summary;
	}

}
