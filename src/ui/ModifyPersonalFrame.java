package ui;

import model.Farmer;
import data.AccessAndSaveFarmer;

import javax.swing.*;
import java.awt.*;

public class ModifyPersonalFrame extends JFrame
{
    private Farmer farmer;
    private AccessAndSaveFarmer service;

    private JTextField nameField, ageField, aadharField;
    private JPasswordField passwordField;

    public ModifyPersonalFrame(Farmer farmer) {

        this.farmer = farmer;
        service = new AccessAndSaveFarmer();

        setTitle("Modify Personal Details");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 2, 10, 10));

        // Fields
        add(new JLabel("Name:"));
        nameField = new JTextField(farmer.getName());
        add(nameField);

        add(new JLabel("Age:"));
        ageField = new JTextField(String.valueOf(farmer.getAge()));
        add(ageField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField(farmer.getPassword());
        add(passwordField);
        
        add(new JLabel("Aadhaar:"));
        aadharField = new JTextField(farmer.getAadharNum());
        add(aadharField);

        JButton updateBtn = new JButton("Update");
        JButton backBtn = new JButton("Back");

        add(updateBtn);
        add(backBtn);

        // ACTION
        updateBtn.addActionListener(e -> updateAction());

        backBtn.addActionListener(e -> {
            new ModifyProfileFrame(farmer).setVisible(true);
            dispose();
        });

        setVisible(true);
    }
    
    private void updateAction() {
        try {
            farmer.setName(nameField.getText());
            farmer.setAge(Integer.parseInt(ageField.getText()));
            farmer.setPassword(new String(passwordField.getPassword()));
            farmer.setAadharNum(aadharField.getText());

            boolean success = service.updateFarmer(farmer);

            if (success) {
                JOptionPane.showMessageDialog(this, "Updated Successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Update Failed!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }
}
