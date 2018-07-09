package basics;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAndDelete {
	public static void main(String[] args) {

		RestAssured.baseURI= "https://dev59776.service-now.com/";
		BasicAuthScheme auth = new BasicAuthScheme();
		auth.setUserName("Babu");
		auth.setPassword("Tuna@123");
		RestAssured.authentication = auth;
		
		Response response = RestAssured
				.get("api/now/table/incident")
				.then()
				.extract().response();
		JsonPath jsonResponse = response.body().jsonPath();
		
		List<String> numbers = jsonResponse.getList("result.sys_id");
		
		response = RestAssured
				.given()
				.headers("Content-Type","application/json")
				.delete("api/now/table/incident/"+numbers.get(0))
				.then()
				.extract().response();

		int statusCode = response.statusCode();
		System.out.println(statusCode);

	}
}