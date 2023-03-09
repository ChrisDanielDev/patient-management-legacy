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
    void givenValidAddressAndFullNameAndContactInformation_whenRegisteringPatient_thenPatientIsRegistered() {
        // arrange
        Result<Address> addressResult = Address.create("Avram Iancu", "Romania", null, null);
        Result<PatientFullName> fullNameResult = PatientFullName.create("John", "Doe");
        Result<ContactInformation> contactInformation = ContactInformation.create("chris@email.com", "+1 123 456 7890");

        // act
        Result<Patient> pacientResult = Patient.registerPatient(fullNameResult.getValue(), addressResult.getValue(), contactInformation.getValue());
        
        // assert
        assertThat(pacientResult.isSuccess()).isTrue();
        
        Patient patient = pacientResult.getValue();
        assertThat(patient.getAddress()).isEqualTo(addressResult.getValue());
        assertThat(patient.getFullName()).isEqualTo(fullNameResult.getValue());
        assertThat(patient.getContactInformation()).isEqualTo(contactInformation.getValue());
    
    }

    @Test
    void givenOnlyFullNameAndContactInformation_whenRegisteringPatient_thenPatientIsRegistered() {
        // arrange
        Result<PatientFullName> fullNameResult = PatientFullName.create("John", "Doe");
        Result<ContactInformation> contactInformation = ContactInformation.create("chris@email.com", "+1 123 456 7890");

        // act
        Result<Patient> pacientResult = Patient.registerPatient(fullNameResult.getValue(), null, contactInformation.getValue());
        
        // assert
        assertThat(pacientResult.isSuccess()).isTrue();
        
        assertThat(pacientResult.getValue()).isNotNull();
    }

    @Test
    void givenOnlyAddress_whenRegisteringPatient_thenPatientIsNotRegistered() {
        // arrange
        Result<Address> addressResult = Address.create("Avram Iancu", "Romania", null, null);

        // act
        Result<Patient> pacientResult = Patient.registerPatient(null, addressResult.getValue(), null);
        
        // assert
        assertThat(pacientResult.isSuccess()).isFalse();
        assertThat(pacientResult.getValue()).isNull();
        assertThat(pacientResult.getMessage()).isEqualTo("Full name is required");
    }

    @Test
    void givenOnlyAddressAndFullName_whenRegisteringPatient_thenPatientIsNotRegistered() {
        // arrange
        Result<PatientFullName> fullNameResult = PatientFullName.create("John", "Doe");
        Result<Address> addressResult = Address.create("Avram Iancu", "Romania", null, null);

        // act
        Result<Patient> pacientResult = Patient.registerPatient(fullNameResult.getValue(), addressResult.getValue(), null);
        
        // assert
        assertThat(pacientResult.isSuccess()).isFalse();
        assertThat(pacientResult.getValue()).isNull();
        assertThat(pacientResult.getMessage()).isEqualTo("Contact info is required");
    }
}
