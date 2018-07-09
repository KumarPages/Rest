package getRequest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequest {

	@Test
	public void postRequstForServiceNow() {
		RestAssured.baseURI = "https://dev35288.service-now.com";
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("admin");
		authScheme.setPassword("Leaf@123");
		RestAssured.authentication = authScheme;

		Response txt = 
				RestAssured.given()
				.header("Content-Type","application/json")
				.body("{\"caller_id\":\"ITIL User\"}")
				.body("{\"short_description\":\"GopinathJayakumar\"}")
				.when()
				.post("api/now/table/incident")
				.then()
				.assertThat().statusCode(201)
				.and().contentType(ContentType.JSON)
				.extract().response();
		
		String t = txt.body().asString();
		
		System.out.println(txt.body().asString());
		JsonPath js = new JsonPath(t);
		System.out.println(js.get("result[81].sys_id"));
	}
}