package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import custom_Jcomponent.*;
import util.*;
import bankAccount.*;
import person.*;

public class Transfer extends JFrame implements ActionListener {
	JPanel contentpane;
	JLabel label, amount, ts;
	JTextField amount_, ds;
	JButton button, cancel;
	JComboBox type;
	CheckAccountManager CM;
	SavingAccountManager SM;
	AccountManager AM;
	int accountNumber;

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {

			dispose();
		}
		if (e.getSource() == button) {

			if (amount_.getText().length() == 0 || ds.getText().length() == 0) {

				JOptionPane.showMessageDialog(button, "Amount or account number cannot be empty!");
			} else {

				if (type.getSelectedIndex() == 0) {
					try {
						int i = CM.transfer(accountNumber, Integer.parseInt(ds.getText().trim()),
								Double.parseDouble(amount_.getText().trim()));
						if (i == 1)
							JOptionPane.showMessageDialog(button, "Your transaction is successful");
						else {

							JOptionPane.showMessageDialog(button, "Account number does not exist ");
						}
					} catch (IllegalArgumentException E) {
						JOptionPane.showMessageDialog(button, "Amount is illegal!");

					} catch (InsufficientFundsException E) {
						JOptionPane.showMessageDialog(button, "Insufficient Funds!");

					}

				} else {
					try {
						int i = SM.transfer(accountNumber, Integer.parseInt(ds.getText().trim()),
								Double.parseDouble(amount_.getText().trim()));
						if (i == 1)
							JOptionPane.showMessageDialog(button, "Your transaction is successful");
						else {

							JOptionPane.showMessageDialog(button, "Account number does not exist ");
						}
					} catch (IllegalArgumentException E) {
						JOptionPane.showMessageDialog(button, "Amount is illegal!");

					} catch (InsufficientFundsException E) {
						JOptionPane.showMessageDialog(button, "Insufficient Funds!");

					}

				}

			}

		}

	}

	public Transfer() {
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		AM = new AccountManager("data/current_user.txt");
		CM = new CheckAccountManager("data/users.txt");
		SM = new SavingAccountManager("data/users.txt");
		accountNumber = ((Person_Account) (AM.returnFirst())).getAccountNumber();
		setSize(300, 300);
		setVisible(true);
		setTitle("Transfer");
		contentpane = new JPanel();
		setContentPane(contentpane);
		contentpane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		label = new JLabel("Type:");
		label.setBounds(40, 30, 70, 24);
		contentpane.add(label);

		String[] s = { "Checking", "Saving" };
		type = new JComboBox(s);
		type.setBounds(140, 30, 120, 24);
		contentpane.add(type);

		ts = new JLabel("Transfer to");
		ts.setBounds(40, 80, 90, 24);
		contentpane.add(ts);

		ds = new JTextField();
		ds.setBounds(140, 80, 120, 24);
		contentpane.add(ds);

		amount = new JLabel("Amount");
		amount.setBounds(40, 130, 70, 24);
		contentpane.add(amount);

		amount_ = new JTextField();
		amount_.setBounds(140, 130, 120, 24);
		contentpane.add(amount_);
		button = new JButton("OK");
		button.setBounds(40, 180, 80, 24);
		contentpane.add(button);

		cancel = new JButton("cancle");
		cancel.setBounds(140, 180, 80, 24);
		contentpane.add(cancel);
		cancel.addActionListener(this);
		button.addActionListener(this);
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Transfer frame = new Transfer();

			}
		});
	}

}