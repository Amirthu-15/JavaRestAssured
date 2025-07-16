package day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {

	@Test(enabled = false)
	public void singleFileUpload() {

		File myFile = new File("D:\\Greens_Technology\\FileUpload\\Test1.txt");

		given()

				.multiPart("file", myFile)

				.contentType(io.restassured.http.ContentType.MULTIPART).when()

				.post("http://localhost:8080/uploadFile")

				.then().statusCode(200).body("fileName", equalTo("Test1.txt")).log().all();

	}

	@Test(enabled = false)
	public void multipleFileUpload() {

		File myFile1 = new File("D:\\Greens_Technology\\FileUpload\\Test1.txt");
		File myFile2 = new File("D:\\Greens_Technology\\FileUpload\\Test2.txt");

		given()

				.multiPart("file", myFile1).multiPart("file", myFile2)

				// .contentType(io.restassured.http.ContentType.MULTIPART)
				.contentType("multipart/form-data")

				.when()

				.post("http://localhost:8080/uploadMultipleFiles")

				.then().statusCode(200).body("[0].fileName", equalTo("Test1.txt"))
				.body("[1].fileName", equalTo("Test2.txt")).log().all();

	}

	@Test(enabled = false)
	public void multipleFileUpload2() {

		File myFile1 = new File("D:\\Greens_Technology\\FileUpload\\Test1.txt");
		File myFile2 = new File("D:\\Greens_Technology\\FileUpload\\Test2.txt");

		File fileArr[] = { myFile1, myFile2 };

		given()

				.multiPart("file", fileArr)

				// .contentType(io.restassured.http.ContentType.MULTIPART)
				.contentType("multipart/form-data")

				.when()

				.post("http://localhost:8080/uploadMultipleFiles")

				.then().statusCode(200).body("[0].fileName", equalTo("Test1.txt"))
				.body("[1].fileName", equalTo("Test2.txt")).log().all();

	}

	@Test(enabled = true)
	public void singleFileDownload() {

		given()

				.when()

				.get("http://localhost:8080/downloadFile/Test1.txt")

				.then().statusCode(200).log().all();

	}
	
	@Test(enabled = true)
	public void multipleFileDownload() {

		given()

				.when()

				.get("http://localhost:8080/downloadFile/Test2.txt")

				.then().statusCode(200).log().all();

	}

}
