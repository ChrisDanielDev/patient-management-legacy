package com.example.patient.management;

public class Result<T> {

    private final boolean success;
    private final T value;
    private final String message;

    public Result(boolean success, T value, String message) {
        this.success = success;
        this.value = value;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public T getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(true, value, null);
    }

    public static <T> Result<T> failure(String message) {
        return new Result<>(false, null, message);
    }
}
