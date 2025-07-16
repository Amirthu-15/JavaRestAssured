package day3;

import static io.restassured.RestAssured.given;

import java.util.Map;
import java.util.Set;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersDemo {

	@Test(enabled = false)
	public void allHeader() {

		given()

				.when()

				.get("https://www.google.com/")

				.then()

				/*
				 * Expires: -1 Cache-Control: private, max-age=0 Content-Type: text/html;
				 * charset=ISO-8859-1 Content-Security-Policy-Report-Only: object-src
				 * 'none';base-uri 'self';script-src 'nonce-ySdLTpdpQkhDmambHmbHVQ'
				 * 'strict-dynamic' 'report-sample' 'unsafe-eval' 'unsafe-inline' https:
				 * http:;report-uri https://csp.withgoogle.com/csp/gws/other-hp Accept-CH:
				 * Sec-CH-Prefers-Color-Scheme P3P:
				 * CP="This is not a P3P policy! See g.co/p3phelp for more info."
				 * Content-Encoding: gzip Server: gws X-XSS-Protection: 0 X-Frame-Options:
				 * SAMEORIGIN
				 */

				.header("Cache-Control", "private, max-age=0").header("Content-Type", "text/html; charset=ISO-8859-1")
				.header("Accept-CH", "Sec-CH-Prefers-Color-Scheme")
				.header("P3P", "CP=\"This is not a P3P policy! See g.co/p3phelp for more info.\"")
				.header("Content-Encoding", "gzip").header("Server", "gws").header("X-XSS-Protection", "0")
				.header("X-Frame-Options", "SAMEORIGIN").log().all();

	}

	@Test(enabled = false)
	public void getHeaders() {

		Response response = given()

				.when()

				.get("https://www.google.com/");

		String Cache_Control = response.getHeader("Cache-Control");

		System.out.println("Cache control==> " + Cache_Control);

		/*
		 * 
		 * 
		 * 
		 * .header("Cache-Control", "private, max-age=0") .header("Content-Type",
		 * "text/html; charset=ISO-8859-1") .header("Accept-CH",
		 * "Sec-CH-Prefers-Color-Scheme") .header("P3P",
		 * "CP=\"This is not a P3P policy! See g.co/p3phelp for more info.\"")
		 * .header("Content-Encoding", "gzip") .header("Server", "gws")
		 * .header("X-XSS-Protection", "0") .header("X-Frame-Options",
		 * "SAMEORIGIN").log().all();
		 */

	}
	
	@Test(enabled = true)
	public void getallHeaders() {

		Response response = given()

				.when()

				.get("https://www.google.com/");

		/*String Cache_Control = response.getHeader("Cache-Control");

		System.out.println("Cache control==> " + Cache_Control);*/

		Headers headers = response.getHeaders();
		

		for(Header h: headers) {
			
			System.out.println(h.getName() + "=====" + h.getValue());
		}

	}

}
