package com.example.patient.management;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AddressTests {

	@Test
	void contextLoads() {
	}

	@Test
    void givenStreetName_whenStreetNameIsValid_thenAddressIsCreated() {
        Result<Address> result = Address.create("Avram Iancu", "Romania", null, null);

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getValue().toString()).isEqualTo("Avram Iancu, Romania");
    }

    @Test
    void givenStreetName_whenStreetNameIsTooLong_thenAddressIsNotCreated() {
        Result<Address> result = Address.create("Avram Iancu, too loooooooooooooooooooooooooooooooooooooooooooooooooooooooong", "Romania", null, null);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Street name too long");
    }

    @Test
    void givenStreetName_whenStreetNameIsEmpty_thenAddressIsNotCreated() {
        Result<Address> result = Address.create("", "Romania", null, null);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Street name is required");
    }

	@Test
    void givenCountryName_whenCountryNameIsValid_thenAddressIsCreated() {
        Result<Address> result = Address.create("Avram Iancu", "Romania", null, null);

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getValue().toString()).contains("Romania");
    }

    @Test
    void givenCountryName_whenCountryNameIsTooLong_thenAddressIsNotCreated() {
        String countryName = "A country name that is too loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong";
        Result<Address> result = Address.create("Avram Iancu", countryName, null, null);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo("Country name too long");
    }

    @Test
    void givenStateName_whenStateNameIsValid_thenAddressIsCreated() {
        Result<Address> result = Address.create("Avram Iancu", "Romania", "Cluj", null);

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getValue().toString()).contains("Cluj");
    }

    @Test
    void givenStateName_whenStateNameIsTooLong_thenAddressIsNotCreated() {
        String stateName = "A state name that is too loooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong";
        Result<Address> result = Address.create("Avram Iancu", "Romania", stateName, null);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo("State name too long");
    }

    @Test
    void givenCityName_whenCityNameIsValid_thenAddressIsCreated() {
        Result<Address> result = Address.create("Avram Iancu", "Romania", "Cluj", "Cluj-Napoca");

        assertThat(result.isSuccess()).isTrue();
        assertThat(result.getValue().toString()).contains("Cluj-Napoca");
    }

    @Test
    void givenCityName_whenCityNameIsTooLong_thenAddressIsNotCreated() {
        String cityName = "A city name that is too looooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooong";
        Result<Address> result = Address.create("Avram Iancu", "Romania", "Cluj", cityName);

        assertThat(result.isSuccess()).isFalse();
        assertThat(result.getMessage()).isEqualTo("City name too long");
    }
}
