package com.rental.vehicles;
import com.rental.customers.Customer;
import com.rental.services.Rentable;

public class Car extends Vehicle implements Rentable {
    // Constructor to initialize the car details
    public Car(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
    }

    // Calculates rental cost based on the number of days
    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalRate() * days;
    }

    // Checks if the car is available for rental
    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    // Rents the car to a customer for a specified number of days
    @Override
    public void rent(Customer customer, int days) {
        if (isAvailableForRental()) {
            setAvailable(false); // Mark the car as unavailable
            setRentedBy(customer); // Track the customer renting the car
            System.out.println("Car rented to " + customer.getName() + " for " + days + " days.");
        } else {
            System.out.println("Car is not available for rental.");
        }
    }
    
    // Returns the car and make it available again
    @Override
    public void returnVehicle() {
        setAvailable(true); // Mark the car as available
        setRentedBy(null); // Clear the customer information
        System.out.println("Car returned.");
    }
}
