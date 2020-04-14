package com.schwinning.alexa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.schwinning.alexa.mapper.StatusMapper;
import com.schwinning.alexa.model.TwitterStatus;
import com.schwinning.alexa.service.TwitterService;

import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;

@Slf4j
@RestController
public class TestController {

	@Autowired
	private TwitterService twitterService;
	
	@Autowired
	private StatusMapper statusMapper;
	
	@GetMapping(value = "/status/{username}")
	public ResponseEntity<String> getStatus(@PathVariable String username) {
		Status status = twitterService.getLatestStatusByUser(username);
		if (status!=null) {
			log.info(status.getText());
			//return status.getText();
			return new ResponseEntity<>(status.getText(), HttpStatus.OK);
		}
		//return null;
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/twitterstatus/{username}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<TwitterStatus> getTwitterStatus(@PathVariable String username) {
		Status status = twitterService.getLatestStatusByUser(username);
		if (status!=null) {
			log.info(status.getText());
			//return status.getText();
			return new ResponseEntity<>(statusMapper.mapToTwitterStatus(status), HttpStatus.OK);
		}
		//return null;
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
