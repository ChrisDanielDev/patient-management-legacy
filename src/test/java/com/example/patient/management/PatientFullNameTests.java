package com.example.patient.management;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatientFullNameTests {

	@Test
	void contextLoads() {
	}

    @Test
    void givenValidName_whenCreatingPatientFullName_thenFullNameIsCreated() {
        String firstName = "John";
        String lastName = "Doe";
        Result<PatientFullName> result = PatientFullName.create(firstName, lastName);
        assertThat(result.getValue().toString()).isEqualTo("John Doe");
    }

    @Test
    void givenEmptyFirstName_whenCreatingPatientFullName_thenFullNameIsNotCreated() {
        String firstName = "";
        String lastName = "Doe";
        Result<PatientFullName> result = PatientFullName.create(firstName, lastName);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage().toString()).isEqualTo("First name is required");
    }

    @Test
    void givenFirstName_whenFirstNameIsTooLong_thenFullNameIsNotCreated() {
        String firstName = "First name, too loooooooooooooooooooooooooooooooooooooooooooooooooooooooong";
        String lastName = "Doe";
        Result<PatientFullName> result = PatientFullName.create(firstName, lastName);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo("First name too long");
    }

    @Test
    void givenEmptyLastName_whenCreatingPatientFullName_thenFullNameIsNotCreated() {
        String firstName = "John";
        String lastName = "";
        Result<PatientFullName> result = PatientFullName.create(firstName, lastName);
    
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage().toString()).isEqualTo("Last name is required");
    }
    
    @Test
    void givenNullLastName_whenCreatingPatientFullName_thenFullNameIsNotCreated() {
        String firstName = "John";
        String lastName = null;
        Result<PatientFullName> result = PatientFullName.create(firstName, lastName);
    
        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage().toString()).isEqualTo("Last name is required");
    }

    @Test
    void givenLastName_whenLastNameIsTooLong_thenFullNameIsNotCreated() {
        String firstName = "John";
        String lastName = "Last name, too loooooooooooooooooooooooooooooooooooooooooooooooooooooooong";
        Result<PatientFullName> result = PatientFullName.create(firstName, lastName);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Last name too long");
    }

    
    @Test
    void givenEqualFullNames_whenComparingPatientFullNames_thenTheyAreEqual() {
        String firstName = "John";
        String lastName = "Doe";

        Result<PatientFullName> result1 = PatientFullName.create(firstName, lastName);
        Result<PatientFullName> result2= PatientFullName.create(firstName, lastName);

        PatientFullName fullName1 = result1.getValue();
        PatientFullName fullName2 = result2.getValue();

        assertThat(fullName1).isEqualTo(fullName2);
    }
    
    @Test
    void givenDifferentFullNames_whenComparingPatientFullNames_thenTheyAreNotEqual() {
        Result<PatientFullName> result1 = PatientFullName.create("John", "Doe");
        Result<PatientFullName> result2= PatientFullName.create("Jane", "Doe");

        PatientFullName fullName1 = result1.getValue();
        PatientFullName fullName2 = result2.getValue();

        assertThat(fullName1).isNotEqualTo(fullName2);
    }
    
    @Test
    void givenNullFullName_whenComparingPatientFullName_thenFullNameIsNotEqual() {
        Result<PatientFullName> result= PatientFullName.create("John", "Doe");

        PatientFullName fullName = result.getValue();
        assertThat(fullName).isNotEqualTo(null);
    }
    
}
