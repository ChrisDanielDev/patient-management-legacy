package com.example.patient.management;

public class Patient {

    private PatientFullName _fullName;
    private Address _address;

    public PatientFullName getFullName() {
        return _fullName;
    }

    public Address getAddress() {
        return _address;
    }

    private Patient(PatientFullName patientFullName, Address address) {
        this._fullName = patientFullName;
        this._address = address;
    }

    public static Result<Patient> registerPatient(String streetName, String CountryName, String StateName, String City, String FirstName, String LastName) {
        Result<Address> addressResult  = Address.create(streetName, CountryName, StateName, City);
        Result<PatientFullName> fullNameResult  = PatientFullName.create(FirstName, LastName);

        if (!addressResult.isSuccess()) {
            return Result.failure(addressResult.getMessage());
        }
        if (!fullNameResult.isSuccess()) {
            return Result.failure(fullNameResult.getMessage());
        }
        
        return Result.success(new Patient(fullNameResult.getValue(), addressResult.getValue()));
    }

}
