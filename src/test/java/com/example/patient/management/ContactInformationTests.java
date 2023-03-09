package com.example.patient.management;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContactInformationTests {

	@Test
	void contextLoads() {
	}

	@Test
    void givenValidEmailAndPhoneNumber_whenCreatingContactInformation_thenContactInfromationIsCreated() {
        Result<ContactInformation> result = ContactInformation.create("christ@gmail.com", "+1 123 456 7890");

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getValue().toString()).isEqualTo("Phone Number: +1 123 456 7890, Email: christ@gmail.com");
    }

    @Test
    void givenValidEmailAndInvalidPhoneNumber_whenCreatingContactInformation_thenContactInfromationIsNotCreated() {
        Result<ContactInformation> result = ContactInformation.create("christ@gmail.com", "321421");

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Phone number is not valid.");
    }

    @Test
    void givenInValidEmailAndValidPhoneNumber_whenCreatingContactInformation_thenContactInfromationIsNotCreated() {
        Result<ContactInformation> result = ContactInformation.create("christgmail.com", "321421");

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Email is not valid.");
    }

    @Test
    void givenOnlyPhoneNumber_whenCreatingContactInformation_thenContactInfromationIsCreated() {
        Result<ContactInformation> result = ContactInformation.create("", "+1 123 456 7890");

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getValue().toString()).isEqualTo("Phone Number: +1 123 456 7890");
    }

    @Test
    void givenOnlyEmail_whenCreatingContactInformation_thenContactInfromationIsNotCreated() {
        Result<ContactInformation> result = ContactInformation.create("christ@gmail.com", null);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Phone Number is required.");
    }
}
