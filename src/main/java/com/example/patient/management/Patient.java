package com.example.patient.management;
import java.util.Objects;
import java.util.UUID;

public class Patient {

    private final UUID ID;
    private PatientFullName _fullName;
    private Address _address;
    private ContactInformation _contactInformation;

    public PatientFullName getFullName() {
        return _fullName;
    }

    public Address getAddress() {
        return _address;
    }

    public ContactInformation getContactInformation() {
        return _contactInformation;
    }

    public UUID getID() {
        return ID;
    }

    private Patient(UUID ID, PatientFullName patientFullName, Address address, ContactInformation contactInformation) {
        this.ID = ID;
        this._fullName = patientFullName;
        this._address = address;
        this._contactInformation = contactInformation;
    }

    public Result<Patient> updateFullName(String FirstName, String LastName) {
        Result<PatientFullName> fullNameResult  = PatientFullName.create(FirstName, LastName);

        if (!fullNameResult.isSuccess()) {
            return Result.failure(fullNameResult.getMessage());
        }

        this._fullName = fullNameResult.getValue();
        return null;
    }

    public static Result<Patient> registerPatient(PatientFullName fullName, Address address, ContactInformation contactInformation) {
        if (fullName == null) {
            return Result.failure("Full name is required");
        }

        if (contactInformation == null) {
            return Result.failure("Contact info is required");
        }

        UUID ID = UUID.randomUUID();


        return Result.success(new Patient(ID, fullName, address, contactInformation));
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
