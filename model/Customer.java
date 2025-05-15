package model;

/**
 * Customer class represents a customer with a name and ID.
 */
public class Customer {
    private String name;
    private String customerId;

    /**
     * Constructor initializes the Customer with a name and ID.
     *
     * @param name       The name of the customer.
     * @param customerId The unique identifier for the customer.
     */
    public Customer(String name, String customerId) {
        this.name = name;
        this.customerId = customerId;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "Customer Name: " + name + ", ID: " + customerId;
    }
}
