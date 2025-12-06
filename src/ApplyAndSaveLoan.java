//Course Based Project
//ApplyAndSaveLoan.java
package data;
import model.Loan;
import model.LoanApplication;
import model.LoanNotification;
import java.io.*;import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
public class ApplyAndSaveLoan 
{
	private static final String LOANS_FILE = "data/loans.dat";
	private static final String LOAN_APPLY_FILE = "data/loan_applications.dat";
	// Load all loans
	public List<Loan> loadLoans() 
	{
        	try 
		{
            		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LOANS_FILE));
		        return (List<Loan>) ois.readObject();
        	} 
		catch(FileNotFoundException e) 
		{
            		return new ArrayList<>();   // file empty or doesn't exist
        	}
		catch(IOException e) 
		{
            		return new ArrayList<>();   // file empty or doesn't exist
        	}
		catch(Exception e) 
		{
            		return new ArrayList<>();
        	}
    	}
    	// Save loans (only once manually)
    	public void saveLoans(List<Loan> list) 
	{
        	try 
		{
            		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LOANS_FILE)); 	
			oos.writeObject(list);
        	} 
		catch(IOException e) 
		{
            		System.out.println("Failed to save the details. Please try again later");
        	}
		catch(Exception e) 
		{
            		System.out.println("Error saving loans");
        	}
    	}
	// Load all loan applications
	public List<LoanApplication> loadLoanApplications() 
	{
        	try
		{
            		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(LOAN_APPLY_FILE));
		 	return (List<LoanApplication>) ois.readObject();
        	} 
		catch(Exception e) 
		{
            		return new ArrayList<>();
        	}
    	}
	// Save applications
    	public void saveLoanApplications(List<LoanApplication> list) 
	{
        	try  
		{
            		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(LOAN_APPLY_FILE));
			oos.writeObject(list);
        	} 
		catch(Exception e) 
		{
            		System.out.println("Error saving loan applications");
        	}
    	}
	// Apply loan
    	public void applyLoan(String farmerId, String loanId) 
	{
        	List<LoanApplication> apps = loadLoanApplications();
        	apps.add(new LoanApplication(farmerId, loanId,"PENDING"));
        	saveLoanApplications(apps);
		NotificationManager nDAO = new NotificationManager();
		nDAO.addNotification(farmerId, new LoanNotification("Your loan " + loanId + " was applied."));

    	}
	//first try with sample data
	public static void initLoans() 
	{
		ApplyAndSaveLoan loanServ = new ApplyAndSaveLoan();
		List<Loan> loans = new ArrayList<>();
    		loans.add(new Loan("L1", "Kisan Credit Card", 4.0, "Land > 1 acre", "Aadhaar, Land Papers"));
    		loans.add(new Loan("L2", "Crop Loan", 5.5, "All farmers", "Aadhaar"));
    		loans.add(new Loan("L3", "Irrigation Loan", 6.0, "Land > 2 acres", "Land Papers"));
    		loanServ.saveLoans(loans);
	}
}
