package com.example.sns.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AWSSnsConfiguration {

	@Primary
	@Bean
	public AmazonSNSClient amazonSNSClient() {
		return (AmazonSNSClient) AmazonSNSClientBuilder
				.standard()
				.withRegion("ap-southeast-2")
				.withCredentials(
						new AWSStaticCredentialsProvider(
								new BasicAWSCredentials("accessKey", "secretkey")))
				.build();
	}
}
