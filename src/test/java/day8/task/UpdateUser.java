package day8.task;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.http.ContentType;

public class UpdateUser {

	@Test
	public void test_updateUser(ITestContext context) {

		// String id = (String) context.getAttribute("user_id");

		String id = (String) context.getSuite().getAttribute("user_id");

		Faker faker = new Faker();

		JSONObject data = new JSONObject();

		data.put("name", faker.name().fullName());
		data.put("location", faker.address().cityName());
		data.put("phone", faker.phoneNumber().cellPhone());

		String coursesArr[] = { faker.programmingLanguage().name(), faker.programmingLanguage().name() };
		data.put("courses", coursesArr);

		given().contentType(ContentType.JSON).body(data.toString()).pathParam("id", id)

				.when().put("http://localhost:3000/students/{id}")

				.then().statusCode(200).log().all();

	}

}
