//Course Based Project
//MarketPriceThread.java
package threads;

import data.MarketPriceManager;
import model.MarketPrice;
import java.util.*;

public class MarketPriceThread extends Thread {

    @Override
    public void run() {
        MarketPriceManager m = new MarketPriceManager();

        while (true) {
            try { Thread.sleep(45000); } catch (Exception e) {}

            List<MarketPrice> list = m.loadPrices();

            Random r = new Random();

            // Random ±10%
            for (MarketPrice p : list) {
                double factor = 0.90 + (1.10 - 0.90) * r.nextDouble();
                p.setPrice(Math.round(p.getPrice() * factor));
            }

            m.savePrices(list);
            System.out.println("Market prices updated automatically.");
        }
    }
}
