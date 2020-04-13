package com.schwinning.alexa.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.StandardCard;
import com.schwinning.alexa.service.TwitterService;

import twitter4j.Status;

@Component
public class RecentScherbenHandler implements IntentHandler{
	
	@Autowired
	private TwitterService twitterService;

	@Override
	public SpeechletResponse handleIntent(Intent intent, IntentRequest request, Session session) {
		// TODO Auto-generated method stub
		PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
				
		Status status = twitterService.getLatestStatusByUser("Scherben81");
		String text = status==null?"":status.getText();
		speech.setText(text);
		
		StandardCard card = new StandardCard();
		card.setTitle("Scherben");
		card.setText(text);
		
		return SpeechletResponse.newTellResponse(speech, card);
		
	}

}
