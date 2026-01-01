# Car Management & Fuel Tracking

This Technical Assignment project is a Java multi-module application consisting of:

    .A Spring Boot backend exposing REST APIs and a servlet
    .A Command Line Interface (CLI) that interacts with the backend over HTTP

The Application Allows users to:

    .Create and list cars
    .Add fuel entries
    .calculate fuel statistics (total fuel, total cost, average consumption)

# Project Structure

    car-management
    ├── backend/        # Spring Boot backend (REST + servlet)
    ├── cli/            # Java CLI client
    └── pom.xml         # Parent Maven POM

# Technologies Used
    
    .Spring Boot (REST controllers, servlet, exception handling)
    .Java HttpClient (CLI → backend communication)

# How to Run the Application

From the project root:

     Run mvn clean package:

# Run the backend First

     cd backend
     mvn spring-boot:run
     it will start on http://localhost:8080

# Run the CLI
    
    open a new terminal
    cd cli
    mvn compile 
    Run using classpath
    java -cp target/classes org.example.CliApplication <command>

# Available CLI Commands

# Create a car
    java -cp target/classes org.example.CliApplication create-car --brand Hyundai --model Tucson --year 2020 
    

# List all cars
    java -cp target/classes org.example.CliApplication list-cars

# Add a fuel entry
    java -cp target/classes org.example.CliApplication add-fuel --carId 1 --liter 40 --price 100000 --odometer 2000

# Get fuel statistics for a car
    java -cp target/classes org.example.CliApplication fuel-stats --carId 1


# Overview
    Uses a service layer to encapsulate business logic
    REST controllers handle HTTP endpoints
    A servlet is implemented alongside controllers to demonstrate manual request handling
    Global exception handling provides consistent error responses

CLI

    Parses command-line arguments
    Plain Java application
    Uses Java HttpClient to call backend REST endpoints
    Prints formatted results in the terminal

# Error Handling
    
    Backend returns appropriate HTTP status codes and messages
    CLI validates required arguments and reports API errors clearly

# Notes
    The backend must be running before executing CLI commands
    Data is stored in memory (no database)