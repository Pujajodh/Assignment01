package org.testyanrta.assignment;

import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Assignment_01 {
	/**
	 * this method id used to get the token and get records of customer
	 */
	
	@Test
	public void authenticate() {
		//  Specify base URI
		RestAssured.baseURI = "http://13.126.80.194:8080";
		//Request object
		RequestSpecification request = RestAssured.given();
		//
		JSONObject requestParams = new JSONObject();
		requestParams.put("username", "rupeek"); //
		requestParams.put("password", "password");
		request.contentType(ContentType.JSON);
		request.body(requestParams.toJSONString());
		// POST request for passing UN and PASS
		//Response Object
		Response response = request.post("/authenticate");
		 String token = response.getBody().prettyPrint();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	
		// GET request for get all the records of customer
		Response response1 = given().auth().oauth2(token).get("/api/v1/users");
		System.out.println(response1.getHeaders());
		//get response body
		System.out.println(response1.getBody().asString());
		//get response status code
		System.out.println(response1.getStatusCode());
		//get response content type
		System.out.println(response1.getContentType());
		//get time taken to get a response
		System.out.println(response1.getTime());
		//print the json response in formatted output
		System.out.println(response1.prettyPrint());
		//verification
		Assert.assertEquals(response1.getStatusCode(), 200);

		
		// GET request to get PHONE NUMBER of customers
		String phone=(String) requestParams.get("phone");
		String path="/api/v1/users"+phone;
		Response response2 = given().auth().oauth2(token).get(path);
		//verification
		Assert.assertEquals(response2.getStatusCode(), 200);
		}

	
	
}
