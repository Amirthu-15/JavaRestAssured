package day4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ParsingJsonResponseData {

	@Test(enabled = true)
	public void testJsonResponse() {

		given().contentType("application/json")

				.when().get("http://localhost:3000/store")

				.then().statusCode(200).header("Content-Type", "application/json")
				.body("book[3].title", equalTo("The Lord of the Rings")).log().all();

	}

	@Test(enabled = true)
	public void testJsonResponse1() {

		Response response = given().contentType("application/json")

				.when().get("http://localhost:3000/store");

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

		String header = response.header("Content-Type");
		Assert.assertEquals(header, "application/json");

		String bookName = response.jsonPath().get("book[3].title").toString();
		Assert.assertEquals(bookName, "The Lord of the Rings");

	}

	@Test(enabled = true)
	public void testJsonResponse2() {

		Response response = given().contentType(ContentType.JSON)

				.when().get("http://localhost:3000/store");

		JSONObject jsonObject = new JSONObject(response.asPrettyString());

		int length = jsonObject.getJSONArray("book").length();

		for (int i = 0; i < length; i++) {

			String bookTitle = jsonObject.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.err.println(bookTitle);

		}

	}

	@Test(enabled = true)
	public void testJsonResponse3() {

		Response response = given().contentType(ContentType.JSON)

				.when().get("http://localhost:3000/store");

		JSONObject jsonObject = new JSONObject(response.asPrettyString());

		int length = jsonObject.getJSONArray("book").length();

		boolean status = false;

		for (int i = 0; i < length; i++) {

			String bookTitle = jsonObject.getJSONArray("book").getJSONObject(i).get("title").toString();

			if (bookTitle.equals("The Lord of the Rings")) {

				status = true;
				break;
			}

		}

		Assert.assertEquals(status, true);

		// Validate total price of books

		double totalPrice = 0;

		for (int i = 0; i < jsonObject.getJSONArray("book").length(); i++) {

			String price = jsonObject.getJSONArray("book").getJSONObject(i).get("price").toString();

			totalPrice = totalPrice + Double.parseDouble(price);

		}

		System.out.println("Total price of book is: " + totalPrice);
		
		Assert.assertEquals(totalPrice, 526.0);

	}

}
