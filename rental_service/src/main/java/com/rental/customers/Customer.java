package com.rental.customers;

public class Customer {

    private final String customerId;
    private final String name;

    // Constructor to initialize customer details
    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    // Getter methods
    
    public String getId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}
