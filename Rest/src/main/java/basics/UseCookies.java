package basics;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UseCookies extends Base{
	
	@Test
	public void set(){

		RestAssured.baseURI= "https://dev59776.service-now.com/";
		Cookie sessionCookies = new Cookie.Builder("JESSIONID", JSESSIONID)
				.setSecured(true)
				.setComment("Session Id to avoid authentication")
				.build();

		Response response = 
				RestAssured
				.given()
				.log().all()
				.cookie(sessionCookies)
				.get("api/now/table/incident")
				.then()
				.extract().response();
		
		System.out.println(response.getStatusLine());
		
		
		/*JsonPath jsonResponse = response.body().jsonPath();
		
		List<String> numbers = jsonResponse.getList("result.sys_id");
		
		response = RestAssured
				.given()
				.headers("Content-Type","application/json")
				.delete("api/now/table/incident/"+numbers.get(0))
				.then()
				.extract().response();

		int statusCode = response.statusCode();
		System.out.println(statusCode);*/
		
	}
}