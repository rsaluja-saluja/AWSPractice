package com.example.kms;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.kms.service.KMSOperations;

import software.amazon.awssdk.core.SdkBytes;

@SpringBootApplication
public class AwskmsDemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AwskmsDemoApplication.class, args);
	}

	private static final Logger logger = LoggerFactory.getLogger(AwskmsDemoApplication.class);

	@Autowired
	KMSOperations kmsOperations;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		String jsonDataUnder4K = "{" + "   \"data\":[" + "      {" + "         \"type\":\"articles\","
				+ "         \"id\":\"1\"," + "         \"attributes\":{"
				+ "            \"title\":\"JSON:API paints my bikeshed!\","
				+ "            \"body\":\"The shortest article. Ever.\","
				+ "            \"created\":\"2015-05-22T14:56:29.000Z\","
				+ "            \"updated\":\"2015-05-22T14:56:28.000Z\"" + "         },"
				+ "         \"relationships\":{" + "            \"author\":{" + "               \"data\":{"
				+ "                  \"id\":\"42\"," + "                  \"type\":\"people\"" + "               }"
				+ "            }" + "         }" + "      }," + "      {" + "         \"type\":\"articles\","
				+ "         \"id\":\"1\"," + "         \"attributes\":{"
				+ "            \"title\":\"JSON:API paints my bikeshed!\","
				+ "            \"body\":\"The shortest article. Ever.\","
				+ "            \"created\":\"2015-05-22T14:56:29.000Z\","
				+ "            \"updated\":\"2015-05-22T14:56:28.000Z\"" + "         },"
				+ "         \"relationships\":{" + "            \"author\":{" + "               \"data\":{"
				+ "                  \"id\":\"42\"," + "                  \"type\":\"people\"" + "               }"
				+ "            }" + "         }" + "      }" + "   ]" + "}";

		// describe key option
		kmsOperations.describeSpecifcKey();

		System.out.println("the json string is" + jsonDataUnder4K);
		System.out.println("the size of the string " + jsonDataUnder4K.getBytes().length);

//		System.out.println("the json string is" + jsonDataAbove4k);
//		System.out.println("the size of the string " + jsonDataAbove4k.getBytes().length);

		// the following will fail as we are using the encrypt function with data more
		// than 4KB
		// kmsOperations.encrypt(SdkBytes.fromUtf8String(jsonDataAbove4k));

		// calling the KMS APIs for encryption and decryption
		SdkBytes encryptedData = kmsOperations.encrypt(SdkBytes.fromUtf8String(jsonDataUnder4K));

		logger.info("the response with the encrypted ciphertext as sdkbytes data: {} ", encryptedData);

		SdkBytes decryptedData = kmsOperations.decrypt(encryptedData);
		String plainText = decryptedData.asUtf8String();

		logger.info("the response with the decrypted plaintext: {} ", plainText);
		logger.info("the response with the decrypted plaintext of length: {} ", plainText.getBytes().length);

		System.out.println("With Data Key:");
		// calling the KMS operation using the data key for the data more than 4 KB
		kmsOperations.encryptUsingDataKey(SdkBytes.fromUtf8String(jsonDataUnder4K));
		kmsOperations.decryptUsingDataKey();
	}

}
