package org.example.car_management.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.example.car_management.exception.CarNotFoundException;
import org.example.car_management.model.FuelStats;
import org.example.car_management.service.FuelService;

import java.io.IOException;

public class FuelStatsServlet extends HttpServlet {

    private FuelService fuelService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public FuelStatsServlet(FuelService fuelService) {
        this.fuelService = fuelService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("application/json");

        try {
            String carIdParam = request.getParameter("carId");

            if (carIdParam == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\":\"carId is required\"}");
                return;
            }

            Long carId = Long.parseLong(carIdParam);

            FuelStats stats = fuelService.getFuelStats(carId);

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write(
                    objectMapper.writeValueAsString(stats)
            );

        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"carId must be a number\"}");

        } catch (CarNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            response.getWriter().write(
                    "{\"error\":\"" + e.getMessage() + "\"}"
            );

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"Unexpected server error\"}");
        }
    }
}
