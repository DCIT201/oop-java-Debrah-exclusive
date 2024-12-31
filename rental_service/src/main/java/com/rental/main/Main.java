package com.rental.main;

import java.util.Scanner;

import com.rental.customers.Customer;
import com.rental.services.Rentable;
import com.rental.vehicles.Car;
import com.rental.vehicles.Motorcycle;
import com.rental.vehicles.Truck;
import com.rental.vehicles.Vehicle;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Admin admin = new Admin();
            
            while (true) {
                // Display main menu
                System.out.println("\nWelcome to the Apex Vehicle Rentals Management System");
                System.out.println("------------------------------------------------");
                System.out.println("1. Secure Vehicle Rental");
                System.out.println("2. Vehicle Return");
                System.out.println("3. Add Vehicle to Inventory (Authorization Required)");
                System.out.println("4. View Fleet Report (Authorization Required)");
                System.out.println("0. Exit System");
                System.out.print("Please enter your selection: ");
                
                int action = getValidIntegerInput(scanner);
                
                if (action == 0) { 
                    System.out.println("Exiting the Apex Vehicle Rentals Management System. Goodbye!");
                    break;
                }

                // Verify admin password
                if (action == 3 || action == 4) {
                    System.out.print("Enter admin password: ");
                    String inputPassword = scanner.nextLine();
                    if (!admin.verifyPassword(inputPassword)) {
                        System.out.println("Incorrect password. Access denied.");
                        continue;
                    }
                }

                // Handle menu options based on user input
                switch (action) {
                    case 1 -> {
                        // Rent a vehicle
                        System.out.println("\nAvailable vehicles for rent:");
                        admin.displayAvailableVehicles();
                        
                        System.out.print("Enter vehicle ID to rent: ");
                        String rentVehicleId = scanner.nextLine();
                        
                        // Get the selected vehicle
                        Vehicle rentVehicle = admin.getVehicleById(rentVehicleId);
                        if (rentVehicle == null || !rentVehicle.isAvailable()) {
                            System.out.println("Vehicle not found or not available.");
                            break;
                        }
                        
                        // Gather customer details and rental duration
                        System.out.print("Enter your name: ");
                        String customerName = scanner.nextLine();
                        Customer customer = new Customer("CU" + System.currentTimeMillis(), customerName);
                        
                        System.out.print("Enter number of rental days: ");
                        int rentalDays = getValidIntegerInput(scanner);
                        
                        // Rent the vehicle and calculate cost
                        ((Rentable) rentVehicle).rent(customer, rentalDays);
                        System.out.printf("Rental cost: $%.2f%n", rentVehicle.calculateRentalCost(rentalDays));
                    }
                    
                    case 2 -> {
                        // Return a vehicle
                        System.out.print("Enter vehicle ID to return: ");
                        String returnVehicleId = scanner.nextLine();
                        
                        // Get the vehicle to be returned
                        Vehicle returnVehicle = admin.getVehicleById(returnVehicleId);
                        if (returnVehicle == null) {
                            System.out.println("Vehicle not found.");
                            break;
                        }
                        
                        // Return the vehicle
                        ((Rentable) returnVehicle).returnVehicle();
                    }

                    case 3 -> {
                        // Admin adds a vehicle to the fleet
                        System.out.println("\nSelect vehicle type to add:");
                        System.out.println("1. Car");
                        System.out.println("2. Motorcycle");
                        System.out.println("3. Truck");
                        System.out.print("Please enter your choice: ");
                        
                        // Input validation for vehicle type selection
                        int vehicleChoice = getValidIntegerInput(scanner);
                        
                        System.out.print("Enter vehicle ID: ");
                        String vehicleId = scanner.nextLine();
                        System.out.print("Enter model: ");
                        String model = scanner.nextLine();
                        System.out.print("Enter base rental rate: ");
                        double baseRentalRate = getValidDoubleInput(scanner);
                        
                        // Create the new vehicle based on user's choice
                        Vehicle newVehicle = null;
                        switch (vehicleChoice) {
                            case 1 -> newVehicle = new Car(vehicleId, model, baseRentalRate);
                            case 2 -> newVehicle = new Motorcycle(vehicleId, model, baseRentalRate);
                            case 3 -> newVehicle = new Truck(vehicleId, model, baseRentalRate);
                            default -> System.out.println("Invalid vehicle type.");
                        }

                        // Add the new vehicle to the fleet
                        if (newVehicle != null) {
                            admin.addVehicle(newVehicle);
                        }
                    }

                    case 4 -> {
                        // Admin views the vehicle fleet and rental information
                        admin.displayFleet();
                        admin.displayRentalInfo();
                    }
                }

                // Ask if the user wants to continue or exit
                System.out.print("\nDo you want to continue? (yes/no): ");
                String continueChoice = scanner.nextLine().toLowerCase();
                if (continueChoice.equals("no")) {
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                }
            }
        }
    }

    private static int getValidIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    private static double getValidDoubleInput(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
}
