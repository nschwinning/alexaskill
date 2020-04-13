package com.schwinning.alexa.properties;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import lombok.Data;

@Data
@Validated
@ConfigurationProperties(prefix = "twitter")
public class TwitterProperties {

	@NotBlank
	private String apikey;
	
	@NotBlank
	private String apisecret;
	
	@NotBlank
	private String token;
	
	@NotBlank
	private String tokensecret;
	
}
