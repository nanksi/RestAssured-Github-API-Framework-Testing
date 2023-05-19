package utility;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class commonresFunction {

	public static JsonPath jsonpath;
	
	public static String getResponseKeyvalue(String response_body, String response_key) {
		jsonpath = new JsonPath(response_body);
		String key_value = jsonpath.get(response_key);
		return key_value;
	}
	
	public static int getResponseKeyvalueint(String response_body, String response_key) {
		jsonpath = new JsonPath(response_body);
		int key_value = jsonpath.get(response_key);
		return key_value;
	}
	
	public static int getstatuscode(Response response) {
		int status_code = response.getStatusCode();
		return status_code;
	}
}
