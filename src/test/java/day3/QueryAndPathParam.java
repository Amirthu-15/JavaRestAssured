package day3;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class QueryAndPathParam {

	@Test
	public void testQueryAndPathParam() {

		given()
			.header("x-api-key", "reqres-free-v1")
			.contentType("application/json")
			.pathParam("mypath", "users")
			.queryParam("page", 2)
			.queryParam("id", 5)
				
				.when()
					.get("https://reqres.in/api/{mypath}")
				
				
				.then()
					.statusCode(200).log().all();

	}

}
