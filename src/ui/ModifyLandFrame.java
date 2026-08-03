package ui;

import model.Farmer;
import model.Land;
import data.AccessAndSaveFarmer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
        
public class ModifyLandFrame extends JFrame
{
    private Farmer farmer;
    private AccessAndSaveFarmer service;
    private JTable table;
    private DefaultTableModel model;
    
    public ModifyLandFrame(Farmer farmer) {

        this.farmer = farmer;
        service = new AccessAndSaveFarmer();

        setTitle("Modify Land Details");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // TABLE
        String[] cols = {"Survey No", "Village", "Mandal", "District", "Soil", "Size"};
        model = new DefaultTableModel(cols, 0);

        loadData();

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // BUTTONS
        JPanel panel = new JPanel();

        JButton editBtn = new JButton("Edit Selected");
        JButton backBtn = new JButton("Back");
        JButton addBtn = new JButton("Add Land");
        JButton deleteBtn = new JButton("Delete Land");
        
        panel.add(editBtn);
        panel.add(addBtn); 
        panel.add(deleteBtn); 
        panel.add(backBtn);

        add(panel, BorderLayout.SOUTH);

        // ACTIONS
        editBtn.addActionListener(e -> editSelected());
        addBtn.addActionListener(e -> addLand());
        deleteBtn.addActionListener(e -> deleteLand());
        backBtn.addActionListener(e -> {
            new ModifyProfileFrame(farmer).setVisible(true);
            dispose();
        });

        setVisible(true);
    }
    
    private void loadData() {
        model.setRowCount(0);

        List<Land> lands = service.getLandByFarmerId(farmer.getFarmerID());

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
    }
    
    private void editSelected() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first!");
            return;
        }

        String surveyNo = model.getValueAt(row, 0).toString();

        String village = JOptionPane.showInputDialog("Village:", model.getValueAt(row, 1));
        String mandal = JOptionPane.showInputDialog("Mandal:", model.getValueAt(row, 2));
        String district = JOptionPane.showInputDialog("District:", model.getValueAt(row, 3));
        String soil = JOptionPane.showInputDialog("Soil Type:", model.getValueAt(row, 4));
        String sizeStr = JOptionPane.showInputDialog("Land Size:", model.getValueAt(row, 5));

        try {
            double size = Double.parseDouble(sizeStr);

            boolean success = service.updateLand(
                    surveyNo, village, mandal, district, soil, size
            );
            if (success) {
                JOptionPane.showMessageDialog(this, "Updated Successfully!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Update Failed!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid Input!");
        }
    }
    
    private void addLand() 
    {
        JTextField surveyField = new JTextField();
        JTextField villageField = new JTextField();
        JTextField mandalField = new JTextField();
        JTextField districtField = new JTextField();
        JTextField soilField = new JTextField();
        JTextField sizeField = new JTextField();

        Object[] fields = {
            "Survey No:", surveyField,
            "Village:", villageField,
            "Mandal:", mandalField,
            "District:", districtField,
            "Soil Type:", soilField,
            "Land Size:", sizeField
        };
    
        int option = JOptionPane.showConfirmDialog(this, fields, "Add Land", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) 
        {
            try 
            {
                String survey = surveyField.getText();
                String village = villageField.getText();
                String mandal = mandalField.getText();
                String district = districtField.getText();
                String soil = soilField.getText();
                double size = Double.parseDouble(sizeField.getText());

                boolean success = service.addLand(
                    survey,
                    farmer.getFarmerID(),
                    village,
                    mandal,
                    district,
                    soil,
                    size
                );

                if (success) 
                {
                    JOptionPane.showMessageDialog(this, "Land Added Successfully!");
                    loadData(); 
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this, "Failed to Add Land!");
                }
            }
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(this, "Invalid Input!");
            }
        }
    }
    
    private void deleteLand() 
    {
        int row = table.getSelectedRow();
        if (row == -1) 
        {
            JOptionPane.showMessageDialog(this, "Select a row first!");
            return;
        }
        String surveyNo = model.getValueAt(row, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this land?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
            );
        if (confirm == JOptionPane.YES_OPTION) 
        {
            boolean success = service.deleteLand(surveyNo);
            if (success) 
            {
                JOptionPane.showMessageDialog(this, "Deleted Successfully!");
                loadData(); 
            } 
            else 
            {
                JOptionPane.showMessageDialog(this, "Delete Failed!");
            }   
        }
    }
}
