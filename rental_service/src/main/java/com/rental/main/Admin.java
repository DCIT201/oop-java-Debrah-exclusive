package com.rental.main;

import java.util.ArrayList;
import java.util.List;

import com.rental.customers.Customer;
import com.rental.vehicles.Car;
import com.rental.vehicles.Motorcycle;
import com.rental.vehicles.Truck;
import com.rental.vehicles.Vehicle;

public class Admin {
    private final List<Vehicle> vehicleFleet;
    private final String password;

    public Admin() {
        this.vehicleFleet = new ArrayList<>();
        this.password = "Luxury";
        prepopulateFleet();
    }

    // Prepopulation of fleet with vehicles
    private void prepopulateFleet() {
        vehicleFleet.add(new Car("V001", "Tesla Model 3", 120.0));
        vehicleFleet.add(new Car("V002", "BMW 5 Series", 150.0));
        vehicleFleet.add(new Motorcycle("M001", "Harley-Davidson Pan America 1250", 80.0));
        vehicleFleet.add(new Motorcycle("M002", "BMW R 1250 GS", 85.0));
        vehicleFleet.add(new Truck("T001", "Ford F-150 Lightning", 200.0));
        vehicleFleet.add(new Truck("T002", "Rivian R1T", 210.0));
    }

    // Verification of admin password
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    // Adding a new vehicle to the fleet
    public void addVehicle(Vehicle vehicle) {
        vehicleFleet.add(vehicle);
        System.out.println(vehicle.getClass().getSimpleName() + " added to the fleet.");
    }

    // Displays all vehicles in the fleet
    public void displayFleet() {
        if (vehicleFleet.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
        } else {
            System.out.println("Vehicle Fleet:");
            for (Vehicle vehicle : vehicleFleet) {
                System.out.println(vehicle.getClass().getSimpleName() + " - ID: " + vehicle.getVehicleId() + ", Model: " + vehicle.getModel());
            }
        }
        System.out.println("\nUnavailable Vehicle(s):");
        for (Vehicle vehicle : vehicleFleet) {
            if (!vehicle.isAvailable()) {
                System.out.println(vehicle.getClass().getSimpleName() + " - ID: " + vehicle.getVehicleId() + 
                                   ", Model: " + vehicle.getModel() + ", Rate: $" + vehicle.getBaseRentalRate() + " per day");
            }
        }
    }

    // Displays available vehicles for rent
    public void displayAvailableVehicles() {
        boolean hasAvailableVehicles = false;
        for (Vehicle vehicle : vehicleFleet) {
            if (vehicle.isAvailable()) {
                System.out.println(vehicle.getClass().getSimpleName() + " - ID: " + vehicle.getVehicleId() + ", Model: " + vehicle.getModel() + ", Rate: $" + vehicle.getBaseRentalRate() + " per day");
                hasAvailableVehicles = true;
            }
        }
        if (!hasAvailableVehicles) {
            System.out.println("No available vehicles.");
        }
    }

    // Display rented vehicle information
    public void displayRentalInfo() {
        boolean hasRentedVehicles = false;
        System.out.println("\nRented Vehicles Information:");
        for (Vehicle vehicle : vehicleFleet) {
            if (!vehicle.isAvailable()) {
                Customer customer = vehicle.getRentedBy();
                System.out.println(
                    vehicle.getClass().getSimpleName() + " - ID: " + vehicle.getVehicleId() +
                    ", Rented By: " + customer.getName() 
                );
                hasRentedVehicles = true;
            }
        }
        if (!hasRentedVehicles) {
            System.out.println("No vehicles are currently rented.");
        }
    }

    // Get a vehicle by its ID
    public Vehicle getVehicleById(String vehicleId) {
        for (Vehicle vehicle : vehicleFleet) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }
}
