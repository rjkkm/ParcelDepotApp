package view;

import model.Manager;
import model.ParcelMap;
import model.QueueOfCustomers;

import javax.swing.*;
import java.awt.*;


public class ManagerView {
    private JFrame frame;
    private ParcelPanel parcelPanel;
    private CustomerQueuePanel customerQueuePanel;
    private ProcessingPanel processingPanel;
    private JButton processButton;
    private JButton addCustomerButton;
    private JButton addParcelButton;
    private JButton markProcessedButton;
    private JLabel statusLabel;

    public ManagerView(Manager manager) {
        frame = new JFrame("Depot Worker Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Initialize components
        parcelPanel = new ParcelPanel(manager.getParcelMap());
        customerQueuePanel = new CustomerQueuePanel(manager.getQueue());
        processingPanel = new ProcessingPanel();
        
        // Initialize buttons
        processButton = new JButton("Process Next Customer");
        addCustomerButton = new JButton("Add New Customer");
        addParcelButton = new JButton("Add New Parcel");
        markProcessedButton = new JButton("Mark Parcel as Processed");
        statusLabel = new JLabel("Ready to process customers", SwingConstants.CENTER);
        
        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.add(processButton);
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(addParcelButton);
        buttonPanel.add(markProcessedButton);

        // Create top panel with buttons and status
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(buttonPanel, BorderLayout.CENTER);
        topPanel.add(statusLabel, BorderLayout.SOUTH);

        // Create center panel with parcels and customers
        JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        centerPanel.add(parcelPanel);
        centerPanel.add(customerQueuePanel);

        // Create bottom panel with processing details
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        bottomPanel.add(processingPanel, BorderLayout.CENTER);

        // Add panels to frame
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerPanel, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Getters for Controller Access
    public JFrame getFrame() {
        return frame;
    }

    public ParcelPanel getParcelPanel() {
        return parcelPanel;
    }

    public CustomerQueuePanel getCustomerQueuePanel() {
        return customerQueuePanel;
    }

    public ProcessingPanel getProcessingPanel() {
        return processingPanel;
    }

    public JButton getProcessButton() {
        return processButton;
    }

    public JButton getAddCustomerButton() {
        return addCustomerButton;
    }

    public JButton getAddParcelButton() {
        return addParcelButton;
    }

    public JButton getMarkProcessedButton() {
        return markProcessedButton;
    }

    public void updateStatus(String status) {
        statusLabel.setText(status);
    }
}
