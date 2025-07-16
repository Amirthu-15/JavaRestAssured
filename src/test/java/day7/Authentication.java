package day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentication {

	@Test(priority = 1)
	public void testBasicAuthentication() {

		given()

				.auth().basic("postman", "password")

				.when().get("https://postman-echo.com/basic-auth")

				.then().statusCode(200).body("authenticated", equalTo(true)).log().all();

	}

	@Test(priority = 2)
	public void testDigestAuthentication() {

		given()

				.auth().digest("postman", "password")

				.when().get("https://postman-echo.com/basic-auth")

				.then().statusCode(200).body("authenticated", equalTo(true)).log().all();

	}

	@Test(priority = 3)
	public void testPreemptiveAuthentication() {

		given()

				.auth().preemptive().basic("postman", "password")

				.when().get("https://postman-echo.com/basic-auth")

				.then().statusCode(200).body("authenticated", equalTo(true)).log().all();

	}

	@Test(priority = 4)
	public void testBearerTokenAuthentication() {

		String bearerToken = System.getenv("GITHUB_TOKEN");

		given()

				.headers("Authorization", "Bearer " + bearerToken)

				.when().get("https://api.github.com/user/repos")

				.then()
				.statusCode(200)
				.log().all();

	}
	
	@Test(priority = 5, enabled=false)
	public void testOauthAuthentication() {


		given()
				// This is oauth 1.0 Authentication
				.auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")

				.when().get("url")

				.then()
				.statusCode(200)
				.log().all();

	}
	
	@Test(priority = 6, enabled=false)
	public void testOauth2Authentication() {


		given()
				// This is oauth 2.0 Authentication
				.auth().oauth2("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")

				.when().get("url")

				.then()
				.statusCode(200)
				.log().all();

	}
	
	@Test(priority = 7, enabled=false)
	public void testApiAuthentication1() {


		given()
				// This is Api Authentication
				.queryParam("appid", "f58b4bfa57e15013799775cfc76546c5")

				.when().get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")

				.then()
				.statusCode(200)
				.log().all();

	}
	
	@Test(priority = 7, enabled=false)
	public void testApiAuthentication2() {


		given()
				// This is Api Authentication
				
				.pathParam("myPath", "data/2.5/forecast/daily")
				.queryParam("appid", "f58b4bfa57e15013799775cfc76546c5")
				
				.queryParam("q", "Delhi")
				
				.queryParam("units", "metric")
				
				.queryParam("cnt", "7")

				.when()
				
				.get("https://api.openweathermap.org/{myPath}")

				.then()
				.statusCode(200)
				.log().all();

	}

}
