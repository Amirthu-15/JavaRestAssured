package day8;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class DeleteUser {

	@Test
	public void test_deleteUser(ITestContext context) {
		
		// int id = (int) context.getAttribute("user_id"); // To set the id in test level
		
		int id = (int) context.getSuite().getAttribute("user_id"); // To set the id in suite level
		
		String bearerToken = "e2f7b16b409967d4901fcc5e73b4ce46f7e57515fb3e472d079f598fcad6aa25";

		given()
		.headers("Authorization", "Bearer "+bearerToken)
		.contentType(ContentType.JSON)
		.pathParam("id", id)
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();

	}
	
}
