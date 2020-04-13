package com.schwinning.alexa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.schwinning.alexa.service.TwitterService;

import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;

@Slf4j
@RestController
public class TestController {

	@Autowired
	private TwitterService twitterService;
	
	@GetMapping(value = "/status", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Status> getStatus() {
		Status status = twitterService.getLatestStatusByUser("Scherben81");
		if (status!=null)
			log.info(status.getText());
		return new ResponseEntity<>(status, HttpStatus.OK);
	}
	
}
