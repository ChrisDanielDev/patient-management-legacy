package com.example.patient.management;
import java.util.Objects;
import java.util.UUID;

public class Patient {

    private final UUID ID;
    private PatientFullName _fullName;
    private Address _address;

    public PatientFullName getFullName() {
        return _fullName;
    }

    public Address getAddress() {
        return _address;
    }

    private Patient(UUID ID, PatientFullName patientFullName, Address address) {
        this.ID = ID;
        this._fullName = patientFullName;
        this._address = address;
    }

    public Result<Patient> updateFullName(String FirstName, String LastName) {
        Result<PatientFullName> fullNameResult  = PatientFullName.create(FirstName, LastName);

        if (!fullNameResult.isSuccess()) {
            return Result.failure(fullNameResult.getMessage());
        }

        this._fullName = fullNameResult.getValue();
        return null;
    }

    public static boolean IsValid(String streetName, String CountryName, String StateName, String City, String FirstName, String LastName) {
        Result<Address> addressResult  = Address.create(streetName, CountryName, StateName, City);
        Result<PatientFullName> fullNameResult  = PatientFullName.create(FirstName, LastName);

        return addressResult.isSuccess() && fullNameResult.isSuccess();
    }

    public static Result<Patient> registerPatient(String streetName, String CountryName, String StateName, String City, String FirstName, String LastName) {
        if(!Patient.IsValid(streetName, CountryName, StateName, City, FirstName, LastName)){
            return Result.failure("Patient registration has faield, because address or full name is invalid.");
        }
        
        Result<Address> addressResult  = Address.create(streetName, CountryName, StateName, City);
        Result<PatientFullName> fullNameResult  = PatientFullName.create(FirstName, LastName);

        UUID ID = UUID.randomUUID();

        return Result.success(new Patient(ID, fullNameResult.getValue(), addressResult.getValue()));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Patient patient = (Patient) obj;
        return Objects.equals(ID, patient.ID);
    }
}
