package bankAccount;

//this account will charge transaction fees. every month there are 5transactions...$3 will deducted from the checking account for every extra transaction//
public class CheckingAccount extends BankAccount {

	private int instances = 1;
	private int fee;

	public CheckingAccount() {

	}

	public CheckingAccount(double balance) {
		super(balance);
	}

	// deposit method for depositing money in account//
	public void deposit(double amount) throws IllegalArgumentException {

		if (amount > 0) {

			this.setBalance(this.getBalance() + amount);

		} else {

			throw new IllegalArgumentException("Amount cannot be negative");
		}

	}

	// withdraw method for withdrawing money from the account//

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

	public static void main(String[] args) {

		CheckingAccount c = new CheckingAccount(100);
		try {
			c.deposit(20);
		}

		catch (Exception e) {

		}
		System.out.println(c);

	}

}
