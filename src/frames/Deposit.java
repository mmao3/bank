package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import custom_Jcomponent.*;
import util.*;
import bankAccount.*;
import person.*;

public class Deposit extends JFrame implements ActionListener {
	JPanel contentpane;
	JLabel label, amount;
	JTextField amount_;
	JComboBox type;
	JButton button, cancel;
	int accountNumber;
	AccountManager AM;
	CheckAccountManager CM;
	SavingAccountManager SM;
	// returnFirst

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {

			dispose();
		}
		if (e.getSource() == button) {

			if (amount_.getText().length() == 0) {

				JOptionPane.showMessageDialog(button, "Amount cannot be empty!");
			} else {

				if (type.getSelectedIndex() == 0) {
					try {
						CM.deposit(accountNumber, Double.parseDouble(amount_.getText().trim()));
						JOptionPane.showMessageDialog(button, "Your transaction is successful");
					} catch (IllegalArgumentException E) {
						JOptionPane.showMessageDialog(button, "Amount is illegal!");

					}

				} else {
					try {
						SM.deposit(accountNumber, Double.parseDouble(amount_.getText().trim()));
						JOptionPane.showMessageDialog(button, "Your transaction is successful");
					} catch (IllegalArgumentException E) {
						JOptionPane.showMessageDialog(button, "Amount is illegal!");

					}

				}

			}

		}

	}

	public Deposit() {
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		AM = new AccountManager("data/current_user.txt");
		CM = new CheckAccountManager("data/users.txt");
		SM = new SavingAccountManager("data/users.txt");
		accountNumber = ((Person_Account) (AM.returnFirst())).getAccountNumber();
		setSize(300, 300);
		setVisible(true);
		setTitle("Deposit");
		contentpane = new JPanel();
		setContentPane(contentpane);
		contentpane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		label = new JLabel("Type:");
		label.setBounds(40, 50, 70, 24);
		contentpane.add(label);

		String[] s = { "Checking", "Saving" };
		type = new JComboBox(s);
		type.setBounds(140, 50, 120, 24);
		contentpane.add(type);

		amount = new JLabel("Amount");
		amount.setBounds(40, 100, 70, 24);
		contentpane.add(amount);

		amount_ = new JTextField();
		amount_.setBounds(140, 100, 120, 24);
		contentpane.add(amount_);
		button = new JButton("OK");
		button.setBounds(40, 150, 80, 24);
		contentpane.add(button);

		cancel = new JButton("cancle");
		cancel.setBounds(140, 150, 80, 24);
		contentpane.add(cancel);
		cancel.addActionListener(this);
		button.addActionListener(this);

	}

	public Deposit(int accountNumber) {
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		AM = new AccountManager("data/current_user.txt");
		CM = new CheckAccountManager("data/users.txt");
		SM = new SavingAccountManager("data/users.txt");
		this.accountNumber = accountNumber;
		setSize(300, 300);
		setVisible(true);
		setTitle("Deposit");
		contentpane = new JPanel();
		setContentPane(contentpane);
		contentpane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		label = new JLabel("Type:");
		label.setBounds(40, 50, 70, 24);
		contentpane.add(label);

		String[] s = { "Checking", "Saving" };
		type = new JComboBox(s);
		type.setBounds(140, 50, 120, 24);
		contentpane.add(type);

		amount = new JLabel("Amount");
		amount.setBounds(40, 100, 70, 24);
		contentpane.add(amount);

		amount_ = new JTextField();
		amount_.setBounds(140, 100, 120, 24);
		contentpane.add(amount_);
		button = new JButton("OK");
		button.setBounds(40, 150, 80, 24);
		contentpane.add(button);

		cancel = new JButton("cancle");
		cancel.setBounds(140, 150, 80, 24);
		contentpane.add(cancel);
		cancel.addActionListener(this);
		button.addActionListener(this);

	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Deposit frame = new Deposit();

			}
		});
	}

}