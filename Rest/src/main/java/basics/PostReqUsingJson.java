package basics;

import groovy.json.JsonParserType;
import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostReqUsingJson {
	public static void main(String[] args) {
		
		RestAssured.baseURI= "https://dev59776.service-now.com/";
	
		BasicAuthScheme auth = new BasicAuthScheme();
		auth.setUserName("Babu");
		auth.setPassword("Tuna@123");
		RestAssured.authentication = auth;

		Response response = 	
				RestAssured
				.given()
				.headers("Content-Type","application/json")
				.body("{\"short_description\":\"TUNA\"}")
				.queryParam("sysparm_fields", "number,sys_id")
				.pathParam("type", "change_request")
				.post("api/now/table/{type}")
				.then()
				.extract().response();
				
		if(response.statusCode() == 201) {
			System.out.println("Success with requests");
		}else {
			System.out.println("Response failed with status code "+response.statusCode() + "and the response error is "+response.prettyPrint());
			throw new RuntimeException();
		}
		
		Headers headers = response.getHeaders();
		System.out.println(headers.get("Content-Type"));
		
		JsonPath jsonResponse = response.body().jsonPath();		
		String text = jsonResponse.get("result.number");
		System.out.println(text);		
		
	}
}