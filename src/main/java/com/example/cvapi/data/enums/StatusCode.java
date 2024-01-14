package com.example.cvapi.data.enums;

public enum StatusCode {
    INFO(100),

    OK(200),

    REDIRECT(300),

    BAD_CLIENT(400),

    SERVER_ERROR(500);


    private final int statusCode;

    StatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
