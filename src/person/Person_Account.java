package person;

import java.io.Serializable;
import util.*;
import bankAccount.*;

public class Person_Account implements Serializable {

	PersonDetails pdl;
	CheckingAccount ca;
	SavingsAccount sa;
	Lock lock;
	int accountNumber;

	public Person_Account(PersonDetails pdl, CheckingAccount ca, SavingsAccount sa, int accountNumber) {

		this.ca = ca;
		this.sa = sa;
		this.pdl = pdl;
		lock = new Lock();
		this.accountNumber = accountNumber;
	}

	public void setAccountNumber(int accountNumber) {

		this.accountNumber = accountNumber;
	}

	public int getAccountNumber() {

		return accountNumber;
	}

	public Lock getLock() {
		return lock;

	}

	public void setLock(Lock lock) {
		this.lock = lock;

	}

	public PersonDetails getPdl() {
		return pdl;
	}

	public void setPdl(PersonDetails pdl) {
		this.pdl = pdl;
	}

	public CheckingAccount getCa() {
		return ca;
	}

	public void setCa(CheckingAccount ca) {
		this.ca = ca;
	}

	public SavingsAccount getSa() {
		return sa;
	}

	public void setSa(SavingsAccount sa) {
		this.sa = sa;
	}

}
