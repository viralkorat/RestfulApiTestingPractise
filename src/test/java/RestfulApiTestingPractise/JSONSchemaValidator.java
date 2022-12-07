package RestfulApiTestingPractise;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class JSONSchemaValidator {

	@Test
	public void getAllUsers() {
		
		RestAssured.get("https://reqres.in/api/users?page=2").
				then().assertThat().body(matchesJsonSchemaInClasspath("schema.json"))
				.statusCode(200);
	}
}

//Youtube Reference 
//https://www.youtube.com/watch?v=rkGI32WXmPQ

//schema.json will be created in target folder