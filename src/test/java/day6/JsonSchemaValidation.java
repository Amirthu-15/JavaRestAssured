package day6;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

public class JsonSchemaValidation {

	@Test
	public void jsonSchemaValidator() {

		given()

				.when().get("http://localhost:3000/store/")

				.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("StoreJsonSchema.json"));

	}

}
