//Course Based Project
//Loan.java
package model;
import java.io.Serializable;
public class Loan implements Serializable 
{
	//data members
	private String schemeId;
        private String name;
        private String eligibility;
        private double maxAmount;
        private double interest;
        private int tenure;
        private String documents;
	//parameterized constructor
	public Loan(String schemeId, String name, String eligibility, double maxAmount, double interest, int tenure, String documents) 
        {
            this.schemeId = schemeId;
            this.name = name;
            this.eligibility = eligibility;
            this.maxAmount = maxAmount;
            this.interest = interest;
            this.tenure = tenure;
            this.documents = documents;
        }
	//getter methods
	public String getSchemeId() { return schemeId; }
        public String getName() { return name; }
        public String getEligibility() { return eligibility; }
        public double getMaxAmount() { return maxAmount; }
        public double getInterest() { return interest; }
        public int getTenure() { return tenure; }
        public String getDocuments() { return documents; }
	//toString method
        @Override
	public String toString() 
	{
            return schemeId + " - " + name +
               " | Interest: " + interest +
               " | Max: " + maxAmount +
               " | Tenure: " + tenure +
               " | Eligibility: " + eligibility;
	}
}
