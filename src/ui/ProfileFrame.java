//Course Based Project
package ui;

import model.Farmer;
import model.Land;
import data.AccessAndSaveFarmer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProfileFrame extends JFrame {

    private Farmer farmer;
    private AccessAndSaveFarmer service;
    private JTable landTable;

   /* public ProfileFrame(Farmer f) {
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
    }*/
    
    public ProfileFrame(String farmerId) 
    {
        service = new AccessAndSaveFarmer();
        farmer = service.getFarmerById(farmerId);
        List<Land> lands = service.getLandByFarmerId(farmerId);
        
        setTitle("Farmer Profile");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JLabel title = new JLabel("FARMER PROFILE", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);
        
        JPanel farmerPanel = new JPanel(new GridLayout(4, 1));
        farmerPanel.setBorder(BorderFactory.createTitledBorder("Farmer Details"));

        farmerPanel.add(new JLabel("Farmer ID: " + farmer.getFarmerID()));
        farmerPanel.add(new JLabel("Name: " + farmer.getName()));
        farmerPanel.add(new JLabel("Age: " + farmer.getAge()));
        farmerPanel.add(new JLabel("Aadhaar: " + farmer.getAadharNum()));

        add(farmerPanel, BorderLayout.WEST);
        
        String[] cols = {"Survey No", "Village", "Mandal", "District", "Soil", "Size"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);

        for (Land l : lands) {
            model.addRow(new Object[]{
                    l.getSurveyNo(),
                    l.getVillage(),
                    l.getMandal(),
                    l.getDistrict(),
                    l.getSoilType(),
                    l.getLandSize()
            });
        }
        
        landTable = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(landTable);

        JPanel landPanel = new JPanel(new BorderLayout());
        landPanel.setBorder(BorderFactory.createTitledBorder("Land Details"));
        landPanel.add(scrollPane, BorderLayout.CENTER);

        add(landPanel, BorderLayout.CENTER);
        
        JPanel btnPanel = new JPanel();

        JButton backBtn = new JButton("Back");
        JButton modifyBtn = new JButton("Modify");

        btnPanel.add(backBtn);
        btnPanel.add(modifyBtn);

        add(btnPanel, BorderLayout.SOUTH);

        // ACTIONS
        backBtn.addActionListener(e -> {
            new DashboardFrame(farmer).setVisible(true);
            dispose();
        });

        modifyBtn.addActionListener(e -> {
            new ModifyProfileFrame(farmer).setVisible(true);
            dispose();
        });

        setVisible(true);
    }
        
}
