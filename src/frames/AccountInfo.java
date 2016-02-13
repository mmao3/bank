
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

public class AccountInfo extends JFrame {
	CheckAccountManager CM;
	SavingAccountManager SM;
	int accountNumber;
	AccountManager AM1;
	AccountManager AM;
	JPanel contentpane;
	JLabel accoun, name, email, ssn, dob, checking, saving;
	JTextField accoun_, name_, email_, ssn_, dob_, checking_, saving_;

	public AccountInfo() {
		AM = new AccountManager("data/current_user.txt");
		CM = new CheckAccountManager("data/users.txt");
		SM = new SavingAccountManager("data/users.txt");
		AM1 = new AccountManager("data/users.txt");
		accountNumber = ((Person_Account) (AM.returnFirst())).getAccountNumber();
		Person_Account PA = (Person_Account) (AM1.getObject(accountNumber));
		System.out.print(PA);

		contentpane = new JPanel();
		contentpane.setLayout(null);
		setContentPane(contentpane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		setVisible(true);
		setSize(400, 300);
		accoun = new JLabel("Account Number");

		accoun_ = new JTextField();
		accoun_.setText(accountNumber + "");
		name = new JLabel("Name");

		name_ = new JTextField();
		name_.setText(PA.getPdl().getPerson().getFirst_Name() + " " + PA.getPdl().getPerson().getMiddle_Name() + " "
				+ PA.getPdl().getPerson().getLast_Name());
		email = new JLabel("Email");
		email_ = new JTextField(PA.getPdl().getEmail());
		email_.setText(PA.getPdl().getEmail());
		ssn = new JLabel("SSN");
		ssn_ = new JTextField(PA.getPdl().getSocialsecurityNumber() + "");
		dob = new JLabel("Date of birth");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(PA.getPdl().getDateOfBirth());
		dob_ = new JTextField(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DATE));
		checking = new JLabel("Checking Balance");
		checking_ = new JTextField(CM.getBalance(accountNumber) + "");
		saving = new JLabel("Saving Balance");
		saving_ = new JTextField(SM.getBalance(accountNumber) + "");
		accoun_.setEditable(false);

		name_.setEditable(false);
		email_.setEditable(false);
		ssn_.setEditable(false);
		dob_.setEditable(false);
		checking_.setEditable(false);
		saving_.setEditable(false);
		accoun.setBounds(40, 40, 100, 24);
		accoun_.setBounds(160, 40, 80, 24);
		name.setBounds(40, 70, 100, 24);
		name_.setBounds(160, 70, 150, 24);
		email.setBounds(40, 100, 80, 24);
		email_.setBounds(160, 100, 150, 24);
		ssn.setBounds(40, 130, 80, 24);
		ssn_.setBounds(160, 130, 80, 24);
		dob.setBounds(40, 160, 80, 24);
		dob_.setBounds(160, 160, 80, 24);
		checking.setBounds(40, 190, 150, 24);
		checking_.setBounds(160, 190, 80, 24);
		saving.setBounds(40, 220, 150, 24);
		saving_.setBounds(160, 220, 80, 24);
		contentpane.add(accoun);
		contentpane.add(accoun_);
		contentpane.add(name);
		contentpane.add(name_);
		contentpane.add(email);
		contentpane.add(email_);
		contentpane.add(ssn_);
		contentpane.add(ssn);
		contentpane.add(dob);
		contentpane.add(dob_);
		contentpane.add(checking);
		contentpane.add(checking_);
		contentpane.add(saving);
		contentpane.add(saving_);
		repaint();

	}

	public AccountInfo(int accountNumber) {
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		AM = new AccountManager("data/current_user.txt");
		CM = new CheckAccountManager("data/users.txt");
		SM = new SavingAccountManager("data/users.txt");
		AM1 = new AccountManager("data/users.txt");
		this.accountNumber = accountNumber;
		Person_Account PA = (Person_Account) (AM1.getObject(accountNumber));
		System.out.print(PA);

		contentpane = new JPanel();
		contentpane.setLayout(null);
		setContentPane(contentpane);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(400, 300);
		accoun = new JLabel("Account Number");

		accoun_ = new JTextField();
		accoun_.setText(accountNumber + "");
		name = new JLabel("Name");

		name_ = new JTextField();
		name_.setText(PA.getPdl().getPerson().getFirst_Name() + " " + PA.getPdl().getPerson().getMiddle_Name() + " "
				+ PA.getPdl().getPerson().getLast_Name());
		email = new JLabel("Email");
		email_ = new JTextField(PA.getPdl().getEmail());
		email_.setText(PA.getPdl().getEmail());
		ssn = new JLabel("SSN");
		ssn_ = new JTextField(PA.getPdl().getSocialsecurityNumber() + "");
		dob = new JLabel("Date of birth");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(PA.getPdl().getDateOfBirth());
		dob_ = new JTextField(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DATE));
		checking = new JLabel("Checking Balance");
		checking_ = new JTextField(CM.getBalance(accountNumber) + "");
		saving = new JLabel("Saving Balance");
		saving_ = new JTextField(SM.getBalance(accountNumber) + "");
		accoun_.setEditable(false);

		name_.setEditable(false);
		email_.setEditable(false);
		ssn_.setEditable(false);
		dob_.setEditable(false);
		checking_.setEditable(false);
		saving_.setEditable(false);
		accoun.setBounds(40, 40, 100, 24);
		accoun_.setBounds(160, 40, 80, 24);
		name.setBounds(40, 70, 100, 24);
		name_.setBounds(160, 70, 150, 24);
		email.setBounds(40, 100, 80, 24);
		email_.setBounds(160, 100, 150, 24);
		ssn.setBounds(40, 130, 80, 24);
		ssn_.setBounds(160, 130, 80, 24);
		dob.setBounds(40, 160, 80, 24);
		dob_.setBounds(160, 160, 80, 24);
		checking.setBounds(40, 190, 150, 24);
		checking_.setBounds(160, 190, 80, 24);
		saving.setBounds(40, 220, 150, 24);
		saving_.setBounds(160, 220, 80, 24);
		contentpane.add(accoun);
		contentpane.add(accoun_);
		contentpane.add(name);
		contentpane.add(name_);
		contentpane.add(email);
		contentpane.add(email_);
		contentpane.add(ssn_);
		contentpane.add(ssn);
		contentpane.add(dob);
		contentpane.add(dob_);
		contentpane.add(checking);
		contentpane.add(checking_);
		contentpane.add(saving);
		contentpane.add(saving_);
		repaint();

	}

	public static void main(String[] args) {
		new AccountInfo();
	}

}