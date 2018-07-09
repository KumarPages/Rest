package getRequest;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI = "https://dev35288.service-now.com";
		
		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName("admin");
		authScheme.setPassword("Leaf@123");
		RestAssured.authentication = authScheme;
		
		Response txt = 	RestAssured.given()
		.param("sysparm_query", "number=INC0010053")
		.param("sysparm_limit", "1")
		.when()
		.get("api/now/table/incident")
		.then()
		.assertThat().statusCode(200)
		.and().contentType(ContentType.JSON)
		.and().body("result[0].number", equalTo("INC0000001"))
		.and().header("server", "ServiceNow")
		.extract().response();
		
		String x = txt.asString();	
	//	System.out.println(x);
		JsonPath js =new JsonPath(x);
		String p = js.get("result[0].number");
		System.out.println(p);
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("number", ""+js.get("result[0].number"));
		map.put("sys_updated_on",""+js.get("result[0].sys_updated_on"));
		map.put("Short Description",""+js.get("result[0].short_description"));
		map.put("Caller",""+js.get("result[0].caller_id"));
		map.put("Priority",""+js.get("result[0].priority"));
		map.put("state",""+js.get("result[0].state"));
		map.put("assignment_group",""+js.get("result[0].assignment_group"));
		map.put("category",""+js.get("result[0].category"));
		map.put("assigned_to",""+js.get("result[0].assigned_to"));
		map.put("sys_updated_by",""+js.get("result[0].sys_updated_by"));
		map.put("sys_updated_on",""+js.get("result[0].sys_updated_on"));
		
		for (Entry<String, String> eachValue :map.entrySet()) {
			System.out.println(eachValue.getKey()+" : "+eachValue.getValue());
		}
			
		
		
		
	}

}
