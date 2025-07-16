package day6;

import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;

import static io.restassured.RestAssured.given;

public class XmlSchemaValidation {

	@Test
	public void testXmlSchemaValidation() {

		given()

				.when().get("https://www.w3schools.com/xml/simple.xml")

				.then().assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("FoodXmlSchema.xsd")).log().all();

	}

}
