package util;

import java.io.*;
import java.util.*;
import person.*;
import bankAccount.*;
import history.*;

public class CheckAccountManager extends AccountManager {

	AccountManager AM;

	public CheckAccountManager(String fileName) {

		super(fileName);
		AM = new AccountManager("data/transactions.txt");

	}

	public void deposit(int accountNumber, double amount) throws IllegalArgumentException {
		Person_Account p = (Person_Account) getObject(accountNumber);
		CheckingAccount Ca = p.getCa();
		Ca.deposit(amount);
		update(accountNumber, p);

		AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumber, "deposit", amount, " ",
				"Checking"));

	}

	public int transfer(int accountNumberFrom, int accountNumberTo, double amount)
			throws IllegalArgumentException, InsufficientFundsException {
		Person_Account p1 = (Person_Account) getObject(accountNumberFrom);
		CheckingAccount Ca1 = p1.getCa();
		Person_Account p2 = (Person_Account) getObject(accountNumberTo);
		CheckingAccount Ca2 = p2.getCa();
		if (Ca2 != null) {
			Ca1.transfer(amount, Ca2);
			update(accountNumberFrom, p1);
			update(accountNumberTo, p2);

			AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumberFrom, "transfer", amount,
					"Transfer to " + accountNumberTo, "Checking"));
			AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumberTo, "transfer", amount,
					"Transfer from " + accountNumberFrom, "Checking"));
			return 1;
		} else {

			return 0;
		}
	}

	public void withdraw(int accountNumber, double amount) throws IllegalArgumentException, InsufficientFundsException {

		Person_Account p = (Person_Account) getObject(accountNumber);
		CheckingAccount Ca = p.getCa();
		Ca.withdraw(amount);
		update(accountNumber, p);
		AM.insert(new TransactionHist(Calendar.getInstance().getTime(), accountNumber, "withdraw", amount, " ",
				"Checking"));

	}

	public double getBalance(int accountNumber) {

		Person_Account p = (Person_Account) getObject(accountNumber);
		CheckingAccount Ca = p.getCa();
		return Ca.getBalance();

	}

}