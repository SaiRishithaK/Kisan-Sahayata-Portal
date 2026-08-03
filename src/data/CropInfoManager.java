//Course Based Project
//CropInfoManager.java
package data;

import model.*;
import java.util.*;

public class CropInfoManager {

    private MarketPriceManager priceManager = new MarketPriceManager();

    public Crop getCropInfo(String cropName, String soilType) {

        cropName = cropName.toLowerCase();
        soilType = soilType.toLowerCase();

        // ---------- KHARIF CROPS ----------
        if (cropName.equals("rice") || cropName.equals("maize") || cropName.equals("cotton")) {
            return new KharifCrop(
                    cropName,
                    120,
                    "Urea, DAP",
                    "Ensure proper irrigation during monsoon months."
            );
        }

        // ---------- RABI CROPS ----------
        if (cropName.equals("wheat") || cropName.equals("mustard") || cropName.equals("barley")) {
            return new RabiCrop(
                    cropName,
                    140,
                    "NPK 10-26-26",
                    "Ideal for dry soil, sow during winter."
            );
        }

        // DEFAULT GENERIC
        return new Crop(cropName, "Unknown", 100, "Basic NPK", 
                "\nGeneral crop tips depending on soil: " + soilType) {};
    }

    // Get market price
    public double getMarketPriceForCrop(String cropName) {
        return priceManager.getPrice(cropName);
    }
}
