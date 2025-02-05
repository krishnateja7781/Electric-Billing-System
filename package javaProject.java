package javaProjects.ElectricityBillingSystem;

import java.util.*;

class Customer {
    String id;
    String name;
    double meterReading;
    double bill;

    Customer(String id, String name) {
        this.id = id;
        this.name = name;
        this.meterReading = 0;
        this.bill = 0;
    }
}

// Your existing Java code continues here...
