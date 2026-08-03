package model;

public class Subsidy {
    
    private String name;
    private double percentage;
    private String description;
    
    //constructor
    public Subsidy(String name, double percentage, String description) 
    {
        this.name = name;
        this.percentage = percentage;
        this.description = description;
    }
    
    //getters
    public String getName() { return name; }
    public double getPercentage() { return percentage; }
    public String getDescription() { return description; }
}
