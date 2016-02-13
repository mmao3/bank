package bankAccount;
// this will earn income at fixed interest rate. Interest rate will calculate at each transactions. If it is less than one day after the nearest transaction, one  Interest will gain.   

public class SavingsAccount extends BankAccount {

	private final double interestRate = 0.01;

	public SavingsAccount() {

	}

	public SavingsAccount(double balance) {
		super(balance);

	}

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

	// add method to add interest each day //
	public double addInterest(int noOfdays) {

		double preBlance = this.getBalance();

		if (noOfdays > 0) {

			for (int i = 1; i <= noOfdays; i++) {

				this.setBalance(this.getBalance() + ((interestRate / 100) * this.getBalance()));

			}
		}

		return this.getBalance() - preBlance;
	}

	public double getInterestRate() {
		return interestRate;
	}

}
