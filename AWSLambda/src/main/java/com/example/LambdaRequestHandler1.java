package com.example;

import java.nio.charset.StandardCharsets;
import java.util.Map;

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

public class LambdaRequestHandler1 implements RequestHandler<Map<String,String>, String> {

	public String handleRequest(Map<String,String> input, Context context) {
		// TODO Auto-generated method stub
		System.out.println("Input payload: " + input);

		AWSCredentials credentials = new BasicAWSCredentials("AKIAXPGSCZ3U6J4R4POM",
				"BcVRav/Gi0agALn4KzKoxyu/zRhbpFT4x0TRfHOT");
		Regions region = Regions.fromName("ap-southeast-2");
		
		// Invoke another lambda function

		AWSLambdaClientBuilder builder = AWSLambdaClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region);
		AWSLambda lambdaClient = builder.build();

		System.out.println("Calling lambdaFunction2");
		InvokeRequest req = new InvokeRequest().withFunctionName("myLambdaFunction2")
				.withPayload("{\"key\":\"value\"}");

		InvokeResult result = lambdaClient.invoke(req);
		String ans = new String(result.getPayload().array(), StandardCharsets.UTF_8);

		System.out.println("Returned from myLambdaFunction2 call");
		System.out.println("Result: " + ans);
		return "myLambdaFunction2 returned payload: " + ans;
	}

}
