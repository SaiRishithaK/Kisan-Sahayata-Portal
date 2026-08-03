//Course Based Project
//CropDetialsFrame.java
package ui;

import data.CropInfoManager;
import model.Crop;
import model.Farmer;

import javax.swing.*;
import java.awt.*;

public class CropDetailsFrame extends JFrame {

    public CropDetailsFrame(Farmer farmer) {

        setTitle("Crop Details");
        setSize(550, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Use BorderLayout for bigger output area
        setLayout(new BorderLayout(10, 10));

        // ------------ TOP PANEL (Inputs) ------------
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 1, 5, 5));

        JTextField cropField = new JTextField(12);   // smaller box
        JTextField soilField = new JTextField(12);   // now user types it manually

        JButton showBtn = new JButton("Get Details");

        inputPanel.add(new JLabel("Enter Crop Name:"));
        inputPanel.add(cropField);

        inputPanel.add(new JLabel("Enter Soil Type:"));   // CHANGED (no auto)
        inputPanel.add(soilField);

        inputPanel.add(showBtn);

        add(inputPanel, BorderLayout.NORTH);

        // ------------ RESULT ------------
        JTextArea result = new JTextArea();
        result.setFocusable(false);
        result.setEditable(false);
        result.setFont(new Font("Arial", Font.PLAIN, 15));

        JScrollPane scroll = new JScrollPane(result);
        scroll.setPreferredSize(new Dimension(500, 400));   // large output box
        add(scroll, BorderLayout.CENTER);

        // ------------ ACTION ------------
        showBtn.addActionListener(e -> {

            String cropName = cropField.getText().trim();
            String soil = soilField.getText().trim();

            if (cropName.isEmpty() || soil.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields!");
                return;
            }

            CropInfoManager manager = new CropInfoManager();
            Crop c = manager.getCropInfo(cropName, soil);

            if (c == null) {
                result.setText("No information found for this crop.\nPlease check spelling or try another crop.");
                return;
            }

            double price = manager.getMarketPriceForCrop(cropName);

            result.setText(
                    "	Crop Details \n\n" +
                    "Crop: " + c.getName() + "\n" +
                    "Season: " + c.getSeason() + "\n" +
                    "Days to Harvest: " + c.getDaysToHarvest() + " days\n" +
                    "Suggested Fertilizer: " + c.getFertilizer() + "\n" +
                    "Tips: " + c.getTip() + "\n\n" +
                    "Current Market Price: " +
                            (price == -1 ? "Not Available" : "₹" + price + " / quintal")
            );
        });

        setVisible(true);
    }
}
