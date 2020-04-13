package com.schwinning.alexa.handler;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazon.speech.json.SpeechletRequestEnvelope;
import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.SpeechletV2;

@Component
public class HandlerSpeechlet implements SpeechletV2 {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private RecentScherbenHandler handler;
	
	@Override
	public void onSessionStarted(SpeechletRequestEnvelope<SessionStartedRequest> requestEnvelope) {
		// This is invoked when a new Alexa session is started. Any initialization logic
		// would go here.
		// You can store stuff in the Alexa session, for example, by calling:
//		 		Session session = requestEnvelope.getSession();
	}

	@Override
	public SpeechletResponse onLaunch(SpeechletRequestEnvelope<LaunchRequest> requestEnvelope) {
		// This is called when the skill is started with no specific intent.
		// Such as "Alexa, ask MyDemoApp."
		// When this happens, we should provide a welcome message and prompt
		// the user to ask a question.
		return null;
	}

	@Override
	public SpeechletResponse onIntent(SpeechletRequestEnvelope<IntentRequest> requestEnvelope) {
		// This is invoked whenever an intent is invoked for our application. We need
		// to figure out which intent it is, then respond.
		// Get the intent from the request.
		
		IntentRequest request = requestEnvelope.getRequest();
		Session session = requestEnvelope.getSession();
		Intent intent = request.getIntent();

		/*
		// Determine which intent this is
		String intentName = intent.getName();
		// Load a handler
		String handlerBeanName = intentName + "Handler";
		Object handlerBean = beanFactory.getBean(handlerBeanName);

		// Invoke the handler
		IntentHandler intentHandler = (IntentHandler) handlerBean;
		*/
		return handler.handleIntent(intent, request, session);
	}

	@Override
	public void onSessionEnded(SpeechletRequestEnvelope<SessionEndedRequest> requestEnvelope) {
		// This is invoked whenever session ends. We can use this to clean up if needed.

	}

}
