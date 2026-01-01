package org.example.car_management.service;


import org.example.car_management.exception.CarNotFoundException;
import org.example.car_management.exception.InvalidFuelDataException;
import org.example.car_management.model.FuelEntry;
import org.example.car_management.model.FuelStats;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuelService {

    private final List<FuelEntry> fuelEntries = new ArrayList<>();
    private final CarService carService;

    public FuelService(CarService carService) {
        this.carService = carService;
    }


    public void addFuel(Long carId, double liters, double price, int odometer) {
        if (!carService.carExists(carId)) {
            throw new CarNotFoundException((carId));
        }

        if (liters <= 0 || price < 0 || odometer <=0 ) {
            throw new InvalidFuelDataException("Fuel Data invalid");
        }

        fuelEntries.add(new FuelEntry(carId, liters, price, odometer));

    }


    public FuelStats getFuelStats(Long carId) {
        if (!carService.carExists(carId)) {
            throw new CarNotFoundException(carId);
        }

        List<FuelEntry> entriesForCar = fuelEntries.stream()
                .filter(e -> e.getCarId().equals(carId))
                .sorted(Comparator.comparingInt(FuelEntry::getOdometer))
                .toList();

        double totalFuel = entriesForCar.stream()
                .mapToDouble(FuelEntry::getLiters)
                .sum();

        double totalCost = entriesForCar.stream()
                .mapToDouble(FuelEntry::getPrice)
                .sum();

        double distance = 0;
        if (entriesForCar.size() >= 2) {
            distance = entriesForCar.get(entriesForCar.size() - 1).getOdometer()
                    - entriesForCar.get(0).getOdometer();
        }

        double avgConsumption = distance > 0
                ? (totalFuel / distance) * 100
                : 0;

        return new FuelStats(totalFuel, totalCost, avgConsumption);
    }
}
