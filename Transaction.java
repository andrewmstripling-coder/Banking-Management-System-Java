package bankingmanagement;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
	private final String transactionID;
	private final String accountID;
	private final String transactionType;
	private final BigDecimal amount;
	private final LocalDateTime transactionDate;
	
	public Transaction(
			String transactionID,
			String accountID,
			String transactionType,
			BigDecimal amount,
			LocalDateTime transactionDate) {
		if (transactionID == null|| transactionID.isBlank()){
			throw new IllegalArgumentException(
					"Transaction ID cannot be null or blank.");
		}
		
		if (accountID == null || accountID.isBlank()) {
			throw new IllegalArgumentException(
					"Account ID cannot be null or blank");
		}
		
		if(transactionType == null ||
				(!"DEPOSIT".equals(transactionType)&&
						!"WITHDRAWAL".equals(transactionType)&&
						!"TRANSFER".equals(transactionType))) {
			throw new IllegalArgumentException(
					"Transaction type must be DEPOSIT, WITHDRAWAL, or TRANSFER");
		}
		
		if (amount == null || amount.compareTo(BigDecimal.ZERO)<= 0) {
			throw new IllegalArgumentException(
					"Amount must be greater than zero.");
		}
		
		if (transactionDate == null) {
			throw new IllegalArgumentException(
					"Transaction date cannot be  null.");
		}
		
		this.transactionID = transactionID;
		this.accountID=accountID;
		this.transactionType=transactionType;
		this.amount=amount;
		this.transactionDate=transactionDate;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public String getAccountID() {
		return accountID;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public LocalDateTime getTransactionDate() {
		return transactionDate;
	}
}
