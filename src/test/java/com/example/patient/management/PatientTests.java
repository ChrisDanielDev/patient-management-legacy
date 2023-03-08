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
        assertThat(pacientResult.getMessage()).isEqualTo("Patient registration has faield, because address or full name is invalid.");
    }

    @Test
    void givenInvalidFirstName_whenCreatingPatient_thenPatientIsNotRegistered() {
        // act & arrange
        Result<Patient> pacientResult = Patient.registerPatient("Avram Iancu", "Romania", null, null, "", "Doe");

        // assert
        assertThat(pacientResult.isSuccess()).isFalse();
        assertThat(pacientResult.getMessage()).isEqualTo("Patient registration has faield, because address or full name is invalid.");
    }

    @Test
    void givenValidFirstNameAddress_whenValidatingPatientInformation_thenPatientIsValid() {
        assertThat(Patient.IsValid("Avram Iancu", "Romania", null, null, "Jhon", "Doe")).isTrue();
    }

    @Test
    void givenInvalidFirstName_whenValidatingPatientInformation_thenPatientIsNotValid() {
        assertThat(Patient.IsValid("Avram Iancu", "Romania", null, null, "", "Doe")).isFalse();
    }

    @Test
    void givenInvalidAddress_whenValidatingPatientInformation_thenPatientIsNotValid() {
        assertThat(Patient.IsValid("Avram Iancu", "", null, null, "Chris", "Doe")).isFalse();
    }
}
