package basics;

import java.util.Iterator;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;
import io.restassured.response.Response;

public class PostReqUsingXml {
	public static void main(String[] args) {

		RestAssured.baseURI= "https://dev59776.service-now.com/";

		BasicAuthScheme auth = new BasicAuthScheme();
		auth.setUserName("Babu");
		auth.setPassword("Tuna@123");
		RestAssured.authentication = auth;

		String xmlBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<request>\r\n" + 
				" <entry>\r\n" + 
				"  <short_description>This is automated through REST Assured</short_description>\r\n" + 
				" </entry>\r\n" + 
				"</request>";

		System.out.println(xmlBody);


		Response response = 	
				RestAssured
				.given()
				.headers("Content-Type","application/xml")
				.header("Accept","application/xml")
				.body(xmlBody)
				.queryParam("sysparm_fields", "number,sys_id,short_description")
				.pathParam("type", "change_request")
				.post("api/now/table/{type}")
				.then()
				/*.using()
				.defaultParser(Parser.XML)	*/
				.extract().response();

		if(response.statusCode() == 201) {
			System.out.println("Success with requests");
		}else {
			System.out.println("Response failed with status code "+response.statusCode() + "and the response error is "+response.prettyPrint());
			throw new RuntimeException();
		}

		Headers headers = response.getHeaders();
		System.out.println(headers.get("Content-Type"));

		XmlPath xmlResponse = response.body().xmlPath();		
		System.out.println(xmlResponse.prettyPrint());
		Object number = xmlResponse.get("xml.result.number");
		System.out.println("out : "+number);

		

	}
}