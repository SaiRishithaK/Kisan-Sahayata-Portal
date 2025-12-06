//Course Based PRoject
//ModifyProfileFrame.java
package ui;

import data.AccessAndSaveFarmer;
import model.Farmer;

import javax.swing.*;
import java.awt.*;

public class ModifyProfileFrame extends JFrame {

    private Farmer farmer;

    private JTextField nameField, ageField, villageField, soilField, landField, aadharField;
    private JPasswordField passwordField;

    private AccessAndSaveFarmer service;

    public ModifyProfileFrame(Farmer f) {
        this.farmer = f;
        this.service = new AccessAndSaveFarmer();

        setTitle("Modify Profile");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(9, 2, 10, 10));

        // ----- PRELOAD EXISTING DATA -----

        add(new JLabel("Name:"));
        nameField = new JTextField(farmer.getName());
        add(nameField);

        add(new JLabel("Age:"));
        ageField = new JTextField(String.valueOf(farmer.getAge()));
        add(ageField);

        add(new JLabel("Village:"));
        villageField = new JTextField(farmer.getVillage());
        add(villageField);

        add(new JLabel("Soil Type:"));
        soilField = new JTextField(farmer.getSoilType());
        add(soilField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField(farmer.getPassword());
        add(passwordField);

        add(new JLabel("Land Size (acres):"));
        landField = new JTextField(String.valueOf(farmer.getLandSize()));
        add(landField);

        add(new JLabel("Aadhaar No:"));
        aadharField = new JTextField(String.valueOf(farmer.getAadharNum()));
        add(aadharField);

        JButton saveBtn = new JButton("Save");
        add(saveBtn);

        JButton backBtn = new JButton("Back");
        add(backBtn);

        // ----- BUTTON ACTIONS -----

        saveBtn.addActionListener(e -> saveChanges());
        backBtn.addActionListener(e -> this.dispose());

        setVisible(true);
    }

    private void saveChanges() {

        // update in object
        farmer.setName(nameField.getText());
        farmer.setAge(Integer.parseInt(ageField.getText()));
        farmer.setVillage(villageField.getText());
        farmer.setSoilType(soilField.getText());
        farmer.setPassword(new String(passwordField.getPassword()));
        farmer.setLandSize(Double.parseDouble(landField.getText()));
        farmer.setAadharNum(Long.parseLong(aadharField.getText()));

        // save to file
        service.updateFarmer(farmer);

        JOptionPane.showMessageDialog(this, "Profile updated successfully!");
        this.dispose();
    }
}
