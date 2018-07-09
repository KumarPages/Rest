package basics;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PatchReq {
	public static void main(String[] args) {
		
		RestAssured.baseURI= "https://dev59776.service-now.com/";
		String sys_id = "cb68fe31db12530098fc1fc76896192d";
	
		BasicAuthScheme auth = new BasicAuthScheme();
		auth.setUserName("Babu");
		auth.setPassword("Tuna@123");
		RestAssured.authentication = auth;

		Response response = RestAssured
				.given()
				.headers("Content-Type","application/json")
				.body("{\"short_description\":\"TUNA Automation\"}")
				.queryParam("sysparm_fields", "number,short_description")
				.patch("api/now/table/incident/"+sys_id)
				.then()
				.extract().response();
				
		
		JsonPath jsonResponse = response.body().jsonPath();		
		String text = jsonResponse.get("result.short_description");
		System.out.println(text);
		
		
	}
}