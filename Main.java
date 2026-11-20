
package bankingmanagement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {

        System.out.println("======================================");
        System.out.println("       BANKING MANAGEMENT SYSTEM      ");
        System.out.println("======================================");

        // 1. Create the bank
        Bank bank = new Bank("Community Bank");

        System.out.println("\nWelcome to " + bank.getBankName());

        // 2. Create customers
        Customer customer1 = new Customer(
                "C001", "John", "Doe",
                "john@example.com", "2025551234"
        );

        Customer customer2 = new Customer(
                "C002", "Jane", "Smith",
                "jane@example.com", "2025555678"
        );

        bank.addCustomer(customer1);
        bank.addCustomer(customer2);

        System.out.println("\n--- CUSTOMER REGISTRATION ---");
        System.out.println("Registered: " + customer1.getCustomerID());
        System.out.println("Registered: " + customer2.getCustomerID());

        // 3. Create checking and savings accounts
        CheckingAccount checking = new CheckingAccount(
                "A001",
                "C001",
                new BigDecimal("1000.00"),
                LocalDate.now(),
                new BigDecimal("500.00"),
                new BigDecimal("10.00")
        );

        SavingsAccount savings = new SavingsAccount(
                "A002",
                "C002",
                new BigDecimal("2000.00"),
                LocalDate.now(),
                new BigDecimal("0.045"),
                new BigDecimal("500.00")
        );

        bank.addAccount(checking);
        bank.addAccount(savings);

        System.out.println("\n--- ACCOUNTS CREATED ---");
        displayAccount(checking);
        displayAccount(savings);

        // 4. Deposit
        System.out.println("\n--- DEPOSIT ---");

        bank.deposit("A001", new BigDecimal("500.00"));
        displayAccount(checking);

        // 5. Withdrawal
        System.out.println("\n--- WITHDRAWAL ---");

        bank.withdraw("A001", new BigDecimal("200.00"));
        displayAccount(checking);

        // 6. Transfer
        System.out.println("\n--- TRANSFER ---");

        bank.transfer("A001", "A002", new BigDecimal("300.00"));
        displayAccount(checking);
        displayAccount(savings);

        // 7. Monthly checking fee
        System.out.println("\n--- MONTHLY CHECKING FEE ---");

        checking.applyMonthlyFee();
        displayAccount(checking);

        // 8. Savings interest
        System.out.println("\n--- SAVINGS INTEREST ---");

        BigDecimal interest = savings.calculateMonthlyInterest();

        System.out.println("Monthly interest: $" + interest);

        savings.applyMonthlyInterest();
        displayAccount(savings);

        // 9. Overdraft protection
        System.out.println("\n--- OVERDRAFT PROTECTION ---");

        try {
            bank.withdraw("A001", new BigDecimal("2000.00"));
        } catch (IllegalStateException e) {
            System.out.println("Withdrawal rejected: " + e.getMessage());
        }

        // 10. Minimum balance protection
        System.out.println("\n--- MINIMUM BALANCE PROTECTION ---");

        try {
            bank.withdraw("A002", new BigDecimal("2500.00"));
        } catch (IllegalStateException e) {
            System.out.println("Withdrawal rejected: " + e.getMessage());
        }

        // 11. Customer search
        System.out.println("\n--- CUSTOMER SEARCH ---");

        Customer foundCustomer = bank.findCustomer("C001");

        if (foundCustomer != null) {
            System.out.println("Customer found: "
                    + foundCustomer.getCustomerID());
        }

        // 12. Account search
        System.out.println("\n--- ACCOUNT SEARCH ---");

        BankAccount foundAccount = bank.findAccount("A002");

        if (foundAccount != null) {
            displayAccount(foundAccount);
        }

        // 13. Transaction history
        System.out.println("\n--- TRANSACTION HISTORY ---");

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Transaction transaction : bank.getTransactions()) {

            System.out.println(
                    "ID: " + transaction.getTransactionID()
                    + " | Account: " + transaction.getAccountID()
                    + " | Type: " + transaction.getTransactionType()
                    + " | Amount: $" + transaction.getAmount()
                    + " | Date: "
                    + transaction.getTransactionDate().format(formatter)
            );
        }

        System.out.println("\nTotal recorded transactions: "
                + bank.getTransactions().size());

        // 14. Final account balances
        System.out.println("\n--- FINAL ACCOUNT BALANCES ---");

        displayAccount(checking);
        displayAccount(savings);

        System.out.println("\n======================================");
        System.out.println("        DEMONSTRATION COMPLETE        ");
        System.out.println("======================================");
    }

    private static void displayAccount(BankAccount account) {

        System.out.println(
                "Account: " + account.getAccountNumber()
                + " | Balance: $" + account.getBalance()
        );
    }
}
