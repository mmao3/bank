package util;

import java.io.*;
import java.util.*;
import person.*;
import bankAccount.*;
import history.*;

public class SavingAccountManager extends AccountManager {

	AccountManager AM;

	public SavingAccountManager(String fileName) {

		super(fileName);
		AM = new AccountManager("data/transactions.txt");

	}

	public void deposit(int accountNumber, double amount) throws IllegalArgumentException {
		Person_Account p = (Person_Account) getObject(accountNumber);
		SavingsAccount Sa = p.getSa();
		TransactionHist LastTransaction = (new TransactionHist()).getLastTransaction(accountNumber, "Saving");
		if (LastTransaction != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(LastTransaction.getTransactionHist());
			double interest = Sa
					.addInterest((int) ((Calendar.getInstance().getTimeInMillis() - c.getTimeInMillis()) / 1000 / 60));
			if (interest > 0)
				AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumber, "interest", interest,
						"  ", "Saving"));

		}
		Sa.deposit(amount);

		update(accountNumber, p);

		AM.insert(
				new TransactionHist(Calendar.getInstance().getTime(), accountNumber, "deposit", amount, " ", "Saving"));

	}

	public int transfer(int accountNumberFrom, int accountNumberTo, double amount)
			throws IllegalArgumentException, InsufficientFundsException {
		Person_Account p1 = (Person_Account) getObject(accountNumberFrom);
		SavingsAccount Sa1 = p1.getSa();
		Person_Account p2 = (Person_Account) getObject(accountNumberTo);
		SavingsAccount Sa2 = p2.getSa();
		if (Sa2 != null) {
			TransactionHist LastTransaction = (new TransactionHist()).getLastTransaction(accountNumberFrom, "Saving");
			if (LastTransaction != null) {
				Calendar c = Calendar.getInstance();
				c.setTime(LastTransaction.getTransactionHist());
				double interest = Sa1.addInterest(
						(int) ((Calendar.getInstance().getTimeInMillis() - c.getTimeInMillis()) / 1000 / 60));
				if (interest > 0)
					AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumberFrom, "interest",
							interest, " ", "Saving"));

			}

			LastTransaction = (new TransactionHist()).getLastTransaction(accountNumberTo, "Saving");
			if (LastTransaction != null) {
				Calendar c = Calendar.getInstance();
				c.setTime(LastTransaction.getTransactionHist());
				double interest = Sa2.addInterest(
						(int) ((Calendar.getInstance().getTimeInMillis() - c.getTimeInMillis()) / 1000 / 60));
				if (interest > 0)
					AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumberTo, "interest",
							interest, " ", "Saving"));

			}

			Sa1.transfer(amount, Sa2);

			update(accountNumberFrom, p1);
			update(accountNumberTo, p2);

			AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumberFrom, "transfer", amount,
					"transfer to " + accountNumberTo, "Saving"));
			AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumberTo, "transfer", amount,
					"transfer From " + accountNumberFrom, "Saving"));

			return 1;
		} else

			return 0;

	}

	public void withdraw(int accountNumber, double amount) throws IllegalArgumentException, InsufficientFundsException {

		Person_Account p = (Person_Account) getObject(accountNumber);
		SavingsAccount Sa = p.getSa();
		TransactionHist LastTransaction = (new TransactionHist()).getLastTransaction(accountNumber, "Saving");
		if (LastTransaction != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(LastTransaction.getTransactionHist());
			double interest = Sa
					.addInterest((int) ((Calendar.getInstance().getTimeInMillis() - c.getTimeInMillis()) / 1000 / 60));
			if (interest > 0)
				AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumber, "interest", interest,
						" ", "Saving"));

		}
		Sa.withdraw(amount);

		update(accountNumber, p);

		AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumber, "withdraw", amount, " ",
				"Saving"));

	}

	public double getBalance(int accountNumber) {

		Person_Account p = (Person_Account) getObject(accountNumber);
		SavingsAccount Sa = p.getSa();
		return Sa.getBalance();

	}

}