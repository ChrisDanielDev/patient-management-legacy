package com.example.patient.management;
import java.util.Optional;

public class Address {
    private static final String ERROR_STREET_NAME_REQUIRED = "Street name is required";
    private static final String ERROR_STREET_NAME_TOO_LONG = "Street name too long";
    private static final String ERROR_COUNTRY_NAME_REQUIRED = "Country name is required";
    private static final String ERROR_COUNTRY_NAME_TOO_LONG = "Country name too long";
    private static final String ERROR_STATE_NAME_TOO_LONG = "State name too long";
    private static final String ERROR_CITY_NAME_TOO_LONG = "City name too long";

    private final String _streetName;
    private final String _stateName;
    private final String _cityName;
    private final String _countryName;
    
    private Address(String streetName, String countryName, String stateName, String cityName) {
        this._streetName = streetName;
        this._countryName = countryName;
        this._stateName = stateName;
        this._cityName = cityName;
    }

    public static Result<Address> create(String streetName, String countryName, String stateName, String cityName) {
        Optional<String> stateNameOptional = Optional.ofNullable(stateName);
        Optional<String> cityNameOptional = Optional.ofNullable(cityName);

        if (streetName == null || streetName.isEmpty()) {
            return Result.failure(ERROR_STREET_NAME_REQUIRED);
        }
        
        if (streetName.length() > 50) {
            return Result.failure(ERROR_STREET_NAME_TOO_LONG);
        }
        
        if (countryName == null || countryName.isEmpty()) {
            return Result.failure(ERROR_COUNTRY_NAME_REQUIRED);
        }
        
        if (countryName.length() > 50) {
            return Result.failure(ERROR_COUNTRY_NAME_TOO_LONG);
        }
        
        if (stateNameOptional.map(String::length).orElse(0) > 50) {
            return Result.failure(ERROR_STATE_NAME_TOO_LONG);
        }
        
        if (cityNameOptional.map(String::length).orElse(0) > 50) {
            return Result.failure(ERROR_CITY_NAME_TOO_LONG);
        }

        return Result.success(new Address(streetName, countryName, stateName, cityName));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._streetName);

        Optional.ofNullable(this._cityName).ifPresent(city -> sb.append(", ").append(city));
        Optional.ofNullable(this._stateName).ifPresent(state -> sb.append(", ").append(state));

        sb.append(", ").append(this._countryName);
        return sb.toString();
    }
}
