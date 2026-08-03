//Course Based Project
//MarketPrice.java
package model;

import java.io.Serializable;

public class MarketPrice implements Serializable {
    private String crop;
    private double price;

    public MarketPrice(String crop, double price) {
        this.crop = crop;
        this.price = price;
    }

    public String getCrop() { return crop; }
    public double getPrice() { return price; }
    public void setPrice(double p) { this.price = p; }

    @Override
    public String toString() {
        return crop + " → ₹" + price + " / quintal";
    }
}
