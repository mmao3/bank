
package util;

import java.io.*;
import java.util.*;
import person.*;
import history.*;

public class AccountManager {
	String fileName;
	FileInputStream fips;
	ObjectOutputStream oops;

	public AccountManager(String fileName) {
		this.fileName = fileName;
		try {
			File fi = new File(fileName);
			if (!fi.exists()) {
				fi.createNewFile();
				oops = new ObjectOutputStream(new FileOutputStream(fi));
				ArrayList l = new ArrayList();
				oops.writeObject(l);
				oops.flush();
				oops.close();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public Object returnFirst() {

		ArrayList list = this.load();
		if (list != null)
			return list.get(0);
		else
			return null;
	}

	public void insert(Object o) {

		ArrayList list = this.load();
		list.add(o);
		try {
			oops = new ObjectOutputStream(new FileOutputStream(fileName));
			oops.writeObject(list);
			oops.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void clear() {
		ArrayList list = load();
		if (list != null)
			list.clear();
		try {
			oops = new ObjectOutputStream(new FileOutputStream(fileName));
			oops.writeObject(list);
			oops.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	public void delete(int AccountNumber) {

		ArrayList list = load();
		ListIterator it = list.listIterator();
		while (it.hasNext()) {
			if (((Person_Account) (it.next())).getAccountNumber() == AccountNumber) {
				System.out.println(it.nextIndex());
				list.remove(it.nextIndex() - 1);
				try {
					oops = new ObjectOutputStream(new FileOutputStream(fileName));
					oops.writeObject(list);
					oops.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
				break;

			}

		}

	}

	public void update(int AccountNumber, Object o) {

		ArrayList list = load();
		ListIterator it = list.listIterator();
		while (it.hasNext()) {
			if (((Person_Account) (it.next())).getAccountNumber() == AccountNumber) {
				list.remove(it.nextIndex() - 1);
				list.add(o);
				try {
					oops = new ObjectOutputStream(new FileOutputStream(fileName));
					oops.writeObject(list);
					oops.close();
				} catch (Exception e) {

					e.printStackTrace();
				}
				break;
			}

		}

	}

	public boolean isExists(int AccountNumber) {

		ArrayList list = load();
		if (list == null)
			return false;
		ListIterator it = list.listIterator();
		while (it.hasNext()) {
			if (((Person_Account) (it.next())).getAccountNumber() == AccountNumber)
				return true;
		}
		return false;

	}

	public Object getObject(int AccountNumber) {

		ArrayList list = load();
		if (list == null)
			return null;
		ListIterator it = list.listIterator();
		while (it.hasNext()) {
			if (((Person_Account) (it.next())).getAccountNumber() == AccountNumber)
				return list.get(it.nextIndex() - 1);
		}
		System.out.print("Hello");
		return null;

	}

	public int getLength() {

		ArrayList list = this.load();
		if (list != null)
			return list.size();
		else
			return 0;
	}

	public ArrayList load() {
		ArrayList list = null;
		try {
			ObjectInputStream oips = new ObjectInputStream(new FileInputStream(fileName));

			list = (ArrayList) (oips.readObject());
			oips.close();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return list;

	}

	public String toString() {
		String out = "";

		ArrayList list = this.load();
		if (list == null)
			return null;
		ListIterator it = list.listIterator();
		Person_Account tem = null;
		while (it.hasNext()) {
			tem = (Person_Account) it.next();
			out = out + " " + tem.getPdl().getEmail();
			out = out + " " + tem.getAccountNumber();
			if (tem.getLock().isLocked()) {

				out += " " + tem.getLock().getLockTimeInDate();
			}
			out += '\n';

		}
		return out;
	}

	public static void main(String[] args) throws Exception {

		/*
		 * AccountManager f = new AccountManager("final.txt");
		 * 
		 * Person n1,n2,n3;
		 * 
		 * n1=new Person("hj","hgq","hjiy"); n2=new Person("hj","hgqqw","hjiy");
		 * n3=new Person("hj","hgqwa","hjiy");
		 * 
		 * PersonDetails p1=new
		 * PersonDetails(n1,"ghj","jjk","1234565544","123786",new
		 * Date(),"mmao3@binghamton.edu"); PersonDetails p2=new
		 * PersonDetails(n2,"jkk","klk","1284565544","9868789",new
		 * Date(),"mmao2@binghamton.edu"); PersonDetails p3=new
		 * PersonDetails(n3,"klll","kl","1764565544","76578990",new
		 * Date(),"mmao4@binghamton.edu");
		 * 
		 * f.insert(new Person_Account(p1,new CheckingAccount(123),new
		 * SavingsAccount(),123));
		 * 
		 * f.insert(new Person_Account(p2,new CheckingAccount(125),new
		 * SavingsAccount(),35));
		 * 
		 * f.insert(new Person_Account(p3,new CheckingAccount(567),new
		 * SavingsAccount(),567));
		 * 
		 * //f.delete(13431); // System.out.println(f.isExists(12312));
		 * //System.out.println(f.isExists(13431)); System.out.println(f);
		 * 
		 * System.out.println(f.getLength());
		 */
		// System.out.println(f.load());
	}
}