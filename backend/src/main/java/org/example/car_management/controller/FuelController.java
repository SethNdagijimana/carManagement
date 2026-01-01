package org.example.car_management.controller;

import org.example.car_management.model.FuelEntry;
import org.example.car_management.model.FuelStats;
import org.example.car_management.service.FuelService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars/{carId}/fuel")
public class FuelController {

    private final FuelService fuelService;

    public FuelController(FuelService fuelService){
        this.fuelService = fuelService;
    }

    @PostMapping
    public void addFuel(
            @PathVariable Long carId,
            @RequestBody FuelEntry fuelEntry
    ){
        fuelService.addFuel(
                carId,
                fuelEntry.getLiters(),
                fuelEntry.getPrice(),
                fuelEntry.getOdometer()

        );
    }

    @GetMapping("/stats")
    public FuelStats getFuelStats(@PathVariable Long carId){
        return fuelService.getFuelStats(carId);
    }
}
