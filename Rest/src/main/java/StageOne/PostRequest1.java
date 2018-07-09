package StageOne;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class PostRequest1 {

	@Test
	public void postRequest() {
		RestAssured.baseURI = "https://maps.googleapis.com";

		given()
		.queryParam("key", "AIzaSyDprkjW__L8zrkw_WrzcZnXwWYEbbgtyXs")

		.when()
		.body("{"+
				"\"location\": {"+
				"\"lat\" :13.0553683,"+
				"\"lng\" :80.25796679999999"+
				"},"+

				"\"location\""+

				"}");

	}

}
