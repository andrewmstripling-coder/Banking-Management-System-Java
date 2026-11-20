package bankingmanagement;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bank {
	
	private final String bankName;
	private final ArrayList<Customer> customers;
	private final ArrayList<BankAccount> accounts;
	private final ArrayList<Transaction> transactions;
	private int transactionCounter;
	
	public Bank(
			String bankName
			) {
		if(bankName ==null || bankName.isBlank()) {
			throw new IllegalArgumentException(
					"Bank name cannot be null or blank.");
		}
		this.bankName = bankName;
		this.customers= new ArrayList<>();
		this.accounts= new ArrayList<>();
		this.transactions = new ArrayList<>();
		this.transactionCounter = 0;
	}
	
	public void addCustomer(Customer customer) {
	
		if (customer == null){
			throw new IllegalArgumentException("Customer cannot be null");
		}
		
			for (Customer existingCustomer:customers) {
				
				if(existingCustomer.getCustomerID().equals(
			customer.getCustomerID())){
				throw new IllegalArgumentException(
						"Customer ID already exists.");
			}
	}
			customers.add(customer);

}
	public Customer findCustomer(String customerID) {
		
		for (Customer customer: customers) {
			
			if(customer.getCustomerID().equals(customerID)){
				return customer;
			
				}
			}
		return null;
		}
	
	public void addAccount(BankAccount account) {
	
	if (account == null){
		throw new IllegalArgumentException(
				"Account cannot be null");
	}
	
	for (BankAccount existingAccount: accounts) {
		
	
		if(existingAccount.getAccountNumber().equals(account.getAccountNumber())){
		
			throw new IllegalArgumentException("Account number already exists");
		
			}
	}
		accounts.add(account);
	}

	public BankAccount findAccount(String accountNumber) {
		
		for (BankAccount existingAccount: accounts) {
			
			if (existingAccount.getAccountNumber().equals(accountNumber)) {
				return existingAccount;
		}
				}
		return null;
}

	public String getBankName() {
		return bankName;
	}
	public void deposit(String accountNumber, BigDecimal amount) {

	    BankAccount account = findAccount(accountNumber);

	    if (account == null) {
	        throw new IllegalArgumentException("Bank account not found");
	    }

	    if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new IllegalArgumentException(
	                "Amount must be greater than zero.");
	    }

	    account.deposit(amount);
	    
	    Transaction transaction = new Transaction(
	    		generateTransactionID(),
	    		accountNumber,
	    		"DEPOSIT",
	    		amount,
	    		LocalDateTime.now());
	    
	    transactions.add(transaction);
	}
	
	
	public void withdraw(String accountNumber, BigDecimal amount) {

	    BankAccount account = findAccount(accountNumber);

	    if (account == null) {
	        throw new IllegalArgumentException("Bank account not found");
	    }

	    if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
	        throw new IllegalArgumentException(
	                "Amount must be greater than zero.");
	    }

	    account.withdraw(amount);
	    
	    Transaction transaction = new Transaction(
	    		generateTransactionID(),
	    		accountNumber,
	    		"WITHDRAWAL",
	    		amount,
	    		LocalDateTime.now());
	    
	    transactions.add(transaction);
	}
	
	public void transfer(String fromAccountNumber,
            String toAccountNumber,
            BigDecimal amount) {

BankAccount fromAccount = findAccount(fromAccountNumber);
BankAccount toAccount = findAccount(toAccountNumber);

if (fromAccount == null || toAccount == null) {
throw new IllegalArgumentException(
       "Bank account not found.");
}

if (fromAccount == toAccount) {
throw new IllegalArgumentException(
       "Cannot transfer money to the same account.");
}

if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
throw new IllegalArgumentException(
       "Transfer amount must be greater than zero.");
}

fromAccount.withdraw(amount);
toAccount.deposit(amount);

Transaction transaction = new Transaction(
		generateTransactionID(),
		fromAccountNumber,
		"TRANSFER",
		amount,
		LocalDateTime.now()
		);

transactions.add(transaction);
}
	private String generateTransactionID() {
		transactionCounter++;
		return String.format("T%03d", transactionCounter);
	}
	
	public ArrayList<Transaction> getTransactions() {
		return new ArrayList<>(transactions);
	}
}
