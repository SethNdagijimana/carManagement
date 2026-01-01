package org.example.car_management.exception;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(Long carId) {
        super("car with id " + carId + " not found");
    }
}
