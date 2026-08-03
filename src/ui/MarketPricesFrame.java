//Course Based Project
//MarketPricesFrame.java
package ui;

import data.MarketPriceManager;
import model.MarketPrice;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MarketPricesFrame extends JFrame {

    public MarketPricesFrame() {

        setTitle("Market Prices");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        MarketPriceManager m = new MarketPriceManager();
        List<MarketPrice> list = m.loadPrices();

        JTextArea area = new JTextArea();
        area.setEditable(false);

        for (MarketPrice mp : list) {
            area.append(mp.toString() + "\n\n");
        }

        add(new JScrollPane(area), BorderLayout.CENTER);
        setVisible(true);
    }
}
