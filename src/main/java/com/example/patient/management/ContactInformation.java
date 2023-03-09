package com.example.patient.management;
import java.util.Optional;
import java.util.regex.Pattern;

public class ContactInformation {
    private final String email;
    private final String phoneNumber;

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private ContactInformation(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static Result<String> validateEmail(String email) {
        Optional<String> emailOptional = Optional.ofNullable(email);
        

        if (emailOptional.map(String::length).orElse(0) > 100) {
            return Result.failure("Email is too long.");
        }
    
        if (email != null && !email.isEmpty()) {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";

            Pattern emailPattern = Pattern.compile(emailRegex);
            if(!emailPattern.matcher(email).matches()){
            return Result.failure("Email is not valid.");
            }
        }
    
        return Result.success(email);
    }
    
    public static Result<String> validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return Result.failure("Phone Number is required.");
        }
    
        if (phoneNumber.length() > 100) {
            return Result.failure("Phone number is too long.");
        }
    
        String phoneRegex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
    
        if (!phonePattern.matcher(phoneNumber).matches()) {
            return Result.failure("Phone number is not valid.");
        }
    
        return Result.success(phoneNumber);
    }

    public static Result<ContactInformation> create(String email, String phoneNumber) {
        Result<String> emailResult = validateEmail(email);
        
        if (!emailResult.isSuccess()) {
            return Result.failure(emailResult.getMessage());
        }
        
        Result<String> phoneResult = validatePhoneNumber(phoneNumber);
        if (!phoneResult.isSuccess()) {
            return Result.failure(phoneResult.getMessage());
        }

        return Result.success(new ContactInformation(email, phoneNumber));
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Phone Number: ").append(this.phoneNumber);
        if (this.email != null && !this.email.isEmpty()) {
            sb.append(", Email: ").append(this.email);
        }
        return sb.toString();
    }
}
