package controller;

import model.*;
import view.ManagerView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class ManagerController {
    private Manager manager;
    private ManagerView view;
    private Customer currentCustomer;
    private Parcel currentParcel;

    public ManagerController(Manager manager, ManagerView view) {
        this.manager = manager;
        this.view = view;
        setupActionListeners();
    }

    private void setupActionListeners() {
        // Process Next Customer
        view.getProcessButton().addActionListener(e -> processNextCustomer());

        // Add New Customer
        view.getAddCustomerButton().addActionListener(e -> addNewCustomer());

        // Add New Parcel
        view.getAddParcelButton().addActionListener(e -> addNewParcel());

        // Mark Parcel as Processed
        view.getMarkProcessedButton().addActionListener(e -> markParcelAsProcessed());
    }

    private void processNextCustomer() {
        QueueOfCustomers queue = manager.getQueue();
        if (queue.isEmpty()) {
            view.updateStatus("No customers in queue");
            return;
        }

        currentCustomer = queue.removeCustomer();
        Log.getInstance().logEvent("Processing customer: " + currentCustomer.getName());
        
        // Find matching parcel
        currentParcel = manager.getParcelMap().getParcel(currentCustomer.getCustomerId());
        if (currentParcel == null) {
            // Add customer back to queue if no matching parcel found
            queue.addCustomer(currentCustomer);
            view.getCustomerQueuePanel().updateQueue(queue);
            view.updateStatus("No parcel found for customer: " + currentCustomer.getName());
            currentCustomer = null;
            return;
        }

        // Calculate fee
        double fee = manager.getWorker().calculateFee(currentParcel);
        
        // Update view
        view.getProcessingPanel().updateProcessing(currentCustomer, currentParcel, fee);
        view.getProcessingPanel().setStatus("Processing");
        view.updateStatus("Processing customer: " + currentCustomer.getName());
        
        // Update other panels
        view.getCustomerQueuePanel().updateQueue(queue);
        view.getParcelPanel().updateParcels(manager.getParcelMap());
    }

    private void addNewCustomer() {
        String name = JOptionPane.showInputDialog(view.getFrame(), "Enter customer name:");
        if (name == null || name.trim().isEmpty()) return;

        String id = JOptionPane.showInputDialog(view.getFrame(), "Enter customer ID:");
        if (id == null || id.trim().isEmpty()) return;

        Customer newCustomer = new Customer(name, id);
        manager.getQueue().addCustomer(newCustomer);
        manager.saveCustomerToCSV(newCustomer);  // Save to CSV file
        Log.getInstance().logEvent("Added new customer: " + name + " (ID: " + id + ")");
        
        view.getCustomerQueuePanel().updateQueue(manager.getQueue());
        view.updateStatus("Added new customer: " + name);
    }

    private void addNewParcel() {
        // Create a form panel
        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        JTextField idField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField lengthField = new JTextField();
        JTextField widthField = new JTextField();
        JTextField heightField = new JTextField();
        JTextField distanceField = new JTextField();

        panel.add(new JLabel("Parcel ID:"));
        panel.add(idField);
        panel.add(new JLabel("Weight (kg):"));
        panel.add(weightField);
        panel.add(new JLabel("Length (cm):"));
        panel.add(lengthField);
        panel.add(new JLabel("Width (cm):"));
        panel.add(widthField);
        panel.add(new JLabel("Height (cm):"));
        panel.add(heightField);
        panel.add(new JLabel("Distance (km):"));
        panel.add(distanceField);

        int result = JOptionPane.showConfirmDialog(view.getFrame(), panel, 
                "Add New Parcel", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String id = idField.getText().trim();
                if (id.isEmpty()) {
                    throw new IllegalArgumentException("Parcel ID cannot be empty");
                }

                int weight = Integer.parseInt(weightField.getText().trim());
                int length = Integer.parseInt(lengthField.getText().trim());
                int width = Integer.parseInt(widthField.getText().trim());
                int height = Integer.parseInt(heightField.getText().trim());
                double distance = Double.parseDouble(distanceField.getText().trim());

                if (weight <= 0 || length <= 0 || width <= 0 || height <= 0 || distance <= 0) {
                    throw new IllegalArgumentException("All measurements must be positive numbers");
                }

                Parcel newParcel = new Parcel(id, weight, length, width, height, distance);
                manager.getParcelMap().addParcel(newParcel);
                manager.saveParcelToCSV(newParcel);
                Log.getInstance().logEvent("Added new parcel: " + id);
                
                view.getParcelPanel().updateParcels(manager.getParcelMap());
                view.updateStatus("Added new parcel: " + id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view.getFrame(), 
                    "Please enter valid numbers for measurements.", 
                    "Invalid Input", 
                    JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(view.getFrame(), 
                    ex.getMessage(), 
                    "Invalid Input", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void markParcelAsProcessed() {
        if (currentParcel == null || currentCustomer == null) {
            view.updateStatus("No parcel being processed");
            return;
        }

        // Remove parcel from map and update CSV
        manager.getParcelMap().removeParcel(currentParcel.getParcelId());
        updateCSVFiles();
        
        Log.getInstance().logEvent("Marked parcel as processed: " + currentParcel.getParcelId() + 
                                 " for customer: " + currentCustomer.getName());
        
        view.getParcelPanel().updateParcels(manager.getParcelMap());
        view.getProcessingPanel().clear();
        view.updateStatus("Parcel processed and released");
        
        currentParcel = null;
        currentCustomer = null;
    }

    // Helper method to rewrite CSV files with current data
    private void updateCSVFiles() {
        try {
            // Rewrite Customers CSV
            try (FileWriter fw = new FileWriter("resources/Custs.csv", false)) {
                for (Customer customer : manager.getQueue().getAllCustomers()) {
                    fw.write(String.format("%s,%s%n", customer.getName(), customer.getCustomerId()));
                }
            }

            // Rewrite Parcels CSV
            try (FileWriter fw = new FileWriter("resources/Parcels.csv", false)) {
                for (Parcel parcel : manager.getParcelMap().getAllParcels()) {
                    fw.write(String.format("%s,%d,%d,%d,%d,%.1f%n",
                        parcel.getParcelId(),
                        parcel.getWeight(),
                        parcel.getLength(),
                        parcel.getWidth(),
                        parcel.getHeight(),
                        parcel.getDistance()));
                }
            }
        } catch (IOException e) {
            Log.getInstance().logEvent("Error updating CSV files: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
