package day8.task;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetUser {
	
	@Test
	public void test_getUser(ITestContext context) {
		
		// String id = (String) context.getAttribute("user_id");
		
		String id = (String) context.getSuite().getAttribute("user_id");

		given()
			.contentType(ContentType.JSON)
			.pathParam("id", id)
		
		.when()
			.get("http://localhost:3000/students/{id}")
		
			.then()
		
			.statusCode(200)
			.log()
			.all();
		
		
		

	}
}
