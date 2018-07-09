package basics;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ApplyProxy extends Base{
	
	@Test
	public void get(){

		RestAssured.baseURI= "https://dev59776.service-now.com/";
		BasicAuthScheme auth = new BasicAuthScheme();
		auth.setUserName("Babu");
		auth.setPassword("Tuna@123");
		RestAssured.authentication = auth;

		int port= 1234;
		Response response = RestAssured
				.given()
				.proxy("host",port)
				.get("api/now/table/incident")
				.then()
				.extract().response();
		
		
		
	}
}