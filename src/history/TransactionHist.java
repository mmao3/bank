
package history;

import java.io.Serializable;
import java.util.Date;
import java.util.*;
import util.*;
import java.io.*;

public class TransactionHist implements Serializable {
	// private static final long serialVersionUID = 1L;
	int accountNumber;
	Date date;
	String type;
	double amount;
	String accountType;
	String description;
	ObjectOutputStream oops;

	public TransactionHist() {

	}

	public TransactionHist(Date date, int accountNumber, String type, double amount, String description,
			String accountType) {
		this.date = date;
		this.accountNumber = accountNumber;
		this.type = type;
		this.amount = amount;
		this.description = description;
		this.accountType = accountType;
	}

	public String getAccountType() {

		return accountType;
	}

	public void setAccountType(String accountType) {

		this.accountType = accountType;
	}

	public String getTpye() {

		return type;
	}

	public void setTpye(int String) {

		this.type = type;
	}

	public double getAmount() {

		return amount;
	}

	public void setAmount(double amount) {

		this.amount = amount;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getTransactionHist() {
		return date;
	}

	public void setTransactionHist(Date date) {
		this.date = date;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {
		this.description = description;

	}

	/*
	 * public void delete(int AccountNumber){ AccountManager AM=new
	 * AccountManager("data/transactions.txt"); ArrayList list=AM.load();
	 * ListIterator it = list.listIterator(); while(it.hasNext()) {
	 * if(((TransactionHist)(it.next())).getAccountNumber()==AccountNumber){
	 * System.out.println(it.nextIndex()); list.remove(it.nextIndex()-1);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * try{ oops = new ObjectOutputStream(new FileOutputStream(fileName));
	 * oops.writeObject(list); oops.close(); } catch(Exception e){
	 * 
	 * 
	 * e.printStackTrace(); }
	 * 
	 * }
	 */
	public TransactionHist getLastTransaction(int accountNumber, String accountType) {
		TransactionHist out = null;
		AccountManager AM = new AccountManager("data/transactions.txt");
		ArrayList list = AM.load();
		if (AM.getLength() > 0) {
			ListIterator it = list.listIterator(AM.getLength());
			while (it.hasPrevious()) {
				out = (TransactionHist) it.previous();
				if (out.getAccountNumber() == accountNumber && out.getAccountType().equals(accountType)) {

					return out;

				}

			}
			return null;

		} else {

			return null;

		}

	}

}
