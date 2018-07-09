package basics;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;

public class ApplyFilter extends Base{

	@Test
	public void get(){

		RestAssured.baseURI= "https://dev59776.service-now.com/";
		BasicAuthScheme auth = new BasicAuthScheme();
		auth.setUserName("Babu");
		auth.setPassword("Tuna@123");
		RestAssured.authentication = auth;
		SessionFilter sessionFilter = new SessionFilter();
		System.out.println("The session "+sessionFilter.getSessionId());
		RestAssured.filters(sessionFilter);
		System.out.println("The session "+sessionFilter.getSessionId());

		Response response = RestAssured
				.given()
				.filter(sessionFilter)
				.get("api/now/table/incident")
				.then()
				.extract().response();

		System.out.println("The session "+sessionFilter.getSessionId());

		Map<String, String> cookies = response.getCookies();
		System.out.println(cookies);
		JSESSIONID = cookies.get("JSESSIONID");
		System.out.println(JSESSIONID);
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