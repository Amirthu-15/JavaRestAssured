package day1;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTP_Request {

	int id;

	@Test(enabled = false)
	public void getUsers() {

		given()

				.when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log()
				.all();

	}

	@Test(priority = 1)
	public void createUser() {

		HashMap data = new HashMap();

		data.put("name", "Amirtharaj");
		data.put("job", "QA");

		id = given()

				.header("x-api-key", "reqres-free-v1").contentType("application/json").body(data).when()
				.post("https://reqres.in/api/users").body().jsonPath().getInt("id");
	

	}

	@Test(priority = 2, dependsOnMethods = "createUser")
	public void updateUser() {

		HashMap data = new HashMap();

		data.put("name", "Selva");
		data.put("job", "Data Analyst");

		given()

				.header("x-api-key", "reqres-free-v1").contentType("application/json").body(data).when()
				.post("https://reqres.in/api/users/" + id).then().statusCode(201).log().all();

	}

	@Test(priority=3)
	public void deleteUser() {

		given().header("x-api-key", "reqres-free-v1").contentType("application/json").when()
				.delete("https://reqres.in/api/users/" + id).then().log().all();

	}

}
