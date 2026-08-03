package ui;

import model.Farmer;
import javax.swing.*;
import java.awt.*;

public class CropMainFrame extends JFrame {
       public CropMainFrame(Farmer farmer)
       {
           setTitle("Crop Details");
            setSize(400, 300);
            setLocationRelativeTo(null);
            setLayout(new GridLayout(4, 1, 10, 10));

            JButton advisoryBtn = new JButton("Crop Advisory");
            JButton viewBtn = new JButton("My Crops");
            JButton addBtn = new JButton("Add Crop");
            JButton backBtn = new JButton("Back");

            add(advisoryBtn);
            add(viewBtn);
            add(addBtn);
            add(backBtn);

            advisoryBtn.addActionListener(e -> new CropDetailsFrame(farmer));
            viewBtn.addActionListener(e -> new ViewCropsFrame(farmer));
            addBtn.addActionListener(e -> new AddCropFrame(farmer));
            backBtn.addActionListener(e -> dispose());

            setVisible(true);
       }
}
