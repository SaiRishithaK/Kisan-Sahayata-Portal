package model;

public class Land 
{
    private String surveyNo;
    private String farmerId;
    private String village;
    private String mandal;
    private String district;
    private String soilType;
    private double landSize;
    //constructor
    public Land(String surveyNo, String farmerId, String village, String mandal, String district, String soilType, double landSize) 
    {
        this.surveyNo = surveyNo;
        this.farmerId = farmerId;
        this.village = village;
        this.mandal = mandal;
        this.district = district;
        this.soilType = soilType;
        this.landSize = landSize;
    }
    //getters & setters
    public String getSurveyNo()
    {
        return this.surveyNo;
    }
    public String getFarmerID()
    {
        return this.farmerId;
    }
    public String getVillage()
    {
        return this.village;
    }
    public String getMandal()
    {
        return this.mandal;
    }
    public String getDistrict()
    {
        return this.district;
    }
    public String getSoilType()
    {
        return this.soilType;
    }
    public double getLandSize()
    {
        return this.landSize;
    }
}
