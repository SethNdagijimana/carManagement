package org.example.car_management.config;

import org.example.car_management.service.FuelService;
import org.example.car_management.servlet.FuelStatsServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean<FuelStatsServlet> fuelStatsServlet(FuelService fuelService) {
        return new ServletRegistrationBean<>(
                new FuelStatsServlet(fuelService),
                "/servlet/fuel-stats"
        );
    }
}
