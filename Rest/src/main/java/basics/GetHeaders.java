package basics;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetHeaders {
	public static void main(String[] args) {
			
		RestAssured.baseURI= "https://dev59776.service-now.com/api/now/table/incident";
	
		BasicAuthScheme auth = new BasicAuthScheme();
		auth.setUserName("Babu");
		auth.setPassword("Tuna@123");
		RestAssured.authentication = auth;

		Response response = 	
				RestAssured
				.given()
				.header("Content-Type", "application/json")
				.get()
				.then()
				.extract().response();
				
		if(response.statusCode() == 200) {
			System.out.println("Success with requests");
		}else {
			System.out.println("Response failed with status code: "+response.statusCode() + "and the response error is "+response.prettyPrint());
			throw new RuntimeException();
		}
		
		Headers headers = response.getHeaders();
		System.out.println(headers.get("Content-Type"));
		
		
		
		
	}
}