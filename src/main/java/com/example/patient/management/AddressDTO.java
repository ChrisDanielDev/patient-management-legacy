package com.example.patient.management;

import java.util.Objects;
import java.util.UUID;


public class AddressDTO {

    public final String StreetName;
    public final String StateName;
    public final String CityName;
    public final String CountryName;

    public AddressDTO(String streetName, String countryName, String stateName, String cityName) {
        this.StreetName = streetName;
        this.CountryName = countryName;
        this.StateName = stateName;
        this.CityName = cityName;
    }
}
