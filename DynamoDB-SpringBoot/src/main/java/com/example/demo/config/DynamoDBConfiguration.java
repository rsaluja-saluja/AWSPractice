package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfiguration {

	@Bean
	public DynamoDBMapper dynamoDBMapper() {
		return new DynamoDBMapper(buildAmazonDynamoDB());
	}

	private AmazonDynamoDB buildAmazonDynamoDB() {
		// TODO Auto-generated method stub
		return AmazonDynamoDBClientBuilder
				.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
												"dynamodb.us-west-2.amazonaws.com",
												"us-west-2"))
				.withCredentials(
						new AWSStaticCredentialsProvider(
								new BasicAWSCredentials(
										"accesskey", 
										"secretKey")))
				.build();
	}
}
