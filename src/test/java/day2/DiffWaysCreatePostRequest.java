package day2;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class DiffWaysCreatePostRequest {

	@Test(enabled = false)
	public void createPostRequestUsingHashMap() {

		HashMap data = new HashMap<>();

		data.put("name", "Amirtharaj");
		data.put("location", "India");
		data.put("phone", "1234567890");

		String coursesArr[] = { "C", "C++" };

		data.put("courses", coursesArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("Amirtharaj")).body("location", equalTo("India"))
				.body("phone", equalTo("1234567890")).body("courses[0]", equalTo("C"))
				.body("courses[1]", equalTo("C++")).log().all();
	}

	@Test(enabled = false)
	public void testDelete() {

		given().when().delete("http://localhost:3000/students/8e6d").then().statusCode(200);
	}

	@Test(enabled = false)
	public void createPostRequestUsingJson() {

		JSONObject data = new JSONObject();

		data.put("name", "Amirtharaj");
		data.put("location", "India");
		data.put("phone", "1234567890");

		String coursesArr[] = { "C", "C++" };

		data.put("courses", coursesArr);

		given().header("content-type", "application/json").body(data.toString()).when()
				.post("http://localhost:3000/students").then().statusCode(201).body("name", equalTo("Amirtharaj"))
				.body("location", equalTo("India")).body("phone", equalTo("1234567890"))
				.body("courses[0]", equalTo("C")).body("courses[1]", equalTo("C++")).log().all();
	}

	@Test(enabled=false)
	public void testPostUsingPojo() {

		POJO_PostRequest data = new POJO_PostRequest();

		data.setName("Arun");
		data.setLocation("Trichy");
		data.setPhone("123456");

		String coursesArr[] = { "Selenium", "Java" };

		data.setCourses(coursesArr);

		given().contentType("application/json").body(data).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("Arun")).body("location", equalTo("Trichy"))
				.body("phone", equalTo("123456")).body("courses[0]", equalTo("Selenium"))
				.body("courses[1]", equalTo("Java")).log().all();

	}
	
	@Test
	public void testPostUsingJson() throws FileNotFoundException {

		File file = new File("JSON_FILES\\Students.json");
		
		FileReader fileReader = new FileReader(file);
		
		JSONTokener jsonTokener = new JSONTokener(fileReader);
		
		JSONObject data = new JSONObject(jsonTokener);
		

		given().contentType("application/json").body(data.toString()).when().post("http://localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("Amirthu")).body("location", equalTo("India"))
				.body("phone", equalTo("1234567890")).body("courses[0]", equalTo("Java"))
				.body("courses[1]", equalTo("Selenium")).log().all();

	}

}
