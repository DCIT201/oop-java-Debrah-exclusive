package com.rental.services;
import com.rental.customers.Customer;

public interface Rentable {
    // Method to rent a vehicle to a customer for a specified number of days
    void rent(Customer customer, int days);

    // Method to return a rented vehicle
    void returnVehicle();
}
