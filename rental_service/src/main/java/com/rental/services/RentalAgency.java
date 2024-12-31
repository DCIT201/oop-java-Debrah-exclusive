package com.rental.services;
import java.util.ArrayList;

import com.rental.customers.Customer;
import com.rental.vehicles.Vehicle;

public class RentalAgency {
    private final ArrayList<Vehicle> fleet;

    public RentalAgency(String agencyName) {
        this.fleet = new ArrayList<>();
    }

    // Adds a vehicle to the fleet
    public void addVehicle(Vehicle vehicle) {
        fleet.add(vehicle);
    }

    // Rents a vehicle to a customer
    public void rentVehicle(String vehicleId, Customer customer, int days) {
        for (Vehicle vehicle : fleet) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                if (vehicle instanceof Rentable rentable) {
                    rentable.rent(customer, days);
                    return;
                }
            }
        }
        System.out.println("Vehicle not found or not rentable.");
    }

    // Returns a rented vehicle
    public void returnVehicle(String vehicleId) {
        for (Vehicle vehicle : fleet) {
            if (vehicle.getVehicleId().equals(vehicleId)) {
                if (vehicle instanceof Rentable rentable) {
                    rentable.returnVehicle();
                    return;
                }
            }
        }
        System.out.println("Vehicle not found.");
    }
}
