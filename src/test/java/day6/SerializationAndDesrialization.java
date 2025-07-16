package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationAndDesrialization {

	@Test
	public void covertPojo2Json() throws JsonProcessingException {

		Student data = new Student();

		data.setName("Arun");
		data.setLocation("Trichy");
		data.setPhone("123456");

		String coursesArr[] = { "Selenium", "Java" };

		data.setCourses(coursesArr);

		// Convert Pojo Object to Json Object

		ObjectMapper objectMapper = new ObjectMapper();

		String jsonData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);

		System.out.println(jsonData);
	}

	@Test
	public void covertJson2Pojo() throws JsonProcessingException {
		
		// Convert Json data to Pojo Object
		
		String jsonData = "{\r\n" + 
				"  \"name\" : \"Amirtharaj\",\r\n" + 
				"  \"location\" : \"Chennai\",\r\n" + 
				"  \"phone\" : \"9789115966\",\r\n" + 
				"  \"courses\" : [ \"Selenium\", \"Java\" ]\r\n" + 
				"}";
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Student readValue = objectMapper.readValue(jsonData, Student.class);
		
		System.out.println(readValue.getName());
		System.out.println(readValue.getLocation());
		System.out.println(readValue.getPhone());
		System.out.println(readValue.getCourses()[0]);
		System.out.println(readValue.getCourses()[1]);
		
	}

}
