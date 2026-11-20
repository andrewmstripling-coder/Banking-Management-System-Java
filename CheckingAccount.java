package bankingmanagement;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CheckingAccount extends BankAccount {
	
	private final BigDecimal overdraftLimit;
	private final BigDecimal monthlyFee;
	
	public CheckingAccount(
			String accountNumber,
			String customerID,
			BigDecimal balance,
			LocalDate dateOpened,
			BigDecimal overdraftLimit,
			BigDecimal monthlyFee) {
	super(accountNumber, customerID, balance, dateOpened);
	
	if (overdraftLimit ==null || overdraftLimit.compareTo(BigDecimal.ZERO)<0) {
		throw new IllegalArgumentException("Invalid overdraft limit.");
	}
	if (monthlyFee == null || monthlyFee.compareTo(BigDecimal.ZERO)<0) {
		throw new IllegalArgumentException("Invalid monthly fee.");
	}
	this.overdraftLimit=overdraftLimit;
	this.monthlyFee=monthlyFee;
	}

	public BigDecimal getOverdraftLimit() {
		return overdraftLimit;
	}

	public BigDecimal getMonthlyFee() {
		return monthlyFee;
	}
	
	
	@Override
	public void withdraw(BigDecimal amount) {
		if (amount == null||  amount.compareTo(BigDecimal.ZERO) <= 0){
			throw new IllegalArgumentException("Invalid withdrawal amount.");
		}
		BigDecimal newBalance = getBalance().subtract(amount);
		if(newBalance.compareTo(overdraftLimit.negate())<0) {
			throw new IllegalStateException("Overdraft limit exceeded.");
		}
		setBalance(newBalance);
	}
	
	public void applyMonthlyFee() {
		if (monthlyFee.compareTo(BigDecimal.ZERO) > 0) {
			withdraw(monthlyFee);

		}
		
	}


}
