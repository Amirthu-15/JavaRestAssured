package day7;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerLibrary {

	@Test
	public void testFakerLibrary() {

		Faker faker = new Faker();

		String fullName = faker.name().fullName();

		String firstName = faker.name().firstName();

		String lastName = faker.name().lastName();

		String username = faker.name().username();

		String password = faker.internet().password();

		String cellPhone = faker.phoneNumber().cellPhone();

		String emailAddress = faker.internet().safeEmailAddress();

		System.out.println("Full Name: " + fullName);
		System.out.println("First Name: " + firstName);
		System.out.println("Last Name: " + lastName);
		System.out.println("User Name: " + username);
		System.out.println("Password: " + password);
		System.out.println("Cell phone: " + cellPhone);
		System.out.println("Email Address: " + emailAddress);

	}

}
