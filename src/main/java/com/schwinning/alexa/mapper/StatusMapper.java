package com.schwinning.alexa.mapper;

import org.springframework.stereotype.Component;

import com.schwinning.alexa.model.TwitterStatus;

import twitter4j.Status;

@Component
public class StatusMapper {

	public TwitterStatus mapToTwitterStatus(Status status) {
		if (status == null) {
			return null;
		}
		return TwitterStatus.builder()
				.username(status.getUser().getScreenName())
				.message(beautifyStatusMessage(status.getText()))
				.build();
	}
	
	private String beautifyStatusMessage(String statusText) {
		return statusText;
	}
	
}
