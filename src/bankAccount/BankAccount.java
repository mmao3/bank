package bankAccount;

import java.io.Serializable;

//Class named BankAccount with field named balance
public class BankAccount implements Serializable {

	// Field balance
	private double balance = 0.00;

	// default constructor

	public BankAccount() {

	}

	// constructor that creates a bank account with a specified balance
	public BankAccount(double balance) {

		// try catch block to handle negative amount entered for balance.
		try {

			if (balance > 0) {

				this.balance = balance;

			} else {

				throw new IllegalArgumentException("Balance cannot be negative");
			}
		} catch (IllegalArgumentException e) {

			System.out.println("IllegalArgumentException: " + e.getMessage());

		}

	}

	// deposit method to deposit amount to the account
	public void deposit(double amount) throws IllegalArgumentException {

		if (amount > 0) {

			this.balance = balance + amount;
		} else {

			throw new IllegalArgumentException("In deposit method  :   Amount cannot be negative");
		}

	}

	// withdrawal method to withdraw amount from balance

	public void withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {

		if (amount < 0) {

			throw new IllegalArgumentException("Amount cannot be negative");

		}
		if (amount > this.getBalance()) {

			throw new InsufficientFundsException("InsufficientFundsException: Amount cannot be more than balance");
		}

		else {
			this.setBalance(this.getBalance() - amount);

		}
	}

	public void transfer(double amount, BankAccount account2)
			throws IllegalArgumentException, InsufficientFundsException {

		account2.deposit(amount);
		this.withdraw(amount);

	}

	public String toString() {

		String accountDesc = null;

		accountDesc = "This account has " + this.getBalance() + " as balance.";
		return (accountDesc);

	}

	public double getBalance() {
		return (double) (Math.round(balance * 100) / 100.0);
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
