package basics;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.http.Headers;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JsonValidation {
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
				.body(JsonSchemaValidator.matchesJsonSchema(new File("./data/incident.json")).toString())
				.post("api/now/table/incident")
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