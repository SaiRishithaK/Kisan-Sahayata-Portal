//Course Based Project
//Loan.java
package model;
import java.io.Serializable;
public class Loan implements Serializable 
{
	//data members
	private String loanId;
	private String loanName;
	private double interestRate;
	private String eligibility;
	private String documents;
	//parameterized constructor
	public Loan(String loanId, String loanName, double interestRate, String eligibility, String documents) 
	{
        	this.loanId = loanId;
        	this.loanName = loanName;
        	this.interestRate = interestRate;
        	this.eligibility = eligibility;
        	this.documents = documents;
    	}
	//getter methods
	public String getLoanId() { return loanId; }
	public String getLoanName() { return loanName; }
	public double getInterestRate() { return interestRate; }
	public String getEligibility() { return eligibility; }
	public String getDocuments() { return documents; }
	//toString method
	public String toString() 
	{
        	return loanId + " - " + loanName + " | Interest: " + interestRate + "%" + " | Eligibility: " + eligibility + " | Docs: " + documents;
	}
}
