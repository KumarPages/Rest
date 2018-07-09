package basics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetReqParameters {
	public static void main(String[] args) {
			
		RestAssured.baseURI= "https://dev59776.service-now.com/api/now/table/incident";
	
		BasicAuthScheme auth = new BasicAuthScheme();
		auth.setUserName("Babu");
		auth.setPassword("Tuna@123");
		RestAssured.authentication = auth;
		
		Map<String, String> parametersMap = new HashMap<String, String>();
		parametersMap.put("impact", "3");

		Response response = 	
				RestAssured
				.given()
				.params(parametersMap)
				.get()
				.then()
				.statusCode(202)
				.extract().response();
				
		if(response.statusCode() == 200) {
			System.out.println("Success with requests");
		}else {
			System.out.println("Response failed with status code: "+response.statusCode() + "and the response error is "+response.prettyPrint());
			throw new RuntimeException();
		}
		//System.out.println(response.prettyPrint());
		JsonPath jsonResponse = response.body().jsonPath();
		
		List<String> numbers = jsonResponse.getList("result.sys_id");
		System.out.println("The number of result: "+ numbers.size());
		/*for (String number : numbers) {
			System.out.println(number);
		}*/
		
		
	}
}