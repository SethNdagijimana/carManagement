package org.example.car_management.service;

import org.example.car_management.model.Car;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarService {

    private final Map<Long, Car> cars = new HashMap<>();
    private long idCounter = 1;

    // Create a new car
    public Car createCar(String brand, String model, int year) {
        Car car = new Car(idCounter++, brand, model, year);
        cars.put(car.getId(), car);
        return car;
    }

    // Get all cars
    public List<Car> getAllCars() {
        return new ArrayList<>(cars.values());
    }

    // Get car by ID
    public Optional<Car> getCarById(Long id) {
        return Optional.ofNullable(cars.get(id));
    }

    // Check existence (used by FuelService)
    public boolean carExists(Long id) {
        return cars.containsKey(id);
    }
}