package com.rental.vehicles;
import com.rental.customers.Customer;
import com.rental.services.Rentable;

public class Motorcycle extends Vehicle implements Rentable {
    // Constructor to initialize the motorcycle details
    public Motorcycle(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
    }

    // Calculate rental cost for motorcycles
    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalRate() * days * 0.9; // Apply 10% discount
    }

    // Checks if the motorcycle is available for rental
    @Override
    public boolean isAvailableForRental() { 
        return isAvailable(); 
    }

    // Rents the motorcycle to a customer for a specified number of days
    @Override
    public void rent(Customer customer, int days) {
        if (isAvailableForRental()) {
            setAvailable(false); 
            setRentedBy(customer); 
            System.out.println("Motorcycle rented to " + customer.getName() + " for " + days + " days.");
        } else {
            System.out.println("Motorcycle is not available for rental.");
        }
    }
    
    // Returns the motorcycle and make it available again
    @Override
    public void returnVehicle() {
        setAvailable(true); 
        setRentedBy(null); 
        System.out.println("Motorcycle returned.");
    }
}
