package com.example;


import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaRequestHandler2 implements RequestHandler<Map<String,String>, String>{

	public String handleRequest(Map<String,String> input, Context context) {
		// TODO Auto-generated method stub
		System.out.println("Input payload received "+input.toString());
		return "{\"key_new\":\"value_new\"}";
	}
	

}
