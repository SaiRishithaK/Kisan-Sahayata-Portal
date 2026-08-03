//Course Based Project
//Farmer.java
package model;
import java.io.Serializable;
public class Farmer implements Serializable
{
	//Data members
	private String farmerID;
	private int age;
	private String name, password; // survey_no, village ,mandal, district, soilType,
	//private double landSize;
	private String aadharNum;
	//parameterized constructor
	public Farmer(String farmerID, String name, int age,String password, String aadharNum) 
	{
        	this.farmerID = farmerID;
        	this.name = name;
        	this.age = age;
        	this.password = password;
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
    	public String getPassword() 
	{
        	return password;
    	}
    	public void setPassword(String password) 
	{
        	this.password = password;
    	}
    	public String getAadharNum() 
	{
        	return aadharNum;
    	}
    	public void setAadharNum(String aadharNum) 
	{
        	this.aadharNum = aadharNum;
    	}
        //toString method
        @Override
    	public String toString() 
	{
        	return "Farmer\nFarmerID =" + farmerID +"\nName =" + name +"\nAadhar Number =" + aadharNum +"\nAge =" + age;
    	}
}