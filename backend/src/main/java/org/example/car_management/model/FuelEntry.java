package org.example.car_management.model;

public class FuelEntry {
    private Long carId;
    private double liters;
    private double price;
    private int odometer;

    public FuelEntry(Long carId, double liters, double price, int odometer) {
        this.carId = carId;
        this.liters = liters;
        this.price = price;
        this.odometer = odometer;
    }

    public Long getCarId() {
        return carId;
    }

    public double getLiters() {
        return liters;
    }

    public double getPrice() {
        return price;
    }

    public int getOdometer() {
        return odometer;
    }
}