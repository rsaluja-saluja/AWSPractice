package com.example.handler;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.spec.PutItemSpec;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.example.model.PersonRequest;
import com.example.model.PersonResponse;

public class SavePersonHandler implements RequestHandler<PersonRequest, PersonResponse> {

	private DynamoDB dynamoDb;
	private String DYNAMODB_TABLE_NAME = "Person";
	private Regions REGION = Regions.US_WEST_2;

	public PersonResponse handleRequest(PersonRequest input, Context context) {
		// TODO Auto-generated method stub
		this.initDynamoDbClient();
		System.out.println("Input: "+input);
		persistData(input);
		PersonResponse response = new PersonResponse();
		response.setMessage("Saved Successfully !!! "+input.getId()+" "+input.getFirstName());
		return response;
	}

	private PutItemOutcome persistData(PersonRequest personRequest) 
		      throws ConditionalCheckFailedException {
		        return this.dynamoDb.getTable(DYNAMODB_TABLE_NAME)
		          .putItem(
		            new PutItemSpec().withItem(new Item()
		              .withNumber("id", personRequest.getId())	
		              .withString("firstName", personRequest.getFirstName())
		              .withString("lastName", personRequest.getLastName())));
		    }

	private void initDynamoDbClient() {
		AmazonDynamoDBClient client = new AmazonDynamoDBClient();
		client.setRegion(Region.getRegion(REGION));
		this.dynamoDb = new DynamoDB(client);
	}
}
