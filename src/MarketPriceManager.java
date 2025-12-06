//Course Based Project
//MarketPriceManager.java
package data;

import model.MarketPrice;
import java.io.*;
import java.util.*;

public class MarketPriceManager {

    private static final String FILE = "marketprices.dat";

    public List<MarketPrice> loadPrices() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            return (List<MarketPrice>) ois.readObject();
        } catch (Exception e) {
            return defaultPrices();
        }
    }

    public void savePrices(List<MarketPrice> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(list);
        } catch (Exception e) {
            System.out.println("Error saving prices.");
        }
    }

    public double getPrice(String cropName) {
    List<MarketPrice> list = loadPrices();

    for (MarketPrice m : list) {
        if (m.getCrop().equalsIgnoreCase(cropName)) {
            return m.getPrice();
        }
    }
    return -1; // crop not found
}


    // Get price of a specific crop
    public double getPriceOfCrop(String cropName) {
    List<MarketPrice> list = loadPrices();

    for (MarketPrice m : list) {
        if (m.getCrop().equalsIgnoreCase(cropName)) {
            return m.getPrice();
        }
    }
    return -1; // crop not found
}


    // Initial default values
    private List<MarketPrice> defaultPrices() {
        List<MarketPrice> list = new ArrayList<>();
        list.add(new MarketPrice("Rice", 2500));
        list.add(new MarketPrice("Wheat", 2100));
        list.add(new MarketPrice("Cotton", 6500));
        list.add(new MarketPrice("Maize", 1800));
	list.add(new MarketPrice("Mustard", 5400));
	list.add(new MarketPrice("Barley", 2150));
        savePrices(list);
        return list;
    }
}
