package day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetUser {

	@Test
	public void test_getUser(ITestContext context) {

		// int id = (int) context.getAttribute("user_id"); // To set the id in test level
		
		int id = (int) context.getSuite().getAttribute("user_id"); // To set the id in suite level
		
		String bearerToken = "e2f7b16b409967d4901fcc5e73b4ce46f7e57515fb3e472d079f598fcad6aa25";

		given().headers("Authorization", "Bearer " + bearerToken).pathParam("id", id)

				.when().get("https://gorest.co.in/public/v2/users/{id}").then().statusCode(200).log().all();

	}

}
