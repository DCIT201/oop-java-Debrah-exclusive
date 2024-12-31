package com.rental.vehicles;

import com.rental.customers.Customer;

public abstract class Vehicle {
    private final String vehicleId;
    private final String model;
    private final double baseRentalRate;
    private Customer rentedBy;
    private boolean isAvailable;

    public Vehicle(String vehicleId, String model, double baseRentalRate) {
        this.vehicleId = vehicleId;
        this.model = model;
        this.baseRentalRate = baseRentalRate;
        this.isAvailable = true;
    }

    // Getter methods
    public String getVehicleId() {
        return vehicleId;
    }

    public String getModel() {
        return model;
    }

    public double getBaseRentalRate() {
        return baseRentalRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Customer getRentedBy() {
        return rentedBy;
    }

    public void setRentedBy(Customer customer) {
        this.rentedBy = customer;
    }

    // Abstract method to calculate rental cost
    public abstract double calculateRentalCost(int days);

    // Abstract method to check availability for rental
    public abstract boolean isAvailableForRental();

    @Override
    public String toString() {
        return model + " (ID: " + vehicleId + ")";
    }
}
