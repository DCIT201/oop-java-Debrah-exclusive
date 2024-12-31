package com.rental.vehicles;
import com.rental.customers.Customer;
import com.rental.services.Rentable;

public class Truck extends Vehicle implements Rentable {
    // Constructor to initialize the truck details
    public Truck(String vehicleId, String model, double baseRentalRate) {
        super(vehicleId, model, baseRentalRate);
    }

    // Calculate rental cost for trucks
    @Override
    public double calculateRentalCost(int days) {
        return getBaseRentalRate() * days * 1.2; // 20% higher rate for trucks
    }

    // Checks if the truck is available for rental
    @Override
    public boolean isAvailableForRental() {
        return isAvailable();
    }

    // Rents the truck to a customer for a specified number of days
    @Override
    public void rent(Customer customer, int days) {
        if (isAvailableForRental()) {
            setAvailable(false); 
            setRentedBy(customer); 
            System.out.println("Truck rented to " + customer.getName() + " for " + days + " days.");
        } else {
            System.out.println("Truck is not available for rental.");
        }
    }
    
    // Returns the truck and make it available again
    @Override
    public void returnVehicle() {
        setAvailable(true); 
        setRentedBy(null); 
        System.out.println("Truck returned.");
    }
}
