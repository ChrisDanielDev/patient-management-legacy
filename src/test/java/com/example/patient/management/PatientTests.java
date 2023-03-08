package com.example.patient.management;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PatientTests {

	@Test
	void contextLoads() {
	}

    @Test
    void givenValidAddressAndFullName_whenCreatingPatient_thenPatientIsRegistered() {
        // arrange
        Result<Address> addressResult = Address.create("Avram Iancu", "Romania", null, null);
        Result<PatientFullName> fullNameResult = PatientFullName.create("John", "Doe");

        // act
        Result<Patient> pacientResult = Patient.registerPatient("Avram Iancu", "Romania", null, null, "John", "Doe");

        // assert
        assertThat(pacientResult.getValue().getFullName()).isEqualTo(fullNameResult.getValue());
        assertThat(pacientResult.getValue().getAddress()).isEqualTo(addressResult.getValue());
    }
    
    @Test
    void givenInvalidAddress_whenCreatingPatient_thenPatientIsNotRegistered() {
        // act & arrange
        Result<Patient> pacientResult = Patient.registerPatient("Avram Iancu", "", null, null, "John", "Doe");

        // assert
        assertThat(pacientResult.isSuccess()).isFalse();
        assertThat(pacientResult.getMessage()).isEqualTo("Country name is required");
    }


    
    @Test
    void givenInvalidFirstName_whenCreatingPatient_thenPatientIsNotRegistered() {
        // act & arrange
        Result<Patient> pacientResult = Patient.registerPatient("Avram Iancu", "Romania", null, null, "", "Doe");

        // assert
        assertThat(pacientResult.isSuccess()).isFalse();
        assertThat(pacientResult.getMessage()).isEqualTo("First name is required");
    }
}
