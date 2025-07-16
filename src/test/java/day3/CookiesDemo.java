package day3;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class CookiesDemo {

	@Test(enabled = false)
	public void testCookies() {

		given()

				.when().get("https://www.google.com/")

				.then().cookie("AEC", "AVh_V2gbau3QcFnU6wNccdPTvg3QnlCNSq90Y-zHypLNBnWrnJfmyGX26Q").log().all();

	}

	@Test(enabled = false)
	public void singleCookie() {

		Response response = given()

				.when().get("https://www.google.com/");

		String cookie = response.getCookie("AEC");
		System.out.println("Cookie value is====> " + cookie);

	}

	@Test(enabled = true)
	public void allCookie() {

		Response response = given()

				.when().get("https://www.google.com/");

		Map<String, String> cookies = response.getCookies();

		Set<String> keySet = cookies.keySet();

		for (String newKeySet : keySet) {

			String cookie = response.getCookie(newKeySet);

			System.out.println("Cookie==> " + cookie);
		}

	}

}
