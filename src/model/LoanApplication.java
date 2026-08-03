//Course Based Project
//LoanApplication.java
package model;
import java.io.Serializable;
public class LoanApplication implements Serializable 
{
    //data members
    private int applicationId;
    private String farmerId;
    private String schemeId;
    private double amount;
    private String status;
    //parameterized constructor
    public LoanApplication(int applicationId, String farmerId, String schemeId, double amount, String status) 
    {
        this.applicationId = applicationId;
        this.farmerId = farmerId;
        this.schemeId = schemeId;
        this.amount = amount;
        this.status = status;
    }
    //getter methods
    public int getApplicationId() { return applicationId; }
    public String getFarmerId() { return farmerId; }
    public String getSchemeId() { return schemeId; }
    public double getAmount() { return amount; }
    public String getStatus() { return status; }
    //toString method
    @Override	
    public String toString() 	
    {
       	return "Application No.:"+ applicationId + " | Loan: " + schemeId + " | Amount: " + amount + " | Status: " + status;
    }
}
