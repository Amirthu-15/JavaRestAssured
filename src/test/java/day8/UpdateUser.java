package day8;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;


public class UpdateUser {

	
	@Test
	public void test_updateUser(ITestContext context) {
		
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "male");
		data.put("email", faker.internet().safeEmailAddress());
		data.put("status", "active");
		
		//int id = (int) context.getAttribute("user_id"); // To set the id in test level
		
		int id = (int) context.getSuite().getAttribute("user_id"); // To set the id in suite level
		
		String bearerToken = "e2f7b16b409967d4901fcc5e73b4ce46f7e57515fb3e472d079f598fcad6aa25";

		given()
		.headers("Authorization", "Bearer "+bearerToken)
		.contentType(ContentType.JSON)
		.pathParam("id", id)
		.body(data.toString())
		
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();

	}
}
