package RestfulApiTestingPractise;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;

import static io.restassured.matcher.ResponseAwareMatcher.*;
import static org.hamcrest.Matchers.*;

public class PutRequest {
	
	@Test
	public void test_put_1() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "nikunj");
		map.put("job", "qa taster");
		
		Gson gson = new Gson();
		
		String requestData = gson.toJson(map);
		
		System.out.println(requestData);
		
		RestAssured.given()
		.header("Content-Type", "application/json;charset=UTF-8")
		.body(requestData)
		.put("https://reqres.in/api/users/2")
	.then()
		.statusCode(200)
		.body("name", equalTo("nikunj"))
		.and()
		.body("job", equalTo("qa taster"));
	}

}
