package javaProjects.ElectricityBillingSystem;

import java.util.*;

public class ElectricBillingSystem {
    
    // Customer class to store customer information
    static class Customer {
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

        public void recordMeterReading(double reading) {
            this.meterReading = reading;
        }

        public void generateBill() {
            double unitsConsumed = this.meterReading;
            if (unitsConsumed <= 100) {
                this.bill = unitsConsumed * 5; // 5 units per kWh
            } else if (unitsConsumed <= 200) {
                this.bill = (100 * 5) + ((unitsConsumed - 100) * 7); // 7 units per kWh
            } else {
                this.bill = (100 * 5) + (100 * 7) + ((unitsConsumed - 200) * 10); // 10 units per kWh
            }
        }
    }

    // Main class to manage customers and billing
    private static Map<String, Customer> customerDatabase = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Display welcome message
        System.out.println("Welcome to the Electric Billing System");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add a Customer");
            System.out.println("2. Record Meter Reading");
            System.out.println("3. Generate Bill");
            System.out.println("4. View Customer Details");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addCustomer();
                    break;
                case 2:
                    recordMeterReading();
                    break;
                case 3:
                    generateBill();
                    break;
                case 4:
                    viewCustomerDetails();
                    break;
                case 5:
                    System.out.println("Thank you for using the Electric Billing System!");
                    return; // Exit the program
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Function to add a customer
    private static void addCustomer() {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        if (!customerDatabase.containsKey(id)) {
            Customer customer = new Customer(id, name);
            customerDatabase.put(id, customer);
            System.out.println("Customer added successfully!");
        } else {
            System.out.println("Customer with this ID already exists.");
        }
    }

    // Function to record meter reading for a customer
    private static void recordMeterReading() {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();

        Customer customer = customerDatabase.get(id);
        if (customer != null) {
            System.out.print("Enter Meter Reading: ");
            double reading = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            customer.recordMeterReading(reading);
            System.out.println("Meter reading recorded successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Function to generate bill for a customer
    private static void generateBill() {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();

        Customer customer = customerDatabase.get(id);
        if (customer != null) {
            customer.generateBill();
            System.out.println("Bill for Customer " + customer.name + ": " + customer.bill);
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Function to view customer details
    private static void viewCustomerDetails() {
        System.out.print("Enter Customer ID: ");
        String id = scanner.nextLine();

        Customer customer = customerDatabase.get(id);
        if (customer != null) {
            System.out.println("Customer ID: " + customer.id);
            System.out.println("Customer Name: " + customer.name);
            System.out.println("Meter Reading: " + customer.meterReading);
            System.out.println("Bill Amount: " + customer.bill);
        } else {
            System.out.println("Customer not found.");
        }
    }
}
