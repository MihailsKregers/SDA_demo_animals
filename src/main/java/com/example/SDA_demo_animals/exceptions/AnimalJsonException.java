package com.example.SDA_demo_animals.exceptions;

public class AnimalJsonException extends Exception {
    public AnimalJsonException() {
    }

    public AnimalJsonException(String message) {
        super(message);
    }

    public AnimalJsonException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnimalJsonException(Throwable cause) {
        super(cause);
    }

    public AnimalJsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
