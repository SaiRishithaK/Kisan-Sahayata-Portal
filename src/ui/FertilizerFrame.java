package ui;

import data.FertilizerData;
import data.SubsidyData;
import model.Subsidy;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FertilizerFrame extends JFrame {
    
    private JTable fertTable,shopTable,subTable;
    DefaultTableModel fertModel;
    DefaultTableModel shopModel, subModel;
    
    public FertilizerFrame()
    {
        setTitle("Fertilizers Infornation");
        setSize(700, 500);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        
        JPanel top = new JPanel();
        JTextField cropField = new JTextField(15);
        JButton searchBtn = new JButton("Search");

        top.add(new JLabel("Crop: "));
        top.add(cropField);
        top.add(searchBtn);

        add(top, BorderLayout.NORTH);
        
        fertModel = new DefaultTableModel(
                new String[]{"ID", "Fertilizer", "Quantity"}, 0);
        fertTable = new JTable(fertModel);
        fertTable.setRowHeight(25);

        shopModel = new DefaultTableModel(
                new String[]{"Shop | Location | Contact"}, 0);
        shopTable = new JTable(shopModel);
        shopTable.setRowHeight(25);
        
        subModel = new DefaultTableModel(
                new String[]{"Subsidy Name", "Percentage", "Description"}, 0);
        subTable = new JTable(subModel);
        subTable.setRowHeight(25);

        JSplitPane split1 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                new JScrollPane(fertTable),
                new JScrollPane(shopTable)
            );
        
        JSplitPane split2 = new JSplitPane(
                JSplitPane.VERTICAL_SPLIT,
                split1,
                new JScrollPane(subTable)
        );
        
        split1.setDividerLocation(180);
        split2.setDividerLocation(350);
        add(split2, BorderLayout.CENTER);
        
        searchBtn.addActionListener(e -> {

            fertModel.setRowCount(0);
            shopModel.setRowCount(0);
            subModel.setRowCount(0);

            String crop = cropField.getText().trim();
            
            if (crop.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter crop name!");
                return;
            }

            FertilizerData dao = new FertilizerData();
            List<Object[]> list = dao.getFertilizers(crop);
            
            if (list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No fertilizers found!");
                return;
            }

            for (Object[] row : list) {
                fertModel.addRow(row);
            }
        });
         
        fertTable.getSelectionModel().addListSelectionListener(e -> {

            if (e.getValueIsAdjusting()) return;

            int row = fertTable.getSelectedRow();
            if (row == -1) return;

            int fertId = (int) fertModel.getValueAt(row, 0);

            shopModel.setRowCount(0);
            subModel.setRowCount(0);

            FertilizerData dao = new FertilizerData();
            List<String> shops = dao.getAvailability(fertId);

            for (String s : shops) {
                shopModel.addRow(new Object[]{s});
            }
        
            SubsidyData sdao = new SubsidyData();
            List<Subsidy> subs = sdao.getSubsidies(fertId);

            for (Subsidy s : subs) {
                subModel.addRow(new Object[]{
                s.getName(),
                s.getPercentage() + "%",
                s.getDescription()
                });
            }
        });

        setVisible(true);
    }
}
