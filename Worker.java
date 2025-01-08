package controller;

import model.Customer;
import model.Parcel;
import model.ParcelMap;
import model.QueofCustomers;
import utils.Log;
import view.MainFrame;


public class Manager {
    private QueofCustomers customerQueue;
    private ParcelMap parcelMap;
    private Worker worker;

    public Manager() {
        this.customerQueue = new QueofCustomers();
        this.parcelMap = new ParcelMap();
        this.worker = new Worker();

        // Hard-coded data loading for demo , ill add the csv later
        loadSampleData();
    }

    private void loadSampleData() {
        // Add some parcels
        parcelMap.addParcel(new Parcel("P001", 5.0, "London"));
        parcelMap.addParcel(new Parcel("P002", 2.0, "Birmingham"));
        parcelMap.addParcel(new Parcel("P003", 10.0, "Manchester"));

        // Add customers (IDs match the Parcel IDs for simplicity)
        customerQueue.addCustomer(new Customer("P001", "Alice"));
        customerQueue.addCustomer(new Customer("P002", "Bob"));
        customerQueue.addCustomer(new Customer("P003", "Charlie"));
    }

    public QueofCustomers getCustomerQueue() {
        return customerQueue;
    }

    public ParcelMap getParcelMap() {
        return parcelMap;
    }

    public Worker getWorker() {
        return worker;
    }

    public static void main(String[] args) {
        Manager manager = new Manager();

      
        new MainFrame(manager);
    }
}
