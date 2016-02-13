package history;

import java.util.*;
import java.util.Date;
import util.*;
import bankAccount.*;
import person.*;

public class TransactionHistArray {

	AccountManager AM;
	Object[][] out;
	int accountNumber;
	ArrayList list;
	ArrayList list2;
	ListIterator it;

	public TransactionHistArray(int accountNumber) {
		this.accountNumber = accountNumber;
		AM = new AccountManager("data/transactions.txt");
		list = AM.load();
		list2 = new ArrayList();
		it = list.listIterator(AM.getLength());
		TransactionHist out = null;
		while (it.hasPrevious()) {
			out = (TransactionHist) it.previous();
			if (out.getAccountNumber() == accountNumber) {
				list2.add(out);

			}

		}
	}

	public TransactionHistArray() {
		AM = new AccountManager("data/transactions.txt");
		list = AM.load();

	}

	public Object[][] getAllTransactionHistArray() {

		if (list.size() > 0) {
			out = new Object[list.size()][6];
			for (int i = 0; i < list.size(); i++) {
				out[i][0] = ((TransactionHist) (list.get(i))).getAccountNumber();
				out[i][2] = ((TransactionHist) (list.get(i))).getTpye();
				out[i][1] = ((TransactionHist) (list.get(i))).getAccountType();
				out[i][3] = ((TransactionHist) (list.get(i))).getAmount();
				out[i][4] = ((TransactionHist) (list.get(i))).getDescription();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(((TransactionHist) (list.get(i))).getTransactionHist());
				out[i][5] = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.HOUR) + ": "
						+ calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " "
						+ (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");

			}

			return out;
		} else

			return null;
	}

	public Object[][] getTransactionHistArray() {
		if (list2.size() > 0) {
			out = new Object[list2.size()][6];
			for (int i = 0; i < list2.size(); i++) {
				out[i][0] = ((TransactionHist) (list2.get(i))).getAccountNumber();
				out[i][2] = ((TransactionHist) (list2.get(i))).getTpye();
				out[i][1] = ((TransactionHist) (list2.get(i))).getAccountType();
				out[i][3] = ((TransactionHist) (list2.get(i))).getAmount();
				out[i][4] = ((TransactionHist) (list2.get(i))).getDescription();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(((TransactionHist) (list2.get(i))).getTransactionHist());
				out[i][5] = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
						+ calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.HOUR) + ":"
						+ calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " "
						+ (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM");

			}
			return out;

		} else

			return null;

	}

}
