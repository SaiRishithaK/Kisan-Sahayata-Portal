//Course Based Project
//DashboardFrame.java
package ui;

import model.Farmer;
import threads.MarketPriceThread;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private Farmer farmer;

    public DashboardFrame(Farmer f) {
        this.farmer = f;
	new threads.NotificationThread(farmer.getFarmerID()).start();
	new MarketPriceThread().start();


        setTitle("Farmer Dashboard");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title Section
        JLabel title = new JLabel("KISAN SAHAYATA PORTAL", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // Farmer Details on top
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(2, 1));
        topPanel.add(new JLabel("Farmer ID: " + farmer.getFarmerID(), SwingConstants.CENTER));
        topPanel.add(new JLabel("Name: " + farmer.getName(), SwingConstants.CENTER));
        add(topPanel, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(9, 1, 10, 10));

        JButton profileBtn = new JButton("View Profile");
        JButton modifyBtn = new JButton("Modify Profile");
        JButton loansBtn = new JButton("Loans");
        JButton subsidiesBtn = new JButton("Subsidies");
        JButton fertilizersBtn = new JButton("Fertilizers");
        JButton cropsBtn = new JButton("Crop Details");
        JButton marketBtn = new JButton("Market Prices");
        JButton notifBtn = new JButton("Notifications");
        JButton logoutBtn = new JButton("Logout");

        buttonPanel.add(profileBtn);
        buttonPanel.add(modifyBtn);
        buttonPanel.add(loansBtn);
        buttonPanel.add(subsidiesBtn);
        buttonPanel.add(fertilizersBtn);
        buttonPanel.add(cropsBtn);
        buttonPanel.add(marketBtn);
        buttonPanel.add(notifBtn);
        buttonPanel.add(logoutBtn);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        profileBtn.addActionListener(e -> new ProfileFrame(farmer).setVisible(true));
        modifyBtn.addActionListener(e -> new ModifyProfileFrame(farmer).setVisible(true));
        loansBtn.addActionListener(e -> new LoansFrame(farmer).setVisible(true));

        subsidiesBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Subsidies feature coming soon!"));

        fertilizersBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Fertilizer availability feature coming soon!"));

        cropsBtn.addActionListener(e ->
                new CropDetailsFrame(farmer).setVisible(true));

        marketBtn.addActionListener(e ->
                new MarketPricesFrame().setVisible(true));

        notifBtn.addActionListener(e ->
                new NotificationsFrame(farmer).setVisible(true));

        logoutBtn.addActionListener(e -> {
            new LoginFrame().setVisible(true);
            this.dispose();
        });
    }
}
