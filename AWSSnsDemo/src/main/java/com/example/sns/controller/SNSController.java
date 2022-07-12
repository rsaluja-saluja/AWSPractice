package com.example.sns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@RestController
public class SNSController {

	@Autowired
	AmazonSNSClient amazonSNSClient;
	
	private String TOPIC_ARN = "arn:aws:sns:ap-southeast-2:513689177833:my-sns-topic";
	
	@GetMapping("/test")
	public String test() {
		return "Hello message";
	}
	@GetMapping("/subscribe/{email}")
	public String subscribeToSNSTopic(@PathVariable String email) {
		SubscribeRequest subscribeRequest = 
				new SubscribeRequest(TOPIC_ARN, "email", email);
		
		amazonSNSClient.subscribe(subscribeRequest);
		return "Check your email";
	}
	
	@GetMapping("/publish/{message}")
	public String publishtoTopic(@PathVariable String message) {
		PublishRequest publishRequest = 
				new PublishRequest(TOPIC_ARN,message);
		amazonSNSClient.publish(publishRequest);
		return "Message published";
	}
}
