/**
 * 
 */
package bankingmanagement;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * 
 */
public abstract class BankAccount {
	private final String accountNumber;
	private final String customerID;
	private BigDecimal balance;
	private final LocalDate dateOpened;
	
	public BankAccount(
			String accountNumber,
			String customerID,
			BigDecimal balance,
			LocalDate dateOpened) {
		
		if(accountNumber ==null|| accountNumber.isBlank()) {
			throw new IllegalArgumentException("Invalid account number.");
		}
		if(customerID ==null|| customerID.isBlank()) {
			throw new IllegalArgumentException("Invalid customer ID.");
		}
		if (balance ==null|| balance.compareTo(BigDecimal.ZERO)<0) {
			throw new IllegalArgumentException("Invalid initial balance.");
		}
		if(dateOpened == null) {
			throw new IllegalArgumentException("Invalid opening date.");
		}
		
		this.accountNumber=accountNumber;
		this.customerID=customerID;
		this.balance=balance;
		this.dateOpened=dateOpened;
		
		
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getCustomerID() {
		return customerID;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public LocalDate getDateOpened() {
		return dateOpened;
	}
	
	public void deposit(BigDecimal amount) {
		if (amount == null||  amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new IllegalArgumentException("Invalid deposit amount.");
		}
		
				balance = balance.add(amount);
	}
	public void withdraw(BigDecimal amount) {
		if (amount == null||  amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new IllegalArgumentException("Invalid withdraw amount.");
		}
		if(balance.compareTo(amount)<0) {
			throw new IllegalStateException("Insufficient funds.");
		}
				balance = balance.subtract(amount);
	}
	protected void setBalance(BigDecimal newBalance) {
		if (newBalance ==null) {
			throw new IllegalArgumentException("Invalid balance.");
		}
		this.balance=newBalance;
		}
	}

