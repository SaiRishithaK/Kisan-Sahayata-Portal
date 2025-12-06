//Course Based Project
//LoanApplication.java
package model;
import java.io.Serializable;
public class LoanApplication implements Serializable 
{
	//data members
	private String farmerId;
	private String loanId;
	private String status;
	//parameterized constructor
	public LoanApplication(String farmerId, String loanId, String status) 
	{
        	this.farmerId = farmerId;
        	this.loanId = loanId;
        	this.status = status;
    	}
	//getter methods
	public String getFarmerId() { return farmerId; }
	public String getLoanId() { return loanId; }
	public String getStatus() { return status; }
	//toString method
	public String toString() 
	{
        	return "Loan: " + loanId + " | Status: " + status;
    	}
}
