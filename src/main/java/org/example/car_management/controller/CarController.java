package org.example.car_management.controller;

import org.example.car_management.model.Car;
import org.example.car_management.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(
                car.getBrand(),
                car.getModel(),
                car.getYear()
        );
    }

    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }
}