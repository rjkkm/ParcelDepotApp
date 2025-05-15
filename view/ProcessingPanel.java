package view;

import model.Customer;
import model.Parcel;

import javax.swing.*;
import java.awt.*;

public class ProcessingPanel extends JPanel {
    private JLabel customerLabel;
    private JLabel parcelLabel;
    private JLabel feeLabel;
    private JLabel statusLabel;
    private JTextArea detailsArea;

    public ProcessingPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createTitledBorder("Current Processing"));

        // Initialize components
        customerLabel = new JLabel("No customer being processed", SwingConstants.CENTER);
        parcelLabel = new JLabel("No parcel selected", SwingConstants.CENTER);
        feeLabel = new JLabel("Fee: £0.00", SwingConstants.CENTER);
        statusLabel = new JLabel("Status: Waiting", SwingConstants.CENTER);
        detailsArea = new JTextArea(4, 40);
        detailsArea.setEditable(false);

        // Create info panel
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoPanel.add(customerLabel);
        infoPanel.add(parcelLabel);
        infoPanel.add(feeLabel);
        infoPanel.add(statusLabel);

        // Add scroll pane for details
        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Processing Details"));

        // Add components to panel
        add(infoPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void updateProcessing(Customer customer, Parcel parcel, double fee) {
        if (customer != null) {
            customerLabel.setText("Customer: " + customer.getName() + " (ID: " + customer.getCustomerId() + ")");
        }
        if (parcel != null) {
            parcelLabel.setText("Parcel: " + parcel.getParcelId() + 
                              " (Weight: " + parcel.getWeight() + "kg, Distance: " + parcel.getDistance() + "km)");
            feeLabel.setText(String.format("Fee: £%.2f", fee));
            
            detailsArea.setText("Processing Details:\n" +
                              "Dimensions: " + parcel.getLength() + "x" + parcel.getWidth() + "x" + parcel.getHeight() + " cm\n" +
                              "Weight Factor: £" + String.format("%.2f", parcel.getWeight() * 0.5) + "\n" +
                              "Distance Factor: £" + String.format("%.2f", parcel.getDistance() * 0.2));
        }
    }

    public void setStatus(String status) {
        statusLabel.setText("Status: " + status);
    }

    public void clear() {
        customerLabel.setText("No customer being processed");
        parcelLabel.setText("No parcel selected");
        feeLabel.setText("Fee: £0.00");
        statusLabel.setText("Status: Waiting");
        detailsArea.setText("");
    }
}
