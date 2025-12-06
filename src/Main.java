//Course Based Project
//Main.java
package main;
import java.util.Scanner;
import java.util.List;
import data.AccessAndSaveFarmer;
import model.Farmer;
import model.Loan;
import model.LoanApplication;
import data.ApplyAndSaveLoan;
public class Main 
{
	static AccessAndSaveFarmer service = new AccessAndSaveFarmer(); //to register, save and modify details
    	static Scanner sc = new Scanner(System.in);
    	public static void main(String[] args) 
	{
        	while(true) 
		{
            		System.out.println("\n=== KISAN SAHAYATA PORTAL ===");
            		System.out.println("1. Register Farmer");
            		System.out.println("2. Login Farmer");
            		System.out.println("3. Exit");
            		System.out.print("Enter choice: ");
            		int choice = Integer.parseInt(sc.nextLine());
            		switch(choice) 
			{
                		case 1:registerFarmer();
                    		       break;
                		case 2:Farmer f = loginFarmer();
                    		       if(f != null) 
				       {
                        			dashboard(f);
                    		       }
                    		       break;
                		case 3:System.out.println("Thank you for using Kisan Sahayata Portal!");
			               return;
                		default:System.out.println("Invalid choice!");
            		}
        	}
    	}

	//REGISTRATION
    	public static void registerFarmer() 
	{
        	System.out.println("\n----- FARMER REGISTRATION -----");
        	System.out.print("Enter Name: ");
        	String name = sc.nextLine();
        	System.out.print("Enter Age: ");
        	int age = sc.nextInt();
		sc.nextLine(); //to clear \n
        	System.out.print("Enter Village: ");
        	String village = sc.nextLine();
        	System.out.print("Enter Land Size (in acres): ");
        	double landSize = sc.nextDouble();
		sc.nextLine(); //to clear \n
        	System.out.print("Enter Soil Type: ");
        	String soilType = sc.nextLine();
		System.out.println("Enter the Aadhar Number:");
		Long aadhar = sc.nextLong();
		sc.nextLine(); //to clear \n
        	System.out.print("Set a Password: ");
        	String password = sc.nextLine();
		// Generate new ID
        	String farmerId = service.generateNewFarmerId();
		// Create Farmer object
        	Farmer farmer = new Farmer(farmerId, age, name, village, soilType, password, landSize,  aadhar);
        	// Save farmer
        	service.addFarmer(farmer);
        	System.out.println("\nRegistration Successful!");
        	System.out.println("Your Farmer ID is: " + farmerId);
        	System.out.println("Use this ID to login.");
    	}

	//LOGIN
	public static Farmer loginFarmer() 
	{
		System.out.println("\n----- FARMER LOGIN -----");
        	System.out.print("Enter Farmer ID: ");
        	String id = sc.nextLine();
        	System.out.print("Enter Password: ");
        	String pass = sc.nextLine();
		Farmer f = service.authenticate(id, pass);
		if(f != null) 
		{
			System.out.println("\nLogin Successful! Welcome " + f.getName());
            		return f;
        	} 
		else 	
		{
            		System.out.println("Invalid ID or Password.");
            		return null;
        	}
    	}

	//DASHBOARD
	public static void dashboard(Farmer f) 
	{
        	while (true) 
		{
            		System.out.println("\n===== FARMER DASHBOARD =====");
            		System.out.println("Farmer ID : " + f.getFarmerID());
            		System.out.println("Name      : " + f.getName());
            		System.out.println("------------------------------");
            		System.out.println("1. View Profile");
			System.out.println("2. Modify Details");
            		System.out.println("3. View Loans");
            		System.out.println("4. View Subsidies");
            		System.out.println("5. View Fertilizers");
            		System.out.println("6. View Crop Details");
            		System.out.println("7. View Harvest Plans");
            		System.out.println("8. Add Harvest Plan");
            		System.out.println("9. View Market Prices");
            		System.out.println("10. View Notifications");
            		System.out.println("11. Logout");
            		System.out.print("Enter choice: ");
            		int choice = sc.nextInt();
			sc.nextLine(); //to clear extra \n
            		switch(choice) 
			{
                		case 1:System.out.println(f);
                    		       break;
				case 2:modifyFarmerDetails(f);
				       break;
                		case 3:viewLoans(f);
                   	 	       break;
                		case 4:System.out.println("Subsidies feature coming soon!");
				       break;
                		case 5:System.out.println("Fertilizer availability coming soon!");
                   		       break;
                		case 6:System.out.println("Crop details coming soon!");
                  		       break;
                		case 7:System.out.println("Harvest plans coming soon!");
                    		       break;
                		case 8:System.out.println("Add harvest plan coming soon!");
                    		       break;
                		case 9:System.out.println("Market prices coming soon!");
                  		       break;
                		case 10:System.out.println("Notifications feature coming soon!");
                    		       break;
                		case 11:System.out.println("Logged out successfully!");
                    			return;
                		default:System.out.println("Invalid choice!");
            		}
        	}
    	}

	//MODIFY FARMER DETAILS
	public static void modifyFarmerDetails(Farmer f) 
	{
		System.out.println("\n----- MODIFY FARMER DETAILS -----");
    		System.out.print("Enter new name (" + f.getName() + "): ");
    		String name = sc.nextLine();
    		if(!name.isEmpty()) f.setName(name);
    		System.out.print("Enter new age (" + f.getAge() + "): ");
    		String age = sc.nextLine();
    		if(!age.isEmpty()) f.setAge(Integer.parseInt(age));
    		System.out.print("Enter new village (" + f.getVillage() + "): ");
    		String village = sc.nextLine();
    		if(!village.isEmpty()) f.setVillage(village);
    		System.out.print("Enter new land size (" + f.getLandSize() + "): ");
    		String land = sc.nextLine();
    		if(!land.isEmpty()) f.setLandSize(Double.parseDouble(land));
    		System.out.print("Enter new soil type (" + f.getSoilType() + "): ");
    		String soil = sc.nextLine();
    		if(!soil.isEmpty()) f.setSoilType(soil);
    		System.out.print("Enter new password: ");
    		String pass = sc.nextLine();
    		if(!pass.isEmpty()) f.setPassword(pass);
    		// Save changes
    		service.updateFarmer(f);
    		System.out.println("Details updated successfully!");
	}
	//VIEW LOANS
	public static void viewLoans(Farmer f) 
	{
    		ApplyAndSaveLoan loanServ = new ApplyAndSaveLoan();
		loanServ.initLoans();
    		System.out.println("\n----- AVAILABLE LOANS -----");
    		List<Loan> loans = loanServ.loadLoans();
    		if(loans.isEmpty()) 
		{
        		System.out.println("No loans available."); //if there are no loans available
        		return;
    		}
    		// Display loans
    		for(Loan l : loans) 
		{
        		System.out.println(l); 
    		}
		//to view more options
    		System.out.println("\n1. Apply for a Loan");
    		System.out.println("2. View My Loan Applications");
    		System.out.println("3. Back");
    		System.out.print("Enter choice: ");
    		int ch = Integer.parseInt(sc.nextLine());
    		switch(ch) 
		{
        		case 1:applyLoan(f.getFarmerID());
           		       break;
			case 2:viewMyLoanApplications(f.getFarmerID());
            		       break;
			default:return;
    		}
	}
	public static void applyLoan(String farmerId) 
	{
    		ApplyAndSaveLoan loanServ = new ApplyAndSaveLoan();
    		List<Loan> loans = loanServ.loadLoans();
    		System.out.println("\nEnter Loan ID to apply: ");
    		String loanId = sc.nextLine();
    		boolean exists = loans.stream().anyMatch(l -> l.getLoanId().equals(loanId));
    		if(!exists) 
		{
        		System.out.println("Invalid Loan ID.");
        		return;
    		}
    		loanServ.applyLoan(farmerId, loanId);
    		System.out.println("Loan applied successfully! Status: PENDING");
	}
	public static void viewMyLoanApplications(String farmerId) 
	{
    		ApplyAndSaveLoan loanServ = new ApplyAndSaveLoan();
    		List<LoanApplication> apps = loanServ.loadLoanApplications();
    		System.out.println("\n----- YOUR LOAN APPLICATIONS -----");
    		boolean found = false;
    		for(LoanApplication app : apps) 
		{
        		if(app.getFarmerId().equals(farmerId)) 
			{
            			System.out.println(app);
            			found = true;
        		}
    		}
    		if(!found) 
		{
        		System.out.println("No loan applications found.");
    		}
	}
}
