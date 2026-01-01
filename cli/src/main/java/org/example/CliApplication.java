package org.example;

import java.util.Map;

public class CliApplication {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("No command provided");
            return;
        }

        String command = args[0];
        Map<String, String> params = CommandParser.parse(args);

        if ("create-car".equals(command)) {
            handleCreateCar(params);
        } else if ("add-fuel".equals(command)) {
            handleAddFuel(params);
        } else if ("fuel-stats".equals(command)) {
            handleFuelStats(params);

        } else if ("list-cars".equals(command)) {
            listCars();
        } else {
            System.out.println("Unknown command: " + command);
        }
    }

    private static void handleCreateCar(Map<String, String> params) {

        String brand = params.get("--brand");
        String model = params.get("--model");
        String year = params.get("--year");

        if (brand == null || model == null || year == null) {
            System.out.println("Missing arguments for create-car");
            return;
        }
        try {
            ApiClient apiClient = new ApiClient();
            String response = apiClient.createCar(
                    brand,
                    model,
                    Integer.parseInt(year)
            );

            System.out.println("Car created successfully:");
            System.out.println(response);

        } catch (Exception e) {
            System.err.println("Failed to create car: " + e.getMessage());
        }
    }

    private static void listCars() {
        try {
            ApiClient apiClient = new ApiClient();
            String response = apiClient.getList();


            System.out.println("Cars Fetched successfully:");
            System.out.println(response);
        } catch (Exception ex) {
            System.err.println("Failed to fetch cars: " + ex.getMessage());
        }

    }

    private static void handleAddFuel(Map<String, String> params) {
        String carId = params.get("--carId");
        String liter = params.get("--liter");
        String price = params.get("--price");
        String odometer = params.get("--odometer");

        if (carId == null || liter == null || price == null || odometer == null) {
            System.out.println("Missing arguments for add-fuel");
            return;
        }
        try {
            ApiClient apiClient = new ApiClient();
            String response = apiClient.FuelEntry(
                    Long.parseLong(carId),
                    Double.parseDouble(liter),
                    Double.parseDouble(price),
                    Integer.parseInt(odometer)
            );
            System.out.println("fuel added successfully:");
            System.out.println(response);
        } catch (Exception ex) {
            System.err.println("Failed to add fuel: " + ex.getMessage());
        }

    }



    private static void handleFuelStats(Map<String, String> params) {
        String carId = params.get("--carId");

        if (carId == null) {
            System.out.println("Missing arguments for fuel-stats");
            return;
        }

        try {
            ApiClient apiClient = new ApiClient();
            String response = apiClient.FuelStats(Long.parseLong(carId));


            response = response.replaceAll("[{}\"]", "");
            String[] parts = response.split(",");
            double totalFuel = 0, totalCost = 0, avgConsumption = 0;

            for (String part : parts) {
                String[] kv = part.split(":");
                switch (kv[0].trim()) {
                    case "totalFuel":
                        totalFuel = Double.parseDouble(kv[1].trim());
                        break;
                    case "totalCost":
                        totalCost = Double.parseDouble(kv[1].trim());
                        break;
                    case "averageConsumption":
                        avgConsumption = Double.parseDouble(kv[1].trim());
                        break;
                }
            }


            System.out.println("Fuel Stats Retrieved successfully:");
            System.out.printf("Total fuel: %.1f L%n", totalFuel);
            System.out.printf("Total cost: %.2f%n", totalCost);
            System.out.printf("Average consumption: %.1f L/100km%n", avgConsumption);

        } catch (Exception ex) {
            System.err.println("Failed to fetch fuel stats: " + ex.getMessage());
        }
    }
}