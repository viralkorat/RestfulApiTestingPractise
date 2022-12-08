package RestfulApiTestingPractise;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;

import static io.restassured.matcher.ResponseAwareMatcher.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class PostRequest { 
	
	@Parameters({"names", "role"})
	@Test
	public void test_post_1(String names, String role) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", names);
		map.put("job", role);
		
		Gson gson = new Gson();
		
		String requestData = gson.toJson(map);
		
		System.out.println(requestData);
		
		RestAssured.given()
		.header("Content-Type", "application/json;charset=UTF-8")
		.body(requestData)
		.post("https://reqres.in/api/users")
	.then()
		.statusCode(201)
		.body("name", equalTo("nikunj"))
		.and()
		.body("job", equalTo("tester")).log().all();
	
//		RestAssured.get("https://reqres.in/api/users").
//		then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"))
//		.statusCode(200);
	}
}
