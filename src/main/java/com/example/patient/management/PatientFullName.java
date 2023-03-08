package com.example.patient.management;
import java.util.Objects;
import java.util.Optional;

public class PatientFullName {
    private static final String ERROR_FIRST_NAME_REQUIRED = "First name is required";
    private static final String ERROR_FIRST_NAME_TOO_LONG = "First name too long";
    private static final String ERROR_LAST_NAME_REQUIRED = "Last name is required";
    private static final String ERROR_LAST_NAME_TOO_LONG = "Last name too long";

    private final String _firstName;
    private final String _lastName;

    private PatientFullName(String firstName, String lastName) {
        this._firstName = firstName;
        this._lastName = lastName;
    }

    public static Result<PatientFullName> create(String firstName, String lastName) {
        if (firstName == null || firstName.isEmpty()) {
            return Result.failure(ERROR_FIRST_NAME_REQUIRED);
        }
    
        if (firstName.length() > 50) {
            return Result.failure(ERROR_FIRST_NAME_TOO_LONG);
        }
    
        if (lastName == null || lastName.isEmpty()) {
            return Result.failure(ERROR_LAST_NAME_REQUIRED);
        }
    
        if (lastName.length() > 50) {
            return Result.failure(ERROR_LAST_NAME_TOO_LONG);
        }
    
        return Result.success(new PatientFullName(firstName, lastName));
    }

    @Override
    public String toString() {
        return _firstName + " " + _lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PatientFullName other = (PatientFullName) obj;
        return Objects.equals(_firstName, other._firstName)
            && Objects.equals(_lastName, other._lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_firstName, _lastName);
    }
}
