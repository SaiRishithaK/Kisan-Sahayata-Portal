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

    private Farmer farmer;
    private ApplyAndSaveLoan loanDAO;

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

        JTextArea area = new JTextArea();
        area.setEditable(false);

        for (Loan l : loans) {
            area.append(l.toString() + "\n\n");
        }

        JOptionPane.showMessageDialog(this, new JScrollPane(area),
                "Available Loans", JOptionPane.INFORMATION_MESSAGE);
    }

    // APPLY LOAN
    private void applyLoan() {
        String loanId = JOptionPane.showInputDialog(this, "Enter Loan ID:");

        if (loanId == null || loanId.trim().isEmpty()) return;

        // Check if loan exists
        List<Loan> loans = loanDAO.loadLoans();
        boolean exists = loans.stream().anyMatch(l -> l.getLoanId().equals(loanId));

        if (!exists) {
            JOptionPane.showMessageDialog(this, "Invalid Loan ID!");
            return;
        }

        // Apply loan with only TWO parameters
        loanDAO.applyLoan(farmer.getFarmerID(), loanId);

        JOptionPane.showMessageDialog(this,
                "Loan Applied Successfully!");
    }

    // VIEW LOAN APPLICATIONS
    private void viewMyApplications() {
        List<LoanApplication> apps = loanDAO.loadLoanApplications();

        JTextArea area = new JTextArea();
        area.setEditable(false);

        boolean found = false;

        for (LoanApplication la : apps) {
            if (la.getFarmerId().equals(farmer.getFarmerID())) {
                area.append("Loan ID: " + la.getLoanId() + "\n\n");
                found = true;
            }
        }

        if (!found) {
            JOptionPane.showMessageDialog(this,
                    "You have no loan applications.");
            return;
        }

        JOptionPane.showMessageDialog(this, new JScrollPane(area),
                "My Loan Applications", JOptionPane.INFORMATION_MESSAGE);
    }
}
