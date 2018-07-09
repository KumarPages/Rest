package StageOne;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class TestingInternal {

	
	

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		String registerWithOnlyCustInfoPayloadDummy3 = "{\r\n" + "	\"schemas\": [\r\n"
				+ "		\"urn:hid:scim:api:sis:1.0:CustomerInfo\",\r\n"
				+ "		\"urn:hid:scim:api:sis:1.0:CustomerServicePreferences\",\r\n"
				+ "		\"urn:hid:scim:api:sis:1.0:AdminUser\"\r\n" + "	],\r\n"
				+ "	\"urn:hid:scim:api:sis:1.0:CustomerInfo\": {\r\n" + "		\"name\": \"${name}\",\r\n"
				+ "		\"address\": {\r\n" + "		\"address1\": \"${address1}\",\r\n"
				+ "		\"address2\": \"${address2}\",\r\n" + "		\"address3\": \"${address3}\",\r\n"
				+ "		\"address4\":\"${address4}\"\r\n" + "	},\r\n" + "		\"city\": \"${city}\",\r\n"
				+ "		\"state\": \"${state}\",\r\n" + "		\"country\": \"${country}\",\r\n"
				+ "		\"postalCode\": \"${postalCode}\",\r\n" + "		\"phoneNumber\": \"${phoneNumber}\",\r\n"
				+ "		\"vettingNotes\": \"${vettingNotes}\"\r\n" + "	}\r\n";
	 
		System.out.println(registerWithOnlyCustInfoPayloadDummy3);
		
	}
	
	
}
