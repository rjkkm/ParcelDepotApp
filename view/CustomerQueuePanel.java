package view;

import model.Customer;
import model.QueueOfCustomers;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerQueuePanel extends JPanel {
    private JList<String> customerList;
    private DefaultListModel<String> listModel;

    public CustomerQueuePanel(QueueOfCustomers queue) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Customer Queue"));

        listModel = new DefaultListModel<>();
        customerList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(customerList);

        add(scrollPane, BorderLayout.CENTER);
        loadCustomers(queue);
    }

    private void loadCustomers(QueueOfCustomers queue) {
        listModel.clear();
        List<Customer> customers = queue.getAllCustomers();
        for (Customer customer : customers) {
            listModel.addElement(customer.toString());
        }
    }

    public void updateQueue(QueueOfCustomers queue) {
        loadCustomers(queue);
    }
}
