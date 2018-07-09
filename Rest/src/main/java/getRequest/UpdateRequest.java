package getRequest;


import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class UpdateRequest {

	@Test
	public void updateRequest() {

		RestAssured.baseURI= "https://dev35288.service-now.com";

		PreemptiveBasicAuthScheme auth = new PreemptiveBasicAuthScheme();
		auth.setUserName("admin");
		auth.setPassword("Leaf@123");
		RestAssured.authentication = auth;

		Response response = RestAssured.given()
				.param("sysparm_query", "number%3DINC0010032")
				.when()
				.get("api/now/table/incident")
				.then()
				.assertThat().statusCode(200)
		//		.and().contentType(ContentType.JSON)
				.extract().response();
		
		String  txt = response.body().asString();
		
		JsonPath js =new JsonPath(txt);
		String num = js.get("result[80].sys_id");
		
		System.out.println(num);
		
		Response bd = RestAssured.given()
	//	.header("Content-Type","application/json")
		//.body("{\"short_description\":\"Gaya3\"}")
		.body("{\"impact\":\"1\"}")
		.when()
		.put("api/now/table/incident"+num)
		;
		
		String abd = bd.asString();
		System.out.println(abd);
		
		
		
		
		

		


	}

}
