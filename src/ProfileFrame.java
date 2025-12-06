//Course Based Project
package ui;

import model.Farmer;

import javax.swing.*;
import java.awt.*;

public class ProfileFrame extends JFrame {

    private Farmer farmer;

    public ProfileFrame(Farmer f) {
        this.farmer = f;

        setTitle("Farmer Profile");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel with vertical box layout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add spacing above
        panel.add(Box.createVerticalStrut(20));

        // Title
        JLabel title = new JLabel("Farmer Profile");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(title);

        panel.add(Box.createVerticalStrut(20)); // space

        // Create all labels centered
        panel.add(centerLabel("Farmer ID: " + farmer.getFarmerID()));
        panel.add(centerLabel("Name: " + farmer.getName()));
        panel.add(centerLabel("Age: " + farmer.getAge()));
        panel.add(centerLabel("Village: " + farmer.getVillage()));
        panel.add(centerLabel("Soil Type: " + farmer.getSoilType()));
        panel.add(centerLabel("Land Size: " + farmer.getLandSize() + " acres"));
        panel.add(centerLabel("Aadhaar: " + farmer.getAadharNum()));

        panel.add(Box.createVerticalStrut(20));

        // Back Button
        JButton backBtn = new JButton("Back");
        backBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(backBtn);

        backBtn.addActionListener(e -> this.dispose());

        add(panel);
        setVisible(true);
    }

    private JLabel centerLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.PLAIN, 14));
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }
}
