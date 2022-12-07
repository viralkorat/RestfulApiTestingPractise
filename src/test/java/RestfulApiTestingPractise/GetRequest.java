package RestfulApiTestingPractise;

import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class GetRequest {

	@Test(priority = 1)
	public void getSpecificUser() {
		Response response = RestAssured.get("https://reqres.in/api/users/2");

		int statusCode = response.getStatusCode();
		ResponseBody body = response.body();
		String bodyAsString = body.asString();
		System.out.println(bodyAsString);

		System.out.println("statusCode : " + statusCode);

		Assert.assertEquals(statusCode, 200);
	}

	@Test(priority = 2)
	public void getAllUsers() {
		RestAssured.get("https://reqres.in/api/users?page=2")
		.then()
		.statusCode(200)
		.body("data.id[0]", equalTo(7))
		.and()
		.body("data.first_name[0]", equalTo("Michael"))
		.log().all();

	}
}
