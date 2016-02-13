
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

public class AccountFrame extends JFrame implements ActionListener {
	ButtonGroup group;
	JCheckBox JRbtn1, JRbtn2, JRbtn3, JRbtn4, JRbtn5, JRbtn6;
	JPanel contentpane;
	JButton button;
	JMenuBar mb;
	JMenuItem menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9, menu10;
	int accountNumber;
	AccountManager AM;
	CheckAccountManager CM;
	SavingAccountManager SM;

	public AccountFrame() {
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		AM = new AccountManager("data/current_user.txt");
		accountNumber = ((Person_Account) (AM.returnFirst())).getAccountNumber();
		CM = new CheckAccountManager("data/users.txt");
		SM = new SavingAccountManager("data/users.txt");
		contentpane = new JPanel();
		contentpane.setLayout(null);
		setContentPane(contentpane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		mb = new JMenuBar();
		setJMenuBar(mb);
		JMenu menu = new JMenu("Account");
		mb.add(menu);
		menu1 = new JMenuItem("withdraw");
		menu2 = new JMenuItem("deposit");
		menu3 = new JMenuItem("transfer");
		menu4 = new JMenuItem("balance");
		menu5 = new JMenuItem("Log out");
		menu6 = new JMenuItem("My Account info");
		menu7 = new JMenuItem("Log-ins");
		menu8 = new JMenuItem("Transactions");
		menu9 = new JMenuItem("profile");
		menu.add(menu1);
		menu.add(menu2);
		menu.add(menu3);
		menu.add(menu4);
		menu.add(menu5);

		menu = new JMenu("View");
		mb.add(menu);
		menu.add(menu6);

		// menu.add(menu7);
		menu.add(menu8);
		menu = new JMenu("Edit");
		mb.add(menu);
		menu.add(menu9);

		group = new ButtonGroup();
		JRbtn1 = new JCheckBox("deposit");
		JRbtn1.setSelected(false);
		JRbtn1.setBounds(100, 30, 90, 60);
		group.add(JRbtn1);
		contentpane.add(JRbtn1);

		JRbtn2 = new JCheckBox("withdraw");
		JRbtn2.setBounds(100, 100, 90, 60);
		group.add(JRbtn2);
		contentpane.add(JRbtn2);

		JRbtn3 = new JCheckBox("transfer");
		JRbtn3.setBounds(100, 170, 100, 60);
		group.add(JRbtn3);
		contentpane.add(JRbtn3);

		JRbtn4 = new JCheckBox("view balance");
		JRbtn4.setBounds(350, 30, 200, 60);
		group.add(JRbtn4);
		contentpane.add(JRbtn4);

		JRbtn5 = new JCheckBox("change password");
		JRbtn5.setBounds(350, 100, 200, 60);
		group.add(JRbtn5);
		contentpane.add(JRbtn5);

		JRbtn6 = new JCheckBox("Log out");
		JRbtn6.setBounds(350, 170, 200, 60);
		group.add(JRbtn6);
		contentpane.add(JRbtn6);
		button = new JButton("Go");
		button.setBounds(220, 240, 100, 40);
		contentpane.add(button);
		// getContentPane().setLayout(new BorderLayout());
		// getContentPane().add("North",new JButton("Button"));
		// getContentPane().add("Center",new JLabel("a label"));
		// getContentPane().add("South",new JCheckBox("checkbox"));
		JRbtn1.addActionListener(this);
		JRbtn2.addActionListener(this);
		JRbtn3.addActionListener(this);
		JRbtn4.addActionListener(this);
		JRbtn5.addActionListener(this);
		JRbtn6.addActionListener(this);
		menu1.addActionListener(this);
		menu2.addActionListener(this);
		menu3.addActionListener(this);
		menu4.addActionListener(this);
		menu5.addActionListener(this);
		menu6.addActionListener(this);
		menu8.addActionListener(this);
		menu9.addActionListener(this);
		button.addActionListener(this);
		pack();
		setSize(600, 400);
		setVisible(true);
		repaint();

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == button) {
			if (JRbtn1.isSelected())
				new Deposit();
			if (JRbtn2.isSelected())
				new Withdraw();
			if (JRbtn3.isSelected())
				new Transfer();

			if (JRbtn4.isSelected()) {

				JOptionPane.showMessageDialog(button,
						"Checking: " + CM.getBalance(accountNumber) + "  Saving: " + SM.getBalance(accountNumber));
			}

			if (JRbtn6.isSelected())
				dispose();
			if (JRbtn5.isSelected())
				new ChangePassWord();
		} else if (e.getSource() == menu8) {

			TransactionHistArray TH = new TransactionHistArray(accountNumber);
			final Object[][] TABLE_DATA = TH.getTransactionHistArray();

			String[] COLUMN_NAMES = { "Account Number", "Account", "transaction type", "Amount", "Description",
					"Date" };
			String title = "Transactions";
			int[] model = { 1, 3, 3, 2, 3, 3 };
			new MyTable(TABLE_DATA, COLUMN_NAMES, title, model);

		} else if (e.getSource() == menu1) {
			new Withdraw();
		} else if (e.getSource() == menu2) {

			new Deposit();
		} else if (e.getSource() == menu3) {
			new Transfer();

		} else if (e.getSource() == menu4) {

			JOptionPane.showMessageDialog(button,
					"Checking: " + CM.getBalance(accountNumber) + "  Saving: " + SM.getBalance(accountNumber));
		} else if (e.getSource() == menu5) {

			dispose();
		} else if (e.getSource() == menu6) {

			new AccountInfo();
		} else if (e.getSource() == menu9) {

			new UpdateProfile();
		}

	}

	public static void main(String[] args) {

		new AccountFrame();

	}

}