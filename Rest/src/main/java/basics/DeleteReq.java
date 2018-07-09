package basics;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteReq {
	public static void main(String[] args) {

		RestAssured.baseURI= "https://dev59776.service-now.com/";
		String sys_id = "f855ef22db12130098fc1fc76896197d";

		BasicAuthScheme auth = new BasicAuthScheme();
		auth.setUserName("Babu");
		auth.setPassword("Tuna@123");
		RestAssured.authentication = auth;

		Response response = RestAssured
				.given()
				.headers("Content-Type","application/json")
				.delete("api/now/table/incident/"+sys_id)
				.then()
				.extract().response();

		int statusCode = response.statusCode();
		System.out.println(statusCode);

	}
}