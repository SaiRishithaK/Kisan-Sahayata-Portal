//Couse Based Project
//Farmer.java
package model;
import java.io.Serializable;
public class Farmer implements Serializable
{
	//Data members
	private String farmerID;
	private int age;
	private String name, village, soilType, username, password;
	private double landSize;
	private long aadharNum;
	//parameterized constructor
	public Farmer(String farmerID, int age, String name, String village, String soilType,String password, double landSize, long aadharNum) 
	{
        	this.farmerID = farmerID;
        	this.age = age;
        	this.name = name;
        	this.village = village;
        	this.soilType = soilType;
        	this.password = password;
        	this.landSize = landSize;
        	this.aadharNum = aadharNum;
    	}
	//Getter and setter methods
	public String getFarmerID() 
	{
        	return farmerID;
    	}
    	public void setFarmerID(String farmerID) 
	{
        	this.farmerID = farmerID;
    	}
    	public int getAge() 
	{
        	return age;
    	}
    	public void setAge(int age) 
	{
        	this.age = age;
    	}	
    	public String getName() 
	{
        	return name;
    	}
    	public void setName(String name) 
	{
        	this.name = name;
    	}
    	public String getVillage() 
	{
        	return village;
    	}
    	public void setVillage(String village) 
	{
        	this.village = village;
    	}
    	public String getSoilType() 
	{
        	return soilType;
    	}
    	public void setSoilType(String soilType) 
	{
        	this.soilType = soilType;
    	}
       	public String getPassword() 
	{
        	return password;
    	}
    	public void setPassword(String password) 
	{
        	this.password = password;
    	}
    	public double getLandSize() 
	{
        	return landSize;
    	}
    	public void setLandSize(double landSize) 
	{
        	this.landSize = landSize;
    	}
    	public long getAadharNum() 
	{
        	return aadharNum;
    	}
    	public void setAadharNum(long aadharNum) 
	{
        	this.aadharNum = aadharNum;
    	}
    	//toString method
    	public String toString() 
	{
        	return "Farmer"+ "\nFarmerID =" + farmerID +"\nAge =" + age +"\nName =" + name +"\nVillage =" + village +"\nSoil Type =" + soilType + "\nLand Size =" + landSize +" acres\nAadhar Number =" + aadharNum +"\n";
    	}
}