package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import custom_Jcomponent.*;
import util.*;
import bankAccount.*;
import person.*;
import history.*;

public class AdminFrame extends AccountFrame {
	JMenuItem menu11;
	JMenuItem menu12;
	JMenuItem menu13;
	JMenuItem menu14;
	JMenuItem menu15;

	JMenuItem menu16;
	JMenuItem menu17;
	JMenuItem menu18;
	JMenuItem menu19;
	JMenuItem menu20;
	AccountManager AM;

	public AdminFrame() {
		super();
		AM = new AccountManager("data/users.txt");
		JMenu menu = new JMenu("Admin");
		mb.add(menu);
		menu11 = new JMenuItem(" all users");
		menu.add(menu11);
		menu12 = new JMenuItem("all transactions");
		menu.add(menu12);
		menu14 = new JMenuItem("search account");
		menu.add(menu14);
		menu15 = new JMenuItem("search transactions");
		menu.add(menu15);

		menu16 = new JMenuItem("deposit");
		menu.add(menu16);
		menu17 = new JMenuItem("charge");
		menu.add(menu17);
		menu18 = new JMenuItem("unlock account");
		menu.add(menu18);

		menu19 = new JMenuItem("update profile");
		menu.add(menu19);

		menu20 = new JMenuItem("add admin");
		menu.add(menu20);

		menu13 = new JMenuItem("delete account");
		menu.add(menu13);

		menu11.addActionListener(this);
		menu12.addActionListener(this);
		menu14.addActionListener(this);
		menu15.addActionListener(this);
		menu13.addActionListener(this);
		menu16.addActionListener(this);
		menu17.addActionListener(this);
		menu18.addActionListener(this);
		menu19.addActionListener(this);
		menu20.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		if (e.getSource() == menu11) {
			AllUsers TH = new AllUsers();
			final Object[][] TABLE_DATA = TH.getAllUsers();

			String[] COLUMN_NAMES = { "Account Number", "First Name", "Middle Name", "Last Name", "Email", "SSN",
					"Date of  Birth", "Checking Balance", "Saving Balance" };
			String title = "Transactions";
			int[] model = { 1, 3, 3, 3, 3, 3, 3, 2, 2 };
			new MyTable(TABLE_DATA, COLUMN_NAMES, title, model);

		} else if (e.getSource() == menu12) {
			TransactionHistArray TH = new TransactionHistArray();
			final Object[][] TABLE_DATA = TH.getAllTransactionHistArray();

			String[] COLUMN_NAMES = { "Account Number", "Account", "transaction type", "Amount", "Description",
					"Date" };
			String title = "Transactions";
			int[] model = { 1, 3, 3, 2, 3, 3 };
			new MyTable(TABLE_DATA, COLUMN_NAMES, title, model);

		} else if (e.getSource() == menu15) {
			String accountNumber = JOptionPane.showInputDialog("Input account number");
			int a = 0;
			if (accountNumber != "" && accountNumber != null) {
				try {
					a = Integer.parseInt(accountNumber.trim());
					if (!AM.isExists(a))
						JOptionPane.showMessageDialog(null, "The account number does not exist");
					else {

						TransactionHistArray TH = new TransactionHistArray(a);
						final Object[][] TABLE_DATA = TH.getTransactionHistArray();

						String[] COLUMN_NAMES = { "Account Number", "Account", "transaction type", "Amount",
								"Description", "Date" };
						String title = "Transactions";
						int[] model = { 1, 3, 3, 2, 3, 3 };
						new MyTable(TABLE_DATA, COLUMN_NAMES, title, model);

					}
				} catch (Exception E) {

					JOptionPane.showMessageDialog(null, "The account number you typed is not valid");
					return;

				}
			}

		} else if (e.getSource() == menu14) {
			String accountNumber = JOptionPane.showInputDialog("Input account number");
			int a = 0;
			if (accountNumber != "" && accountNumber != null) {
				try {
					a = Integer.parseInt(accountNumber.trim());

					if (!AM.isExists(a))
						JOptionPane.showMessageDialog(null, "The account number does not exist");
					else {
						new AccountInfo(a);

					}

				} catch (Exception E) {

					JOptionPane.showMessageDialog(null, "The account number you typed is not valid");

				}
			}

		} else if (e.getSource() == menu16) {

			String accountNumber = JOptionPane.showInputDialog("Input account number");
			int a = 0;
			if (accountNumber != "" && accountNumber != null) {
				try {
					a = Integer.parseInt(accountNumber.trim());
					if (!AM.isExists(a))
						JOptionPane.showMessageDialog(null, "The account number does not exist");
					else {

						new Deposit(a);

					}
				} catch (Exception E) {

					JOptionPane.showMessageDialog(null, "The account number you typed is not valid");

				}
			}

		} else if (e.getSource() == menu17) {
			String accountNumber = JOptionPane.showInputDialog("Input account number");
			int a = 0;
			if (accountNumber != "" && accountNumber != null) {
				try {
					a = Integer.parseInt(accountNumber.trim());
					if (!AM.isExists(a))
						JOptionPane.showMessageDialog(null, "The account number does not exist");
					else {

						new Withdraw(a);

					}
				} catch (Exception E) {

					JOptionPane.showMessageDialog(null, "The account number you typed is not valid");

				}
			}

		} else if (e.getSource() == menu18) {
			String accountNumber = JOptionPane.showInputDialog("Input account number");
			int a = 0;
			if (accountNumber != "" && accountNumber != null) {
				try {
					a = Integer.parseInt(accountNumber.trim());

					a = Integer.parseInt(accountNumber.trim());
					if (!AM.isExists(a))
						JOptionPane.showMessageDialog(null, "The account number does not exist");
					else {
						Person_Account p = ((Person_Account) (AM.getObject(a)));
						if (p.getLock().isLocked()) {
							p.getLock().removeLock();
							AccountManager AM;
							AM = new AccountManager("data/users.txt");
							AM.update(a, p);
							JOptionPane.showMessageDialog(null, "The account's lock was removed");
						}

						else {

							JOptionPane.showMessageDialog(null, "The account has no lock. No need to unlock!");
						}
					}
				} catch (Exception E) {

					JOptionPane.showMessageDialog(null, "The account number you typed is not valid");

				}
			}

		} else if (e.getSource() == menu19) {

			String accountNumber = JOptionPane.showInputDialog("Input account number");
			int a = 0;
			if (accountNumber != "" && accountNumber != null) {
				try {
					a = Integer.parseInt(accountNumber.trim());
					if (!AM.isExists(a))
						JOptionPane.showMessageDialog(null, "The account number does not exist");
					else {

						new UpdateProfile_(a);

					}
				} catch (Exception E) {

					JOptionPane.showMessageDialog(null, "The account number you typed is not valid");

				}
			}

		} else if (e.getSource() == menu20) {

			String accountNumber = JOptionPane.showInputDialog("Input account number");
			int a = 0;
			if (accountNumber != "" && accountNumber != null) {
				try {
					a = Integer.parseInt(accountNumber.trim());
					if (!AM.isExists(a))
						JOptionPane.showMessageDialog(null, "The account number does not exist");
					else {
						Person_Account p = ((Person_Account) (AM.getObject(a)));
						p.getPdl().setIdentity(1);
						AccountManager AM;
						AM = new AccountManager("data/users.txt");
						AM.update(a, p);
						System.out.println(a);

						JOptionPane.showMessageDialog(null, "The account has been changed to admin");

					}
				} catch (Exception E) {

					JOptionPane.showMessageDialog(null, "The account number you typed is not valid");

				}
			}

		} else if (e.getSource() == menu13) {

			String accountNumber = JOptionPane.showInputDialog("Input account number");
			int a = 0;
			if (accountNumber != "" && accountNumber != null) {
				try {
					a = Integer.parseInt(accountNumber.trim());
					if (!AM.isExists(a))
						JOptionPane.showMessageDialog(null, "The account number does not exist");
					else {
						AccountManager AM = new AccountManager("data/users.txt");
						// TransactionHist AM1=new TransactionHist();
						AM.delete(a);
						// AM1.delete(a);
						JOptionPane.showMessageDialog(null, "The account has been delete");

					}
				} catch (Exception E) {

					JOptionPane.showMessageDialog(null, "The account number you typed is not valid");

				}
			}

		}

	}

	public static void main(String[] args) {

		new AdminFrame();

	}

}