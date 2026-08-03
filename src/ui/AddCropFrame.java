package ui;

import data.AccessAndSaveCrop;
import model.Farmer;
import model.FarmerCrop;

import javax.swing.*;
import java.awt.*;

public class AddCropFrame extends JFrame {
    
    public AddCropFrame(Farmer farmer)
    {
        setTitle("Add Crop");
        setSize(300, 250);
        setLayout(new GridLayout(5, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField seasonField = new JTextField();
        JTextField areaField = new JTextField();

        JButton addBtn = new JButton("Add");

        add(new JLabel("Crop Name:"));
        add(nameField);

        add(new JLabel("Season:"));
        add(seasonField);

        add(new JLabel("Area:"));
        add(areaField);

        add(new JLabel());
        add(addBtn);
        
        addBtn.addActionListener(e -> {
            try {
                FarmerCrop c = new FarmerCrop(
                        farmer.getFarmerID(),
                        nameField.getText(),
                        seasonField.getText(),
                        Double.parseDouble(areaField.getText())
                );

                AccessAndSaveCrop dao = new AccessAndSaveCrop();
                dao.addCrop(c);

                JOptionPane.showMessageDialog(this, "Crop Added!");
                dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        });

        setVisible(true);
    }
}
