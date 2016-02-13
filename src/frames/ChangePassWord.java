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

public class ChangePassWord extends JFrame implements ActionListener {
	JPanel contentpane;
	JLabel newPassword, reNewPassword, passWordDisMatch, passWordLen;
	JPasswordField newPassword_, reNewPassword_;
	boolean isPassWordValid;
	JButton button;
	int accountNumber;
	AccountManager AM, AM1;
	Person_Account PA;

	public void actionPerformed(ActionEvent e) {
		if (isPassWordValid) {
			PA.getPdl().setPassword(new String(newPassword_.getPassword()));
			AM1.update(accountNumber, PA);
			JOptionPane.showMessageDialog(button, "You successfully changed your password!");
		} else {

			JOptionPane.showMessageDialog(button, "Your new password is invalid!");
		}

	}

	public ChangePassWord() {
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		AM = new AccountManager("data/current_user.txt");
		AM1 = new AccountManager("data/users.txt");
		accountNumber = ((Person_Account) (AM.returnFirst())).getAccountNumber();
		PA = (Person_Account) (AM1.getObject(accountNumber));
		contentpane = new JPanel();
		contentpane.setLayout(null);
		setContentPane(contentpane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(450, 250);

		newPassword = new JLabel("New Password");

		newPassword.setBounds(40, 50, 100, 24);
		contentpane.add(newPassword);

		newPassword_ = new JPasswordField();

		newPassword_.setBounds(160, 50, 100, 24);
		contentpane.add(newPassword_);

		passWordLen = new JLabel("Min_len is 6");
		passWordLen.setBounds(270, 50, 100, 24);
		passWordLen.setForeground(Color.RED);
		passWordLen.setVisible(false);
		contentpane.add(passWordLen);

		passWordDisMatch = new JLabel("Passwords mismatch!");
		passWordDisMatch.setBounds(270, 80, 150, 24);
		passWordDisMatch.setForeground(Color.RED);
		passWordDisMatch.setVisible(false);
		contentpane.add(passWordDisMatch);
		button = new JButton("Submit");
		button.setBounds(160, 150, 100, 24);
		button.addActionListener(this);
		contentpane.add(button);
		// newPassword_,reNewPassword_;
		newPassword_.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				passWordLen.setVisible(true);
				newPassword_.setBackground(Color.WHITE);
				validate();
				repaint();
			}

			public void focusLost(FocusEvent e) {
				passWordLen.setVisible(false);

				if (newPassword_.getPassword().length == 0) {
					isPassWordValid = false;
					if (newPassword_.getPassword().length < 6 && newPassword_.getPassword().length >= 0) {
						passWordDisMatch.setVisible(false);
						newPassword_.setBackground(Color.RED);

					} else {
						newPassword_.setBackground(Color.WHITE);
					}
				} else {
					isPassWordValid = false;
					if (newPassword_.getPassword().length < 6 && newPassword_.getPassword().length >= 0) {
						passWordDisMatch.setVisible(true);
						newPassword_.setBackground(Color.RED);
						reNewPassword_.setBackground(Color.RED);

					} else {
						if (!(new String(newPassword_.getPassword())
								.equals(new String(reNewPassword_.getPassword())))) {

							passWordDisMatch.setVisible(true);
							reNewPassword_.setBackground(Color.RED);

						} else {
							isPassWordValid = true;
							passWordDisMatch.setVisible(false);
							reNewPassword_.setBackground(Color.WHITE);
						}
					}

				}
				validate();
				repaint();

			}
		});

		reNewPassword = new JLabel("Confirm Password");

		reNewPassword.setBounds(40, 80, 120, 24);
		contentpane.add(reNewPassword);

		reNewPassword_ = new JPasswordField();

		reNewPassword_.setBounds(160, 80, 100, 24);
		contentpane.add(reNewPassword_);//// newPassword_,reNewPassword_;
		reNewPassword_.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				passWordDisMatch.setVisible(false);
				reNewPassword_.setBackground(Color.WHITE);

				validate();
				repaint();
			}

			public void focusLost(FocusEvent e) {
				if (newPassword_.getPassword().length >= 6) {
					if (!(new String(newPassword_.getPassword()).equals(new String(reNewPassword_.getPassword())))) {

						passWordDisMatch.setVisible(true);
						reNewPassword_.setBackground(Color.RED);
						isPassWordValid = false;
					} else
						isPassWordValid = true;

				} else {
					isPassWordValid = false;

				}
				validate();
				repaint();

			}
		});

	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ChangePassWord frame = new ChangePassWord();

			}
		});
	}

}