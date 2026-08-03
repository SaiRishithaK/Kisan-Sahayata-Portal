//Course Based project
//RegisterFrame.java
package ui;

import data.AccessAndSaveFarmer;
import model.Farmer;
import model.Land;
import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField nameField, ageField, villageField, soilField, landField, aadharField, surveyField, mandalField, districtField;
    private final JPasswordField passwordField;
    private final AccessAndSaveFarmer service;

    public RegisterFrame() {

        service = new AccessAndSaveFarmer();

        setTitle("Farmer Registration");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("Farmer Registration", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel farmerPanel = new JPanel(new GridBagLayout());
        farmerPanel.setBorder(BorderFactory.createTitledBorder("Farmer Details"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        farmerPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        farmerPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        farmerPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        farmerPanel.add(passwordField, gbc);
        
        gbc.gridx = 0; gbc.gridy++;
        farmerPanel.add(new JLabel("Age:"), gbc);
        gbc.gridx = 1;
        ageField = new JTextField(15);
        farmerPanel.add(ageField, gbc);

        gbc.gridx = 0; gbc.gridy++;
        farmerPanel.add(new JLabel("Aadhaar:"), gbc);
        gbc.gridx = 1;
        aadharField = new JTextField(15);
        farmerPanel.add(aadharField, gbc);
        
        JPanel landPanel = new JPanel(new GridBagLayout());
        landPanel.setBorder(BorderFactory.createTitledBorder("Land Details"));
        
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(5, 5, 5, 5);
        gbc2.fill = GridBagConstraints.HORIZONTAL;

        gbc2.gridx = 0; gbc2.gridy = 0;
        landPanel.add(new JLabel("Survey No:"), gbc2);
        gbc2.gridx = 1;
        surveyField = new JTextField(15);
        landPanel.add(surveyField, gbc2);

        gbc2.gridx = 0; gbc2.gridy++;
        landPanel.add(new JLabel("Village:"), gbc2);
        gbc2.gridx = 1;
        villageField = new JTextField(15);
        landPanel.add(villageField, gbc2);

        gbc2.gridx = 0; gbc2.gridy++;
        landPanel.add(new JLabel("Mandal:"), gbc2);
        gbc2.gridx = 1;
        mandalField = new JTextField(15);
        landPanel.add(mandalField, gbc2);

        gbc2.gridx = 0; gbc2.gridy++;
        landPanel.add(new JLabel("District:"), gbc2);
        gbc2.gridx = 1;
        districtField = new JTextField(15);
        landPanel.add(districtField, gbc2);

        gbc2.gridx = 0; gbc2.gridy++;
        landPanel.add(new JLabel("Soil Type:"), gbc2);
        gbc2.gridx = 1;
        soilField = new JTextField(15);
        landPanel.add(soilField, gbc2);

        gbc2.gridx = 0; gbc2.gridy++;
        landPanel.add(new JLabel("Land Size (acres):"), gbc2);
        gbc2.gridx = 1;
        landField = new JTextField(15);
        landPanel.add(landField, gbc2);
        
        JPanel buttonPanel = new JPanel();

        JButton registerBtn = new JButton("Register");
        JButton backBtn = new JButton("Back");

        buttonPanel.add(registerBtn);
        buttonPanel.add(backBtn);
        
        mainPanel.add(farmerPanel);
        mainPanel.add(Box.createVerticalStrut(10)); // spacing
        mainPanel.add(landPanel);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

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
        String password = new String(passwordField.getPassword());
        String aadhar = aadharField.getText();
        String surveyNo = surveyField.getText();
        String village = villageField.getText();
        String mandal = mandalField.getText();
        String district = districtField.getText();
        String soil = soilField.getText();
        double land = Double.parseDouble(landField.getText());

        // Generate Farmer ID
        String farmerId = service.generateNewFarmerId();

        // Create farmer object in correct order
        Farmer f = new Farmer(farmerId,name,age,password,aadhar);
        Land l = new Land(surveyNo,farmerId,village,mandal,district,soil,land);

        // Save to file
        service.addFarmer(f,l);

        JOptionPane.showMessageDialog(this,
                "Registration Successful!\nYour Farmer ID is: " + farmerId);

        new LoginFrame().setVisible(true);
        this.dispose();
    }
}

