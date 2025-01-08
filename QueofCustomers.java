package model;

import java.util.LinkedList;
import java.util.Queue;

public class QueofCustomers {
    private Queue<Customer> customerQueue;

    public QueofCustomers() {
        this.customerQueue = new LinkedList<>();
    }

    public void addCustomer(Customer customer) {
        customerQueue.offer(customer);
    }

    public Customer removeCustomer() {
        return customerQueue.poll();
    }

    public Customer peekNextCustomer() {
        return customerQueue.peek();
    }

    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }

    @Override
    public String toString() {
        return "QueofCustomers{" + customerQueue + '}';
    }

    // For GUI to display how many are in the queue
    public int size() {
        return customerQueue.size();
    }

    // Provide an unmodifiable list/array of customers if needed
    public Customer[] toArray() {
        return customerQueue.toArray(new Customer[0]);
    }
}
