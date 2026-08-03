package ui;

import data.AccessAndSaveCrop;
import model.Farmer;
import model.FarmerCrop;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewCropsFrame extends JFrame {
    
    public ViewCropsFrame(Farmer farmer)
    {
        setTitle("My Crops");
        setSize(600, 300);

        AccessAndSaveCrop dao = new AccessAndSaveCrop();
        List<FarmerCrop> list = dao.getCrops(farmer.getFarmerID());

        String[] cols = {"ID", "Crop", "Season", "Area"};

        Object[][] data = new Object[list.size()][4];

        for (int i = 0; i < list.size(); i++) {
            FarmerCrop c = list.get(i);
            data[i][0] = c.getCropId();
            data[i][1] = c.getCropName();
            data[i][2] = c.getSeason();
            data[i][3] = c.getArea();
        }

        JTable table = new JTable(data, cols);

        add(new JScrollPane(table));

        setVisible(true);
    }
}
