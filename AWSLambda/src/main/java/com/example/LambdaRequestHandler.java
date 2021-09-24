package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.stream.Collectors;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.lambda.runtime.events.S3Event;

public class LambdaRequestHandler implements RequestHandler<S3Event, String>{

	public String handleRequest(S3Event event, Context context) {
		// TODO Auto-generated method stub
		System.out.println("Input Bucket :"+ event.getRecords().get(0).getS3().getBucket().getName());
		System.out.println("Input File Added : "+event.getRecords().get(0).getS3().getObject().getKey());
		
//		context.getLogger().log("Input: "+input.toString());
		String bucketName = event.getRecords().get(0).getS3().getBucket().getName();
		String fileName = event.getRecords().get(0).getS3().getObject().getKey();
		
		AWSCredentials credentials = new BasicAWSCredentials(
				  "AKIAXPGSCZ3U6J4R4POM", 
				  "BcVRav/Gi0agALn4KzKoxyu/zRhbpFT4x0TRfHOT"
				);
		Regions region = Regions.fromName("ap-southeast-2");
		AmazonS3 s3client = AmazonS3ClientBuilder
				  .standard()
				  .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(region)
				  .build();
		
		String bucketNew = bucketName+"-new";
		s3client.createBucket(bucketNew);
		final String data = s3client.getObjectAsString(bucketName, fileName);
		System.out.println("File Content: "+ data);
		
		s3client.putObject(bucketNew, "Hello.txt", data+" Hello");
		
				return "Hello World - ";
	}

}
