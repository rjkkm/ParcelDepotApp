package view;

import model.Parcel;
import model.ParcelMap;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class ParcelPanel extends JPanel {
    private JList<String> parcelList;
    private DefaultListModel<String> listModel;

    public ParcelPanel(ParcelMap parcelMap) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Parcels to be Processed"));

        listModel = new DefaultListModel<>();
        parcelList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(parcelList);

        add(scrollPane, BorderLayout.CENTER);
        loadParcels(parcelMap);
    }

    private void loadParcels(ParcelMap parcelMap) {
        listModel.clear();
        Collection<Parcel> parcels = parcelMap.getAllParcels();
        for (Parcel parcel : parcels) {
            listModel.addElement(parcel.toString());
        }
    }

    public void updateParcels(ParcelMap parcelMap) {
        loadParcels(parcelMap);
    }
}
