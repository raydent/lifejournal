package com.example.lifejournal;

public enum CustomErrors {
    USER_WITH_SUCH_USERNAME_FOUND (new CustomException("CE-001", "User with such username was found.")),
    PASSWORD_HAS_INVALID_LENGTH (new CustomException("CE-002", "Password has invalid length")),
    INVALID_PASSWORD (new CustomException("CE-003", "Password is incorrect")),
    NO_USER_FOUND (new CustomException("CE-004", "No such user found"));

    private CustomException exception;

    CustomErrors(CustomException exception) {
        this.exception = exception;
    }

    public CustomException getException() {
        return this.exception;
    }
}
