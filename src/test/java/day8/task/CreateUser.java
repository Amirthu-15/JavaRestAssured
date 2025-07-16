package day8.task;
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
		data.put("location", faker.address().cityName());
		data.put("phone", faker.phoneNumber().cellPhone());
		
		String coursesArr[] = {faker.programmingLanguage().name(), faker.programmingLanguage().name()};
		data.put("courses", coursesArr);

		String id = given()
			.contentType(ContentType.JSON)
			.body(data.toString())
		
		.when()
			.post("http://localhost:3000/students").jsonPath().getString("id");
		
		System.out.println("Generated Id: "+id);
		
		//context.setAttribute("user_id", id);
		
		context.getSuite().setAttribute("user_id", id);
		
		

	}

}
