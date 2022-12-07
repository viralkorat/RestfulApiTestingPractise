package RestfulApiTestingPractise;

import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;


public class PostRequestUsingExcelData {

	@DataProvider(name="dataProvider")
	public Object[][] postRequestUsingExcelData(){
		Object[][] testData = null;
		try {
			testData = ExcelUtils.getTableArray("Test.xlsx","Sheet1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return testData;
	}
	
	@Test(dataProvider="dataProvider")
	public void test_post_1(String name, String job) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", name);
		map.put("job", job);
		
		Gson gson = new Gson();
		
		String requestData = gson.toJson(map);
		
		System.out.println(requestData);
		
		RestAssured.given()
		.header("Content-Type", "application/json;charset=UTF-8")
		.body(requestData)
		.post("https://reqres.in/api/users")
	.then()
		.statusCode(201)
		.body("name", equalTo(name))
		.and()
		.body("job", equalTo(job));
	}
}
