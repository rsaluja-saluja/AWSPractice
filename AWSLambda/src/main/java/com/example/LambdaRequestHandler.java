package com.example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaRequestHandler implements RequestHandler<String, String>{

	public String handleRequest(String input, Context context) {
		// TODO Auto-generated method stub
		context.getLogger().log("Input: "+input);
		return "Hello World - "+ input;
	}

}
