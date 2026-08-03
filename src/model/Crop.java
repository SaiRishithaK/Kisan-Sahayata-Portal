//Course Based Project
//Crop.java
package model;

import java.io.Serializable;

public abstract class Crop implements Serializable {

    protected String name;
    protected String season;   // Rabi / Kharif
    protected int daysToHarvest;
    protected String fertilizer;
    protected String tip;

    public Crop(String name, String season, int daysToHarvest, String fertilizer, String tip) {
        this.name = name;
        this.season = season;
        this.daysToHarvest = daysToHarvest;
        this.fertilizer = fertilizer;
        this.tip = tip;
    }

    public String getName() { return name; }
    public String getSeason() { return season; }
    public int getDaysToHarvest() { return daysToHarvest; }
    public String getFertilizer() { return fertilizer; }
    public String getTip() { return tip; }
}
