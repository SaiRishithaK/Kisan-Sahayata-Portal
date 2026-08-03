package model;


public class FarmerCrop 
{
    private int cropId;
    private String farmerId;
    private String cropName;
    private String season;
    private double area;
    
    //constructor
    public FarmerCrop(int cropId, String farmerId, String cropName, String season, double area) 
    {
        this.cropId = cropId;
        this.farmerId = farmerId;
        this.cropName = cropName;
        this.season = season;
        this.area = area;
    }
    
    public FarmerCrop(String farmerId, String cropName, String season, double area) 
    {
        this.farmerId = farmerId;
        this.cropName = cropName;
        this.season = season;
        this.area = area;
    }
    
    //getters
    public int getCropId() { return cropId; }
    public String getFarmerId() { return farmerId; }
    public String getCropName() { return cropName; }
    public String getSeason() { return season; }
    public double getArea() { return area; }
    
}
