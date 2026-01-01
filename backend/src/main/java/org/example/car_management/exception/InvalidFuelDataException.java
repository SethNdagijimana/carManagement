package org.example.car_management.exception;

public class InvalidFuelDataException extends RuntimeException{
    public InvalidFuelDataException(String message) {
        super(message);
    }
}
