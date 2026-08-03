//Course Based Project
//LoginFrame.java
package ui;

import data.AccessAndSaveFarmer;
import model.Farmer;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField idField;
    private JPasswordField passwordField;
    private AccessAndSaveFarmer service;

    public LoginFrame() {
        service = new AccessAndSaveFarmer();

        setTitle("Kisan Sahayata Portal - Login");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("KISAN SAHAYATA PORTAL", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // Center Panel
        JPanel center = new JPanel(new GridLayout(4, 1, 10, 10));

        JPanel p1 = new JPanel();
        p1.add(new JLabel("Farmer ID:"));
        idField = new JTextField(15);
        p1.add(idField);

        JPanel p2 = new JPanel();
        p2.add(new JLabel("Password:"));
        passwordField = new JPasswordField(15);
        p2.add(passwordField);

        center.add(p1);
        center.add(p2);

        add(center, BorderLayout.CENTER);

        // Buttons
        JPanel btnPanel = new JPanel();

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        btnPanel.add(loginBtn);
        btnPanel.add(registerBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // ACTION LISTENERS
        loginBtn.addActionListener(e -> loginAction());
        registerBtn.addActionListener(e -> openRegister());

        setVisible(true);
    }

    // LOGIN LOGIC
    private void loginAction() {
        String id = idField.getText();
        String pass = new String(passwordField.getPassword());

        Farmer f = service.authenticate(id, pass);

        if (f != null) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            new DashboardFrame(f).setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid ID or Password!");
        }
    }

    // OPEN REGISTER FRAME
    private void openRegister() {
        new RegisterFrame().setVisible(true);
        this.dispose();
    }
}
