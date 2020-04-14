package com.schwinning.alexa.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.schwinning.alexa.properties.TwitterProperties;

import lombok.extern.slf4j.Slf4j;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

@Slf4j
@Service
@EnableConfigurationProperties(TwitterProperties.class)
public class TwitterService {
	
	private Twitter twitter;
	
	@Autowired
	private TwitterProperties properties;
	
	@PostConstruct
	public void init() {
		if (twitter==null) {
			String apiKey = properties.getApikey();
	        String apiSecret = properties.getApisecret();
	        String token = properties.getToken();
	        String tokenSecret = properties.getTokensecret();
	        
	        twitter = TwitterFactory.getSingleton();
	        twitter.setOAuthConsumer(apiKey, apiSecret);
	        AccessToken accessToken = new AccessToken(token, tokenSecret);
	        twitter.setOAuthAccessToken(accessToken);
		}
                
        try {
			log.info("Connected to twitter account " + twitter.getScreenName());
		} catch (IllegalStateException | TwitterException e) {
			throw new IllegalStateException("Could not connect to twitter account");
		}
	}
	
	public List<Status> findTweetsByUser(String username){
    	List<Status> statusList = new ArrayList<>();
		try {
			for (Status s:twitter.getUserTimeline(username)) {
				if (!s.isRetweet()) {
					statusList.add(s);
				}
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statusList;
    }
	
	public Status getLatestStatusByUser(String username){
		return findTweetsByUser(username).size()>0?findTweetsByUser(username).get(0):null;
	}
	
}
