package day5;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

public class Parsing_XML_Response {

	@Test(enabled = false)
	public void testXMLResponse() {

		given()

				.when().get("https://www.w3schools.com/xml/simple.xml")

				.then().statusCode(200).header("Content-Type", "text/xml")
				.body("breakfast_menu.food[0].name", equalTo("Belgian Waffles"))
				.body("breakfast_menu.food[4].calories", equalTo("950")).log().all();

	}

	@Test(enabled = false)
	public void testXMLResponse_XMlPath() {

		Response response = given()

				.when().get("https://www.w3schools.com/xml/simple.xml");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		String foodName = response.xmlPath().get("breakfast_menu.food[0].name").toString();
		Assert.assertEquals(foodName, "Belgian Waffles");

		String calories = response.xmlPath().get("breakfast_menu.food[4].calories").toString();
		Assert.assertEquals(Integer.parseInt(calories), 950);

	}

	@Test(enabled = true)
	public void testXMLResponse_XMlPathClass() {

		Response response = given()

				.when().get("https://www.w3schools.com/xml/simple.xml");

		XmlPath xmlPath = new XmlPath(response.asPrettyString());

		// Verify food count
		List<String> foodCount = xmlPath.getList("breakfast_menu.food");
		Assert.assertEquals(foodCount.size(), 5);

		// Verify particular food is present in the list
		List<String> foodNames = xmlPath.getList("breakfast_menu.food.name");

		boolean status = false;

		for (int i = 0; i < foodNames.size(); i++) {

			String foodName = foodNames.get(i);

			if (foodName.equals("Homestyle Breakfast")) {

				status = true;
				break;
			}
		}

		Assert.assertEquals(status, true);

		/*
		 * for (String newFoodName: foodName) {
		 * 
		 * if (newFoodName.equals("Homestyle Breakfast")) {
		 * 
		 * status = true; break; }
		 * 
		 * }
		 * 
		 * Assert.assertEquals(status, true);
		 */

	}
}
