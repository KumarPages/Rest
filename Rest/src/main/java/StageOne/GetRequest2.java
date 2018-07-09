package StageOne;




import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class GetRequest2 {
	
	@Test
	public void getRequest() {
		RestAssured.baseURI = "https://maps.googleapis.com";		
		given()
		.param("query","Theater in chennai")
		.param("key", "AIzaSyDprkjW__L8zrkw_WrzcZnXwWYEbbgtyXs")		
		.when()
		.get("maps/api/place/textsearch/json")		
		.then()
		.assertThat().statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		.and()
		.body("results[0].name", equalTo("Sathyam Cinemas"))
		.and().header("server", "scaffolding on HTTPServer2");	
	}
}
