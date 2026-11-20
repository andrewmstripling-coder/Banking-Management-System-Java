
package bankingmanagement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Test {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("=== BANKING MANAGEMENT SYSTEM TEST ===");

        // ========================================
        // 1. BANK CREATION
        // ========================================

        Bank bank = new Bank("Community Bank");

        check("Bank creation",
                bank.getBankName().equals("Community Bank"));

        // ========================================
        // 2. CUSTOMER MANAGEMENT
        // ========================================

        Customer customer1 = new Customer(
                "C001", "John", "Doe",
                "john@example.com", "2025551234"
        );

        Customer customer2 = new Customer(
                "C002", "Jane", "Smith",
                "jane@example.com", "2025555678"
        );

        check("Customer creation",
                customer1.getCustomerID().equals("C001"));

        bank.addCustomer(customer1);
        bank.addCustomer(customer2);

        check("Add customer",
                bank.findCustomer("C001") == customer1);

        check("Find second customer",
                bank.findCustomer("C002") == customer2);

        check("Find nonexistent customer",
                bank.findCustomer("C999") == null);

        expectException("Duplicate customer",
                IllegalArgumentException.class,
                () -> bank.addCustomer(customer1));

        expectException("Null customer",
                IllegalArgumentException.class,
                () -> bank.addCustomer(null));

        // ========================================
        // 3. ACCOUNT MANAGEMENT
        // ========================================

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

        check("Account creation",
                checking.getAccountNumber().equals("A001"));

        bank.addAccount(checking);
        bank.addAccount(savings);

        check("Add account",
                bank.findAccount("A001") == checking);

        check("Find second account",
                bank.findAccount("A002") == savings);

        check("Find nonexistent account",
                bank.findAccount("A999") == null);

        expectException("Duplicate account",
                IllegalArgumentException.class,
                () -> bank.addAccount(checking));

        expectException("Null account",
                IllegalArgumentException.class,
                () -> bank.addAccount(null));

        // ========================================
        // 4. DEPOSIT TESTS
        // ========================================

        bank.deposit("A001", new BigDecimal("200.00"));

        check("Deposit $200",
                checking.getBalance().compareTo(
                        new BigDecimal("1200.00")) == 0);

        expectException("Negative deposit",
                IllegalArgumentException.class,
                () -> bank.deposit(
                        "A001", new BigDecimal("-100.00")));

        expectException("Zero deposit",
                IllegalArgumentException.class,
                () -> bank.deposit(
                        "A001", BigDecimal.ZERO));

        expectException("Null deposit",
                IllegalArgumentException.class,
                () -> bank.deposit("A001", null));

        expectException("Missing deposit account",
                IllegalArgumentException.class,
                () -> bank.deposit(
                        "A999", new BigDecimal("100.00")));

        // ========================================
        // 5. WITHDRAWAL TESTS
        // ========================================

        bank.withdraw("A001", new BigDecimal("300.00"));

        check("Withdraw $300",
                checking.getBalance().compareTo(
                        new BigDecimal("900.00")) == 0);

        expectException("Negative withdrawal",
                IllegalArgumentException.class,
                () -> bank.withdraw(
                        "A001", new BigDecimal("-50.00")));

        expectException("Insufficient funds",
                IllegalStateException.class,
                () -> bank.withdraw(
                        "A001", new BigDecimal("2000.00")));

        expectException("Missing withdrawal account",
                IllegalArgumentException.class,
                () -> bank.withdraw(
                        "A999", new BigDecimal("100.00")));

        // ========================================
        // 6. TRANSFER TESTS
        // ========================================

        bank.transfer(
                "A001",
                "A002",
                new BigDecimal("400.00")
        );

        check("Transfer - sending account",
                checking.getBalance().compareTo(
                        new BigDecimal("500.00")) == 0);

        check("Transfer - receiving account",
                savings.getBalance().compareTo(
                        new BigDecimal("2400.00")) == 0);

        expectException("Same-account transfer",
                IllegalArgumentException.class,
                () -> bank.transfer(
                        "A001", "A001",
                        new BigDecimal("100.00")));

        expectException("Negative transfer",
                IllegalArgumentException.class,
                () -> bank.transfer(
                        "A001", "A002",
                        new BigDecimal("-100.00")));

        expectException("Missing destination account",
                IllegalArgumentException.class,
                () -> bank.transfer(
                        "A001", "A999",
                        new BigDecimal("100.00")));

        check("Final account 1 balance",
                checking.getBalance().compareTo(
                        new BigDecimal("500.00")) == 0);

        check("Final account 2 balance",
                savings.getBalance().compareTo(
                        new BigDecimal("2400.00")) == 0);

        // ========================================
        // 7. CHECKING ACCOUNT TESTS
        // ========================================

        CheckingAccount overdraftAccount = new CheckingAccount(
                "A003",
                "C001",
                new BigDecimal("100.00"),
                LocalDate.now(),
                new BigDecimal("500.00"),
                new BigDecimal("10.00")
        );

        overdraftAccount.withdraw(new BigDecimal("300.00"));

        check("Checking overdraft allowed",
                overdraftAccount.getBalance().compareTo(
                        new BigDecimal("-200.00")) == 0);

        expectException("Checking overdraft exceeded",
                IllegalStateException.class,
                () -> overdraftAccount.withdraw(
                        new BigDecimal("400.00")));

        overdraftAccount.applyMonthlyFee();

        check("Checking monthly fee",
                overdraftAccount.getBalance().compareTo(
                        new BigDecimal("-210.00")) == 0);

        // ========================================
        // 8. SAVINGS ACCOUNT TESTS
        // ========================================

        SavingsAccount interestAccount = new SavingsAccount(
                "A004",
                "C002",
                new BigDecimal("1200.00"),
                LocalDate.now(),
                new BigDecimal("0.06"),
                new BigDecimal("500.00")
        );

        check("Savings monthly interest calculation",
                interestAccount.calculateMonthlyInterest()
                        .compareTo(new BigDecimal("6.00")) == 0);

        interestAccount.applyMonthlyInterest();

        check("Savings monthly interest applied",
                interestAccount.getBalance().compareTo(
                        new BigDecimal("1206.00")) == 0);

        expectException("Savings minimum balance",
                IllegalStateException.class,
                () -> interestAccount.withdraw(
                        new BigDecimal("800.00")));

        // ========================================
        // 9. TRANSACTION HISTORY TESTS
        // ========================================

        System.out.println("\n=== TRANSACTION HISTORY TESTS ===");

        Bank transactionBank = new Bank("Transaction Test Bank");

        CheckingAccount account1 = new CheckingAccount(
                "TX001",
                "C001",
                new BigDecimal("1000.00"),
                LocalDate.now(),
                new BigDecimal("500.00"),
                new BigDecimal("10.00")
        );

        SavingsAccount account2 = new SavingsAccount(
                "TX002",
                "C002",
                new BigDecimal("2000.00"),
                LocalDate.now(),
                new BigDecimal("0.045"),
                new BigDecimal("500.00")
        );

        transactionBank.addAccount(account1);
        transactionBank.addAccount(account2);

        check("Transaction history initially empty",
                transactionBank.getTransactions().isEmpty());

        // Deposit transaction
        transactionBank.deposit(
                "TX001", new BigDecimal("200.00"));

        check("Deposit transaction recorded",
                transactionBank.getTransactions().size() == 1);

        Transaction depositTransaction =
                transactionBank.getTransactions().get(0);

        check("Deposit transaction ID",
                depositTransaction.getTransactionID().equals("T001"));

        check("Deposit transaction type",
                depositTransaction.getTransactionType().equals("DEPOSIT"));

        check("Deposit transaction amount",
                depositTransaction.getAmount().compareTo(
                        new BigDecimal("200.00")) == 0);

        check("Deposit transaction account",
                depositTransaction.getAccountID().equals("TX001"));

        check("Deposit transaction timestamp",
                depositTransaction.getTransactionDate() != null);

        // Withdrawal transaction
        transactionBank.withdraw(
                "TX001", new BigDecimal("100.00"));

        check("Withdrawal transaction recorded",
                transactionBank.getTransactions().size() == 2);

        Transaction withdrawalTransaction =
                transactionBank.getTransactions().get(1);

        check("Withdrawal transaction ID",
                withdrawalTransaction.getTransactionID().equals("T002"));

        check("Withdrawal transaction type",
                withdrawalTransaction.getTransactionType()
                        .equals("WITHDRAWAL"));

        // Transfer transaction
        transactionBank.transfer(
                "TX001",
                "TX002",
                new BigDecimal("300.00")
        );

        check("Transfer transaction recorded",
                transactionBank.getTransactions().size() == 3);

        Transaction transferTransaction =
                transactionBank.getTransactions().get(2);

        check("Transfer transaction ID",
                transferTransaction.getTransactionID().equals("T003"));

        check("Transfer transaction type",
                transferTransaction.getTransactionType()
                        .equals("TRANSFER"));

        check("Transfer sending account",
                transferTransaction.getAccountID().equals("TX001"));

        // Failed withdrawal should not be recorded
        int historyBeforeFailure =
                transactionBank.getTransactions().size();

        expectException("Transaction test insufficient funds",
                IllegalStateException.class,
                () -> transactionBank.withdraw(
                        "TX001", new BigDecimal("10000.00")));

        check("Failed withdrawal not recorded",
                transactionBank.getTransactions().size()
                        == historyBeforeFailure);

        // Failed transfer should not be recorded
        expectException("Transaction test same-account transfer",
                IllegalArgumentException.class,
                () -> transactionBank.transfer(
                        "TX001", "TX001",
                        new BigDecimal("100.00")));

        check("Failed transfer not recorded",
                transactionBank.getTransactions().size()
                        == historyBeforeFailure);

        // Defensive copy
        ArrayList<Transaction> historyCopy =
                transactionBank.getTransactions();

        historyCopy.clear();

        check("Transaction history protected",
                transactionBank.getTransactions().size() == 3);

        // Final balances
        check("Transaction test checking balance",
                account1.getBalance().compareTo(
                        new BigDecimal("800.00")) == 0);

        check("Transaction test savings balance",
                account2.getBalance().compareTo(
                        new BigDecimal("2300.00")) == 0);

        // ========================================
        // 10. TEST SUMMARY
        // ========================================

        System.out.println("\n=== TEST SUMMARY ===");

        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);
        System.out.println("Total: " + (passed + failed));

    }

    // ========================================
    // TEST HELPER METHODS
    // ========================================

    private static void check(String testName, boolean condition) {

        if (condition) {
            passed++;
            System.out.println("PASS: " + testName);
        } else {
            failed++;
            System.out.println("FAIL: " + testName);
        }
    }

    private static void expectException(
            String testName,
            Class<? extends Throwable> expectedType,
            Runnable action) {

        try {
            action.run();
            failed++;
            System.out.println("FAIL: " + testName
                    + " (No exception thrown)");

        } catch (Throwable e) {

            if (expectedType.isInstance(e)) {
                passed++;
                System.out.println("PASS: " + testName);
            } else {
                failed++;
                System.out.println("FAIL: " + testName
                        + " (Unexpected exception: "
                        + e.getClass().getSimpleName() + ")");
            }
        }
    }
}
