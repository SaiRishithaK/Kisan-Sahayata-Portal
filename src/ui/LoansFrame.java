//Course Based Project
//LoansFrame.java
package ui;

import model.Farmer;
import data.ApplyAndSaveLoan;
import model.Loan;
import model.LoanApplication;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoansFrame extends JFrame {

    private final Farmer farmer;
    private final ApplyAndSaveLoan loanDAO;

    public LoansFrame(Farmer f) {
        this.farmer = f;
        this.loanDAO = new ApplyAndSaveLoan();

        setTitle("Loan Services");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(4, 1, 10, 10));

        JButton viewBtn = new JButton("View All Loans");
        JButton applyBtn = new JButton("Apply for Loan");
        JButton myAppsBtn = new JButton("My Loan Applications");
        JButton backBtn = new JButton("Back");

        add(viewBtn);
        add(applyBtn);
        add(myAppsBtn);
        add(backBtn);

        viewBtn.addActionListener(e -> viewLoans());
        applyBtn.addActionListener(e -> applyLoan());
        myAppsBtn.addActionListener(e -> viewMyApplications());
        backBtn.addActionListener(e -> this.dispose());

        setVisible(true);
    }

    // VIEW ALL LOANS
    private void viewLoans() {
        List<Loan> loans = loanDAO.loadLoans();

        if (loans.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No loans available.");
            return;
        }

        String[] cols = {
            "Loan ID", "Name", "Interest", "Max Amount",
            "Tenure", "Eligibility", "Documents"
        };

        Object[][] data = new Object[loans.size()][7];
        
        for (int i = 0; i < loans.size(); i++) 
        {
            Loan l = loans.get(i);

            data[i][0] = l.getSchemeId();
            data[i][1] = l.getName();
            data[i][2] = l.getInterest();
            data[i][3] = l.getMaxAmount();
            data[i][4] = l.getTenure();
            data[i][5] = l.getEligibility();
            data[i][6] = l.getDocuments();
        }
        
        JTable table = new JTable(data, cols);
        table.setEnabled(false); // read-only
        table.setRowHeight(28);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame("Available Loans");
        frame.setSize(900, 400);   
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        frame.add(scrollPane, BorderLayout.CENTER);

        JButton okBtn = new JButton("Close");
        okBtn.addActionListener(e -> frame.dispose());

        JPanel bottom = new JPanel();
        bottom.add(okBtn);

        frame.add(bottom, BorderLayout.SOUTH);

        frame.setVisible(true); 
    }

    // APPLY LOAN
    private void applyLoan() 
    {
        List<Loan> loans = loanDAO.loadLoans();
        
        if (loans.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this, "No loans available.");
            return;
        }
        
        String[] options = loans.stream().map(l -> l.getSchemeId() + " - " + l.getName()).toArray(String[]::new);
        String selected = (String) JOptionPane.showInputDialog(
                this,
                "Select Loan:",
                "Apply Loan",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
        if (selected == null) return;
        String loanId = selected.split(" - ")[0];
        String amountStr = JOptionPane.showInputDialog("Enter amount:");
        try 
        {
            double amount = Double.parseDouble(amountStr);

            boolean success = loanDAO.applyLoan(
                    farmer.getFarmerID(),
                    loanId,
                    amount
            );

            JOptionPane.showMessageDialog(this,
                    success ? "Loan Applied Successfully!" : "Application Failed!");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,"Invalid Amount!");
        }
    }

    // VIEW LOAN APPLICATIONS
    private void viewMyApplications() 
    {
        List<LoanApplication> apps = loanDAO.getMyApplications(farmer.getFarmerID());
        if (apps.isEmpty()) 
        {
            JOptionPane.showMessageDialog(this,"You have no loan applications.");
            return;
        }
        JTextArea area = new JTextArea();
        area.setEditable(false);
        for (LoanApplication la : apps) 
        {
            area.append(
                    "Loan: " + la.getSchemeId() +
                    " | Amount: " + la.getAmount() +
                    " | Status: " + la.getStatus() + "\n\n"
            );
        }
        JOptionPane.showMessageDialog(this, new JScrollPane(area),
                "My Loan Applications", JOptionPane.INFORMATION_MESSAGE);
    }
}
