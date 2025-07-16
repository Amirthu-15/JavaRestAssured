package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class CreateUser {

	@Test
	public void test_createUser(ITestContext context) {

		Faker faker = new Faker();

		JSONObject data = new JSONObject();

		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().safeEmailAddress());
		data.put("status", "inactive");

		String bearerToken = "e2f7b16b409967d4901fcc5e73b4ce46f7e57515fb3e472d079f598fcad6aa25";

		int id = given().headers("Authorization", "Bearer " + bearerToken).contentType(ContentType.JSON)
				.body(data.toString())

				.when().post("https://gorest.co.in/public/v2/users").jsonPath().getInt("id");

		System.out.println("Generated Id is " + id);
		
		//context.setAttribute("user_id", id); // To set as Test level in testng XML file
		
		context.getSuite().setAttribute("user_id", id); // To set as Suite level in testng XML file


	}

}
