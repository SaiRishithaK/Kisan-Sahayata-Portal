//Course Based project
//RegisterFrame.java
package ui;

import data.AccessAndSaveFarmer;
import model.Farmer;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField nameField, ageField, villageField, soilField, landField, aadharField;
    private JPasswordField passwordField;

    private AccessAndSaveFarmer service;

    public RegisterFrame() {

        service = new AccessAndSaveFarmer();

        setTitle("Farmer Registration");
        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridLayout(9, 2, 10, 10));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Age:"));
        ageField = new JTextField();
        add(ageField);

        add(new JLabel("Village:"));
        villageField = new JTextField();
        add(villageField);

        add(new JLabel("Soil Type:"));
        soilField = new JTextField();
        add(soilField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Land Size (acres):"));
        landField = new JTextField();
        add(landField);

        add(new JLabel("Aadhaar Number:"));
        aadharField = new JTextField();
        add(aadharField);

        JButton registerBtn = new JButton("Register");
        add(registerBtn);

        JButton backBtn = new JButton("Back");
        add(backBtn);

        // ACTIONS
        registerBtn.addActionListener(e -> registerAction());
        backBtn.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });

        setVisible(true);
    }

    private void registerAction() {

        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String village = villageField.getText();
        String soil = soilField.getText();
        String password = new String(passwordField.getPassword());
        double land = Double.parseDouble(landField.getText());
        long aadhar = Long.parseLong(aadharField.getText());

        // Generate Farmer ID
        String farmerId = service.generateNewFarmerId();

        // Create farmer object in correct order
        Farmer f = new Farmer(farmerId,age, name,village,soil,password,land, aadhar);

        // Save to file
        service.addFarmer(f);

        JOptionPane.showMessageDialog(this,
                "Registration Successful!\nYour Farmer ID is: " + farmerId);

        new LoginFrame().setVisible(true);
        this.dispose();
    }
}

