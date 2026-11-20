package bankingmanagement;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.math.RoundingMode;

public class SavingsAccount extends BankAccount{
	
	private final BigDecimal annualInterestRate;
	private final BigDecimal minimumBalance;

	public SavingsAccount(
			String accountNumber,
			String customerID,
			BigDecimal balance,
			LocalDate dateOpened,
			BigDecimal annualInterestRate,
			BigDecimal minimumBalance) {
		
		super (accountNumber, customerID, balance, dateOpened);
		
		if(annualInterestRate == null ||
				annualInterestRate.compareTo(BigDecimal.ZERO)<0) {
		throw new IllegalArgumentException("Invalid annual interest rate.");
		}
		
		if(minimumBalance == null ||
				minimumBalance.compareTo(BigDecimal.ZERO)<0) {
			throw new IllegalArgumentException("Invalid minimum balance.");
		}
		
		if(balance.compareTo(minimumBalance)<0) {
			throw new IllegalArgumentException(
					"Initial balance is below the required minimum");
		}
		
		this.annualInterestRate=annualInterestRate;
		this.minimumBalance=minimumBalance;
		}

	public BigDecimal getAnnualInterestRate() {
		return annualInterestRate;
	}

	public BigDecimal getMinimumBalance() {
		return minimumBalance;
	}
	
	@Override
	public void withdraw(BigDecimal amount) {
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0) {
			throw new IllegalArgumentException("Invalid withdrawal amount.");
		}
		BigDecimal newBalance = getBalance().subtract(amount);
		
		if(newBalance.compareTo(minimumBalance)<0) {
			throw new IllegalStateException("Minimum balance requirement violated.");
		}
		setBalance(newBalance);
		}
	
	public BigDecimal calculateMonthlyInterest() {
		
		BigDecimal annualInterest =
				getBalance().multiply(annualInterestRate);
				
		BigDecimal monthlyInterest =
					annualInterest.divide(BigDecimal.valueOf(12),2,RoundingMode.HALF_UP);
		
		return monthlyInterest;
	}
	
	public void applyMonthlyInterest() {
		
		BigDecimal interest = calculateMonthlyInterest();
		
		if(interest.compareTo(BigDecimal.ZERO) > 0) {
		deposit(interest);
	}
	}}

	

