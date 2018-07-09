package StageOne;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;



public class GetRequest1 {

	@Test
	public void getRequest() {

		RestAssured.baseURI = "https://maps.googleapis.com/";

		RestAssured.given()
		.param("location", "-33.8670522,151")
		.param("radius","500")
		.param("key","AIzaSyDprkjW__L8zrkw_WrzcZnXwWYEbbgtyXs")

		.when()
		.get("maps/api/place/nearbysearch/json")

		.then()
		.assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().body("results[0].name", equalTo("Sydney1"));		
	}
}
