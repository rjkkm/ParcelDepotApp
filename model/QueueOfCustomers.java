package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

/**
 * QueueOfCustomers class manages a queue of Customer objects.
 */
public class QueueOfCustomers {
    private Queue<Customer> customerQueue;

    /**
     * Constructor initializes the LinkedList as a Queue.
     */
    public QueueOfCustomers() {
        customerQueue = new LinkedList<>();
    }

    /**
     * Adds a Customer to the queue.
     *
     * @param customer The Customer object to add.
     */
    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
        Log.getInstance().logEvent("Customer added to queue: " + customer.getName());
    }

    /**
     * Removes the next Customer from the queue.
     *
     * @return The next Customer object if the queue is not empty; otherwise, null.
     */
    public Customer removeCustomer() {
        Customer customer = customerQueue.poll();
        if (customer != null) {
            Log.getInstance().logEvent("Customer removed from queue: " + customer.getName());
        }
        return customer;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty; otherwise, false.
     */
    public boolean isEmpty() {
        return customerQueue.isEmpty();
    }

    /**
     * Retrieves all Customers currently in the queue.
     *
     * @return A List of all Customer objects in the queue.
     */
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customerQueue);
    }
}
