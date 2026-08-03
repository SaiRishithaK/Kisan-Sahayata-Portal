//Course Based Project
//ApplyAndSaveLoan.java
package data;
import model.Loan;
import model.LoanApplication;
import model.LoanNotification;
import java.io.*;
import java.util.*;
import java.sql.*;
public class ApplyAndSaveLoan 
{
	/*private static final String LOANS_FILE = "data/loans.dat";
	private static final String LOAN_APPLY_FILE = "data/loan_applications.dat";*/
	// Load all loans
	public List<Loan> loadLoans() 
	{
            List<Loan> list = new ArrayList<>();
            try
            {
                Connection con=DBConnection.getConnection();
                String sql="SELECT * FROM LoanScheme";
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) {
                list.add(new Loan(
                        rs.getString("scheme_id"),
                        rs.getString("name"),
                        rs.getString("eligibility"),
                        rs.getDouble("max_amount"),
                        rs.getDouble("interest_rate"),
                        rs.getInt("tenure"),
                        rs.getString("documents")
                    ));
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return list;
    	}
        
	// Load all loan applications
        public List<LoanApplication> getMyApplications(String farmerId)
        {
            List<LoanApplication> list = new ArrayList<>();
            try
            {
                Connection con = DBConnection.getConnection();
                String sql = "SELECT * FROM LoanApplication WHERE farmer_id=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, farmerId);
                ResultSet rs = ps.executeQuery();
                
                while (rs.next()) 
                {
                    list.add(new LoanApplication(
                        rs.getInt("application_id"),
                        rs.getString("farmer_id"),
                        rs.getString("scheme_id"),
                        rs.getDouble("amount"),
                        rs.getString("status")
                    ));
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return list;
        }
        
	// Apply loan
    	public boolean applyLoan(String farmerId, String loanId, double amount) 
	{
            try
            {
                Connection con=DBConnection.getConnection();
                String sql = "INSERT INTO LoanApplication(farmer_id, scheme_id, amount, status, apply_date) VALUES (?, ?, ?, 'Pending', CURDATE())";
                
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, farmerId);
                ps.setString(2, loanId);
                ps.setDouble(3, amount);
                
                int rows = ps.executeUpdate();
                
                if(rows>0)
                {
                    NotificationManager nDAO = new NotificationManager();
                    nDAO.addNotification(farmerId, new LoanNotification("Loan " + loanId + " applied successfully."));
                    return true;
                }
                return false;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return false;
            }
    	}
}
