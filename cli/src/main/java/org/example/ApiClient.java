package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080/api";
    private final HttpClient client = HttpClient.newHttpClient();

    public String createCar(String brand, String model, int year) throws Exception {

        String jsonBody = String.format(
                "{\"brand\":\"%s\",\"model\":\"%s\",\"year\":%d}",
                brand, model, year
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/cars"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 400) {
            throw new RuntimeException(
                    "API error " + response.statusCode() + ": " + response.body()
            );
        }

        return response.body();
    }

    public String getList() throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/cars"))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 400) {
            throw new RuntimeException("API error " + response.statusCode() + ": " + response.body());
        }
        return response.body();
    }

    public String FuelEntry(Long carId, double liters, double price, int odometer) throws Exception {
        String jsonBody = String.format(
                "{\"liters\":%d,\"price\":%d,\"odometer\":%d}",
                (int) liters, (int) price, odometer
        );

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(BASE_URL + "/cars/%d/fuel", carId)))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 400) {
            throw new RuntimeException("API error " + response.statusCode() + ": " + response.body());
        }

        return response.body();
    }

    public String FuelStats(Long carId) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(String.format(BASE_URL + "/cars/%d/fuel/stats", carId)))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() >= 400) {
            throw new RuntimeException("API error " + response.statusCode() + ": " + response.body());
        }
        return response.body();
    }


}
