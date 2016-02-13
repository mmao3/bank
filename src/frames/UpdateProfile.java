
package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import custom_Jcomponent.*;
import util.*;
import bankAccount.*;
import person.*;

public class UpdateProfile extends JFrame implements ActionListener {
	public JPanel contentpane;
	public JLabel JLFirstName, JLMiddleName, JLLastName, dateOfBirth, JLMonth, JLDay, JLYear, JLSeQ, JLAns, JLSSN,
			JLEmail, JLPassword, JLRePassword, JLVerify;
	public JTextField JTFFirstName, JTFMiddleName, JTFLastName, JTFAge, JTFYear, JTFSeQ, JTFAns, JTFSSN, JTFEmail,
			JTFVerify;
	public JButton sendEmail, submit, save;
	public JPasswordField JTFPassword, JTFRePassword;
	public JComboBox JCMonth, JCDay, Jsq;
	public SSNData ssndata;
	public int t = 20;
	public javax.swing.Timer timer;
	public JLabel yearInvalid, nameInvalid, answerLen, passWordLen, passWordDisMatch, EmailVerify;
	public String randomString;
	public String content;
	public boolean isFirstNValid = true, isMiddleNValid = true, isLastNValid = true, isYearValid = true,
			isAnswerValid = true, isSSNValid = true, isPassWordValid = true, isEmailValid = true;
	String[] m = { "Jan.", "Feb.", "Mar.", "Apr.", "May", "Jun.", "Jul.", "Aug.", "Sep.", "Oct.", "Nov.", "Dec." };
	String[] d = new String[31];
	int accountNumber;
	AccountManager AMup, AMup1;;
	Person_Account PA;

	public UpdateProfile() {
		setSize(600, 700);
		setVisible(true);

		setTitle("Update Profile");
		contentpane = new JPanel();
		setContentPane(contentpane);
		contentpane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		AMup = new AccountManager("data/current_user.txt");
		AMup1 = new AccountManager("data/users.txt");
		accountNumber = ((Person_Account) (AMup.returnFirst())).getAccountNumber();
		PA = (Person_Account) (AMup1.getObject(accountNumber));
		init();
		timer = new javax.swing.Timer(1000, this);
	}

	public void sendEmail() {
		randomString = new GetRandomCode().getVerifyCode();
		content = "Hello there:\n This is Email which verifies your account and the following is your verification code: "
				+ randomString + ". pLease enter the code to get updated! \n Thank you! \nMVM TEAM";
		System.out.println(randomString);
		timer.start();
		try {
			OurMail.sendMail(JTFEmail.getText().trim(), content);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(sendEmail, e.toString());
		}

	} //////////////////////////////////////////////////////////

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == sendEmail) {
			/// add send email logic

			AccountManager AM = new AccountManager("data/users.txt");
			ArrayList list = AM.load();
			ListIterator it = list.listIterator();
			Person_Account tem = null;
			while (it.hasNext()) {
				tem = (Person_Account) it.next();

				if ((tem.getPdl().getEmail().equals(JTFEmail.getText().trim()))
						&& (!tem.getPdl().getEmail().equals(PA.getPdl().getEmail()))) {

					isEmailValid = false;
					JOptionPane.showMessageDialog(sendEmail, "Email already being used!");
					break;
				}

			}
			if (AM.getLength() == 0
					|| (it.hasNext() == false && !(tem.getPdl().getEmail().equals(JTFEmail.getText().trim())))) {
				isEmailValid = true;
				sendEmail();

				sendEmail.setEnabled(false);
				JTFEmail.setEditable(false);
				JTFEmail.setEnabled(false);

			}

		} else if (ae.getSource() == submit) {

			JTFFirstName.setEditable(true);
			JTFFirstName.setEnabled(true);
			JTFMiddleName.setEditable(true);
			JTFMiddleName.setEnabled(true);
			JTFLastName.setEditable(true);
			JTFLastName.setEnabled(true);
			JTFYear.setEditable(true);
			JTFYear.setEnabled(true);
			JTFAns.setEditable(true);
			JTFAns.setEnabled(true);
			JTFPassword.setEditable(true);
			JTFPassword.setEnabled(true);
			JTFRePassword.setEditable(true);
			JTFRePassword.setEnabled(true);
			JTFEmail.setEditable(true);
			JTFEmail.setEnabled(true);
			JTFVerify.setEditable(true);
			JTFVerify.setEnabled(true);
			JCMonth.setEditable(true);
			JCMonth.setEnabled(true);
			JCDay.setEditable(true);
			JCDay.setEnabled(true);
			Jsq.setEditable(true);
			Jsq.setEnabled(true);
			ssndata.setEnabled(true);

		} else if (ae.getSource() == save) {
			//////////////////////////////////////////////////////////////

			AccountManager AM = new AccountManager("data/users.txt");
			ArrayList list = AM.load();
			ListIterator it = list.listIterator();
			Person_Account tem = null;
			while (it.hasNext()) {
				tem = (Person_Account) it.next();

				if ((tem.getPdl().getEmail().equals(JTFEmail.getText().trim()))
						&& (!tem.getPdl().getEmail().equals(PA.getPdl().getEmail()))) {

					isEmailValid = false;
					JOptionPane.showMessageDialog(sendEmail, "Email already being used!");
					return;
				}

			}
			System.out.println("isFirstNVali " + isFirstNValid);
			System.out.println("isMiddleNValid " + isMiddleNValid);
			System.out.println("isLastNValid " + isLastNValid);
			System.out.println("isYearValid" + isYearValid);
			System.out.println("isAnswerValid" + isAnswerValid);
			System.out.println("isPassWordValid" + isPassWordValid);
			System.out.println("isEmailValid" + isEmailValid);
			System.out.println(ssndata.getLength());
			if (isFirstNValid && isMiddleNValid && isLastNValid && isYearValid && isAnswerValid && isPassWordValid
					&& isEmailValid && ssndata.getLength() == 9) {
				if (JTFEmail.getText().trim().equals(PA.getPdl().getEmail())) {
					AccountManager f = new AccountManager("data/users.txt");
					Calendar c = Calendar.getInstance();
					c.set(Integer.parseInt(JTFYear.getText()), JCMonth.getSelectedIndex(),
							JCDay.getSelectedIndex() + 1);
					System.out.println(Integer.parseInt(JTFYear.getText()));
					System.out.println(JCMonth.getSelectedIndex());
					System.out.println(JCDay.getSelectedIndex());

					Person n1 = new Person(JTFFirstName.getText(), JTFLastName.getText(), JTFMiddleName.getText());
					PersonDetails p1 = new PersonDetails(n1, (String) (Jsq.getSelectedItem()), JTFAns.getText(),
							ssndata.getText(), new String(JTFPassword.getPassword()), c.getTime(), JTFEmail.getText());
					Person_Account up = new Person_Account(p1, new CheckingAccount(), new SavingsAccount(),
							accountNumber);
					f.update(accountNumber, up);
					System.out.println("updated!");

					content = "Hello there:\n This email confirms you have successfully updated your profile! \n Thank you! \n MVM TEAM";

					System.out.println(content);
					try {
						OurMail.sendMail(JTFEmail.getText().trim(), content);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(sendEmail, e.toString());
					}

					JOptionPane.showMessageDialog(submit,
							"You have successfully updated your profile! A confirmation email has been sent to you.");
				}

				else if (JTFVerify.getText().trim().equals(randomString)) {
					AccountManager f = new AccountManager("data/users.txt");
					Calendar c = Calendar.getInstance();
					c.set(Integer.parseInt(JTFYear.getText()), JCMonth.getSelectedIndex(),
							JCDay.getSelectedIndex() + 1);
					System.out.println(Integer.parseInt(JTFYear.getText()));
					System.out.println(JCMonth.getSelectedIndex());
					System.out.println(JCDay.getSelectedIndex());

					Person n1 = new Person(JTFFirstName.getText(), JTFLastName.getText(), JTFMiddleName.getText());
					PersonDetails p1 = new PersonDetails(n1, (String) (Jsq.getSelectedItem()), JTFAns.getText(),
							ssndata.getText(), new String(JTFPassword.getPassword()), c.getTime(), JTFEmail.getText());
					Person_Account up = new Person_Account(p1, new CheckingAccount(), new SavingsAccount(),
							accountNumber);
					f.update(accountNumber, up);
					System.out.println("updated!");

					content = "Hello there:\n This email confirms you have successfully updated your profile! \n Thank you! \n MVM TEAM";

					System.out.println(content);
					try {
						OurMail.sendMail(JTFEmail.getText().trim(), content);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(sendEmail, e.toString());
					}

					JOptionPane.showMessageDialog(submit,
							"You have successfully updated your profile! A confirmation email has been sent to you.");
				}

				else {

					JOptionPane.showMessageDialog(submit, "Verification code is not correct!");
				}

			} else {
				JOptionPane.showMessageDialog(submit, "Not all the required fields are valid!");

			}

		}

		/// end of if

		else {
			// EmailVerify.setText("Resend after "+t+" seconds");

			EmailVerify.setText("Resend after " + t + " second");

			EmailVerify.setVisible(true);
			t--;
			if (t < 0) {
				timer.stop();
				EmailVerify.setVisible(false);
				sendEmail.setEnabled(true);
				JTFEmail.setEditable(true);
				JTFEmail.setEnabled(true);
				t = 20;
			}
		}
	}

	public void init() {
		JLFirstName = new JLabel("First Name:");
		JLFirstName.setBounds(40, 40, 80, 24);
		contentpane.add(JLFirstName);

		JLMiddleName = new JLabel("Middle Name:");
		JLMiddleName.setBounds(140, 40, 120, 24);
		contentpane.add(JLMiddleName);

		JLLastName = new JLabel("Last Name:");
		JLLastName.setBounds(240, 40, 80, 24);
		contentpane.add(JLLastName);
		nameInvalid = new JLabel("Invalid Name!");
		nameInvalid.setForeground(Color.RED);
		nameInvalid.setBounds(370, 70, 120, 24);
		nameInvalid.setVisible(false);
		contentpane.add(nameInvalid);

		JTFFirstName = new JTextField();
		JTFFirstName.setBounds(40, 70, 100, 24);
		contentpane.add(JTFFirstName);
		JTFFirstName.addKeyListener(new KeyAdapter() {

			/*
			 * public void keyTyped(KeyEvent e) {
			 * 
			 * String tem=""; for(int
			 * i=0;i<JTFFirstName.getText().trim().length();i++){
			 * 
			 * if((JTFFirstName.getText().trim().charAt(i)>='a'&&JTFFirstName.
			 * getText().trim().charAt(i)<='z')) tem=tem+(char
			 * )(JTFFirstName.getText().trim().charAt(i)-32); else
			 * tem=tem+JTFFirstName.getText().trim().charAt(i);
			 * 
			 * 
			 * } JTFFirstName.setText(tem); }
			 */

			public void keyReleased(KeyEvent e) {
				System.out.println(e.getKeyCode());
				char tem = e.getKeyChar();
				String tem1 = "";

				if (tem >= 'a' && tem <= 'z') {
					tem = (char) (tem - 32);

				} else if (tem >= 'A' && tem <= 'Z' || tem == '\b' || e.getKeyChar() == ' '
						|| (e.getKeyCode() >= 16 && e.getKeyCode() <= 20)) {

					nameInvalid.setVisible(false);
				} else {

					nameInvalid.setVisible(true);
				}
				for (int i = 0; i < JTFFirstName.getText().trim().length(); i++) {

					if ((JTFFirstName.getText().trim().charAt(i) >= 'a'
							&& JTFFirstName.getText().trim().charAt(i) <= 'z'))
						tem1 = tem1 + (char) (JTFFirstName.getText().trim().charAt(i) - 32);
					else
						tem1 = tem1 + JTFFirstName.getText().trim().charAt(i);

				}
				JTFFirstName.setText(tem1);
				for (int i = 0; i < JTFFirstName.getText().trim().length(); i++) {
					if ((JTFFirstName.getText().trim().charAt(i) >= 'A'
							&& JTFFirstName.getText().trim().charAt(i) <= 'Z')) {
						nameInvalid.setVisible(false);

					}

					else {
						nameInvalid.setVisible(true);
						break;

					}

				}
			}

		});

		JTFFirstName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				nameInvalid.setText("Invalid First Name");
				JTFFirstName.setBackground(Color.WHITE);
				validate();
			}

			public void focusLost(FocusEvent e) {
				nameInvalid.setVisible(false);
				if (JTFFirstName.getText().trim().length() > 0) {
					int i;
					for (i = 0; i < JTFFirstName.getText().trim().length(); i++) {
						if (!(JTFFirstName.getText().trim().charAt(i) >= 'A'
								&& JTFFirstName.getText().trim().charAt(i) <= 'Z')) {
							isFirstNValid = false;
							JTFFirstName.setBackground(Color.RED);
							break;
						}

					}
					if (i == JTFFirstName.getText().trim().length()) {
						isFirstNValid = true;
						JTFFirstName.setBackground(Color.WHITE);

					}
				} else {
					isFirstNValid = false;
					JTFFirstName.setBackground(Color.RED);
				}
				validate();

			}
		});

		JTFMiddleName = new JTextField();
		JTFMiddleName.setBounds(140, 70, 100, 24);
		contentpane.add(JTFMiddleName);

		JTFMiddleName.addKeyListener(new KeyAdapter() {

			public void keyReleased(KeyEvent e) {
				char tem = e.getKeyChar();
				String tem1 = "";
				if (tem >= 'a' && tem <= 'z') {
					tem = (char) (tem - 32);

				} else if (tem >= 'A' && tem <= 'Z' || tem == '\b' || e.getKeyChar() == ' '
						|| (e.getKeyCode() >= 16 && e.getKeyCode() <= 20)) {

					nameInvalid.setVisible(false);
				} else {

					nameInvalid.setVisible(true);
				}

				for (int i = 0; i < JTFMiddleName.getText().trim().length(); i++) {

					if ((JTFMiddleName.getText().trim().charAt(i) >= 'a'
							&& JTFMiddleName.getText().trim().charAt(i) <= 'z'))
						tem1 = tem1 + (char) (JTFMiddleName.getText().trim().charAt(i) - 32);
					else
						tem1 = tem1 + JTFMiddleName.getText().trim().charAt(i);

				}
				JTFMiddleName.setText(tem1);
				if (tem >= 'A' && tem <= 'Z')
					JTFMiddleName.setText(
							JTFMiddleName.getText().trim().substring(0, JTFMiddleName.getText().trim().length() - 1)
									+ tem);
				for (int i = 0; i < JTFMiddleName.getText().trim().length(); i++) {
					if ((JTFMiddleName.getText().trim().charAt(i) >= 'A'
							&& JTFMiddleName.getText().trim().charAt(i) <= 'Z')) {
						nameInvalid.setVisible(false);

					}

					else {
						nameInvalid.setVisible(true);
						break;

					}

				}
			}

		});

		JTFMiddleName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				nameInvalid.setText("Invalid Middle Name");
				JTFMiddleName.setBackground(Color.WHITE);
				validate();
			}

			public void focusLost(FocusEvent e) {
				nameInvalid.setVisible(false);
				if (JTFMiddleName.getText().length() > 0) {
					int i;
					for (i = 0; i < JTFMiddleName.getText().trim().length(); i++) {
						if (!(JTFMiddleName.getText().trim().charAt(i) >= 'A'
								&& JTFMiddleName.getText().trim().charAt(i) <= 'Z')) {
							isMiddleNValid = false;
							JTFMiddleName.setBackground(Color.RED);
							break;
						}

					}
					if (i == JTFMiddleName.getText().trim().length()) {
						isMiddleNValid = true;
						JTFMiddleName.setBackground(Color.WHITE);
					}

				} else {

					isMiddleNValid = true;
					JTFMiddleName.setBackground(Color.WHITE);
				}

				validate();

			}
		});

		JTFLastName = new JTextField();
		JTFLastName.setBounds(240, 70, 100, 24);
		contentpane.add(JTFLastName);

		JTFLastName.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

				char tem = e.getKeyChar();
				if (tem != '\b' || e.getKeyChar() != ' ' || !(e.getKeyCode() >= 16 && e.getKeyCode() <= 20)) {
					for (int i = 0; i < JTFLastName.getText().trim().length(); i++) {

						if ((JTFLastName.getText().trim().charAt(i) >= 'a'
								&& JTFLastName.getText().trim().charAt(i) <= 'z'))
							JTFLastName.setText(JTFLastName.getText().trim().substring(0, i)
									+ (char) (JTFLastName.getText().trim().charAt(i) - 32));
						else {

							break;
						}
					}

				}
			}

			public void keyReleased(KeyEvent e) {
				char tem = e.getKeyChar();
				String tem1 = "";
				if (tem >= 'a' && tem <= 'z') {
					tem = (char) (tem - 32);

				} else if (tem >= 'A' && tem <= 'Z' || tem == '\b' || e.getKeyChar() == ' '
						|| (e.getKeyCode() >= 16 && e.getKeyCode() <= 20)) {

					nameInvalid.setVisible(false);
				} else {

					nameInvalid.setVisible(true);
				}

				for (int i = 0; i < JTFLastName.getText().trim().length(); i++) {

					if ((JTFLastName.getText().trim().charAt(i) >= 'a'
							&& JTFLastName.getText().trim().charAt(i) <= 'z'))
						tem1 = tem1 + (char) (JTFLastName.getText().trim().charAt(i) - 32);
					else
						tem1 = tem1 + JTFLastName.getText().trim().charAt(i);

				}
				JTFLastName.setText(tem1);
				if (tem >= 'A' && tem <= 'Z')
					JTFLastName.setText(
							JTFLastName.getText().trim().substring(0, JTFLastName.getText().trim().length() - 1) + tem);
				for (int i = 0; i < JTFLastName.getText().trim().length(); i++) {
					if ((JTFLastName.getText().trim().charAt(i) >= 'A'
							&& JTFLastName.getText().trim().charAt(i) <= 'Z')) {
						nameInvalid.setVisible(false);

					} else {
						nameInvalid.setVisible(true);
						break;

					}
				}

			}

		});

		JTFLastName.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				nameInvalid.setText("Invalid Last Name");
				JTFLastName.setBackground(Color.WHITE);
				validate();
			}

			public void focusLost(FocusEvent e) {
				nameInvalid.setVisible(false);
				if (JTFLastName.getText().trim().length() > 0) {
					int i;
					for (i = 0; i < JTFLastName.getText().trim().length(); i++) {
						if (!(JTFLastName.getText().trim().charAt(i) >= 'A'
								&& JTFLastName.getText().trim().charAt(i) <= 'Z')) {
							isLastNValid = false;
							JTFLastName.setBackground(Color.RED);
							break;
						}

					}
					if (i == JTFLastName.getText().trim().length()) {
						isLastNValid = true;
						JTFLastName.setBackground(Color.WHITE);
					}
				} else {
					isLastNValid = false;
					JTFLastName.setBackground(Color.RED);
				}
				validate();

			}
		});
		dateOfBirth = new JLabel("Date of Birth: ");
		dateOfBirth.setBounds(40, 100, 100, 24);
		contentpane.add(dateOfBirth);

		JLMonth = new JLabel("Month");
		JLMonth.setBounds(40, 130, 40, 24);
		contentpane.add(JLMonth);

		JCMonth = new JComboBox(m);
		JCMonth.setBounds(80, 130, 80, 24);
		contentpane.add(JCMonth);

		JLDay = new JLabel("Day");
		JLDay.setBounds(180, 130, 30, 24);
		contentpane.add(JLDay);
		for (int i = 0; i < d.length; i++) {

			d[i] = i + 1 + "";
		}
		JCDay = new JComboBox(d);
		JCDay.setBounds(210, 130, 80, 24);
		contentpane.add(JCDay);

		JLYear = new JLabel("Year");
		JLYear.setBounds(310, 130, 30, 24);

		contentpane.add(JLYear);
		yearInvalid = new JLabel("Invalid year!");
		yearInvalid.setForeground(Color.RED);
		yearInvalid.setBounds(440, 130, 80, 24);
		yearInvalid.setVisible(false);
		contentpane.add(yearInvalid);

		JTFYear = new JTextField();
		JTFYear.setBounds(340, 130, 80, 24);
		JTFYear.setDocument(new MyDocument(4));
		contentpane.add(JTFYear);

		JTFYear.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {
				yearInvalid.setVisible(false);
				if (e.getKeyChar() != '\b') {
					if (JTFYear.getText().trim().length() > 0) {
						try {

							int y = Integer.parseInt(JTFYear.getText().trim());

						} catch (Exception ie) {
							yearInvalid.setVisible(true);

						}
					}
				}
			}

			public void keyReleased(KeyEvent e) {
				if (JTFYear.getText().trim().length() == 0) {
					yearInvalid.setVisible(false);
					isYearValid = false;
				} else if (JTFYear.getText().trim().length() < 4) {
					if (!((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyChar() == '\b'))
						yearInvalid.setVisible(true);
					isYearValid = false;
				} else {

					try {

						int y = Integer.parseInt(JTFYear.getText().trim());
						if (y >= 1900 && y <= 2015) {
							isYearValid = true;
							yearInvalid.setVisible(false);
						} else {
							yearInvalid.setVisible(true);
							isYearValid = false;
						}

					} catch (Exception ie) {
						yearInvalid.setVisible(true);

					}

				}

			}
		});
		JTFYear.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				JTFYear.setBackground(Color.WHITE);
				validate();
			}

			public void focusLost(FocusEvent e) {
				yearInvalid.setVisible(false);
				if (JTFYear.getText().trim().length() != 4 && JTFYear.getText().trim().length() > 0) {
					JTFYear.setBackground(Color.RED);

					isYearValid = false;

				} else if (JTFYear.getText().trim().length() == 0) {

					yearInvalid.setVisible(true);
					isYearValid = false;

				} else if (JTFYear.getText().trim().length() == 4) {
					try {

						int y = Integer.parseInt(JTFYear.getText().trim());
						if (y >= 1900 && y <= 2015) {
							isYearValid = true;
							yearInvalid.setVisible(false);
						} else {
							JTFYear.setBackground(Color.RED);
							isYearValid = false;
						}

					} catch (Exception ie) {
						JTFYear.setBackground(Color.RED);
						isYearValid = false;

					}
				}

				else {

				}

			}

		}

		);

		JLSeQ = new JLabel("Security Question:");
		JLSeQ.setBounds(40, 160, 140, 24);
		contentpane.add(JLSeQ);
		String[] sq = { "What is the first name of the person you first kissed?",
				"What was the name of your elementary / primary school?", "What is your pet's name",
				"In what year was your father born?", "What is your mother's last name",
				"What is your favourite sports?", "In what city or town does your nearest sibling live?",
				"What was your favourite place to visit as a child?" };
		Jsq = new JComboBox(sq);
		Jsq.setBounds(40, 190, 350, 24);
		contentpane.add(Jsq);

		JLAns = new JLabel("Answer: ");
		JLAns.setBounds(40, 220, 100, 24);
		contentpane.add(JLAns);

		answerLen = new JLabel("The minimum length is 6");
		answerLen.setBounds(380, 250, 300, 24);
		answerLen.setForeground(Color.RED);
		answerLen.setVisible(false);
		contentpane.add(answerLen);

		JTFAns = new JTextField();
		JTFAns.setBounds(40, 250, 300, 24);
		contentpane.add(JTFAns);

		JTFAns.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				answerLen.setVisible(true);
				JTFAns.setBackground(Color.WHITE);
				validate();
			}

			public void focusLost(FocusEvent e) {
				answerLen.setVisible(false);
				if (JTFAns.getText().trim().length() < 6 && JTFAns.getText().trim().length() >= 0) {
					JTFAns.setBackground(Color.RED);
					isAnswerValid = false;

				} else {
					JTFAns.setBackground(Color.WHITE);
					isAnswerValid = true;
				}
				validate();

			}
		});

		JLSSN = new JLabel("SSN: ");
		JLSSN.setBounds(40, 280, 60, 24);
		contentpane.add(JLSSN);

		ssndata = new SSNData();
		ssndata.setBounds(25, 310, 200, 24);
		contentpane.add(ssndata);

		JLPassword = new JLabel("Password: ");
		JLPassword.setBounds(40, 340, 80, 24);
		contentpane.add(JLPassword);

		JTFPassword = new JPasswordField();
		JTFPassword.setBounds(40, 370, 200, 24);
		contentpane.add(JTFPassword);

		passWordLen = new JLabel("The minimum password length is 6");
		passWordLen.setBounds(250, 370, 200, 24);
		passWordLen.setForeground(Color.RED);
		passWordLen.setVisible(false);

		contentpane.add(passWordLen);

		JTFPassword.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				passWordLen.setVisible(true);
				JTFPassword.setBackground(Color.WHITE);
				validate();
				repaint();
			}

			public void focusLost(FocusEvent e) {
				passWordLen.setVisible(false);

				if (JTFPassword.getPassword().length == 0) {
					isPassWordValid = false;
					if (JTFPassword.getPassword().length < 6 && JTFPassword.getPassword().length >= 0) {
						passWordDisMatch.setVisible(false);
						JTFPassword.setBackground(Color.RED);

					} else {
						JTFPassword.setBackground(Color.WHITE);
					}
				} else {
					isPassWordValid = false;
					if (JTFPassword.getPassword().length < 6 && JTFPassword.getPassword().length >= 0) {
						passWordDisMatch.setVisible(true);
						JTFPassword.setBackground(Color.RED);
						JTFRePassword.setBackground(Color.RED);

					} else {
						if (!(new String(JTFPassword.getPassword()).equals(new String(JTFRePassword.getPassword())))) {

							passWordDisMatch.setVisible(true);
							JTFRePassword.setBackground(Color.RED);

						} else {
							isPassWordValid = true;
							passWordDisMatch.setVisible(false);
							JTFRePassword.setBackground(Color.WHITE);
						}
					}

				}
				validate();
				repaint();

			}
		});

		JLRePassword = new JLabel("Re-enter password: ");
		JLRePassword.setBounds(40, 400, 150, 24);
		contentpane.add(JLRePassword);
		// answerLen,passWordLen,passWordDisMatch,EmailVerify;
		JTFRePassword = new JPasswordField();
		JTFRePassword.setBounds(40, 430, 200, 24);
		contentpane.add(JTFRePassword);

		passWordDisMatch = new JLabel("Passwords mismatch !");
		passWordDisMatch.setBounds(250, 430, 200, 24);
		passWordDisMatch.setForeground(Color.RED);
		passWordDisMatch.setVisible(false);
		contentpane.add(passWordDisMatch);
		JTFRePassword.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				passWordDisMatch.setVisible(false);
				JTFRePassword.setBackground(Color.WHITE);

				validate();
				repaint();
			}

			public void focusLost(FocusEvent e) {
				if (JTFPassword.getPassword().length >= 6) {
					if (!(new String(JTFPassword.getPassword()).equals(new String(JTFRePassword.getPassword())))) {

						passWordDisMatch.setVisible(true);
						JTFRePassword.setBackground(Color.RED);
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

		JLEmail = new JLabel("Email: ");
		JLEmail.setBounds(40, 460, 50, 24);
		contentpane.add(JLEmail);

		JTFEmail = new JTextField();
		JTFEmail.setBounds(40, 490, 200, 24);
		contentpane.add(JTFEmail);

		sendEmail = new JButton("Verify");
		sendEmail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sendEmail.setBounds(250, 490, 80, 24);
		sendEmail.setEnabled(false);
		contentpane.add(sendEmail);
		sendEmail.addActionListener(this);

		// answerLen,passWordLen,passWordDisMatch,EmailVerify;
		EmailVerify = new JLabel("Resend after 10 seconds");
		EmailVerify.setBounds(350, 490, 200, 24);
		EmailVerify.setVisible(false);
		contentpane.add(EmailVerify);

		JTFEmail.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {

				if (JTFEmail.getText().trim().indexOf('@') > -1) {

					sendEmail.setEnabled(true);
				} else {

					sendEmail.setEnabled(false);
				}

			}

		});

		JTFEmail.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {

				EmailVerify.setVisible(false);
				JTFEmail.setBackground(Color.WHITE);

			}

			public void focusLost(FocusEvent e) {
				if (JTFEmail.getText().trim().length() > 0) {
					if (JTFEmail.getText().trim().indexOf('@') > -1) {

						sendEmail.setEnabled(true);
						isEmailValid = true;
					} else {

						JTFEmail.setBackground(Color.RED);
						EmailVerify.setText("Invalid Email!");
						EmailVerify.setForeground(Color.RED);
						EmailVerify.setVisible(true);
						isEmailValid = false;

					}

				}

				validate();
				repaint();
			}
		});

		JLVerify = new JLabel("Verify code: ");
		JLVerify.setBounds(40, 520, 80, 24);
		contentpane.add(JLVerify);

		JTFVerify = new JTextField();
		JTFVerify.setBounds(40, 550, 100, 24);
		contentpane.add(JTFVerify);
		submit = new JButton("Update");
		submit.setBounds(150, 600, 80, 24);
		submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		submit.addActionListener(this);
		contentpane.add(submit);
		save = new JButton("Save");
		save.setBounds(350, 600, 80, 24);
		contentpane.add(save);
		save.addActionListener(this);
		// JTFFirstName.setEditable(false);
		JTFFirstName.setEnabled(false);
		JTFFirstName.setText(PA.getPdl().getPerson().getFirst_Name());
		// JTFMiddleName.setEditable(false);
		JTFMiddleName.setEnabled(false);
		JTFMiddleName.setText(PA.getPdl().getPerson().getMiddle_Name());
		// JTFLastName.setEditable(false);
		JTFLastName.setEnabled(false);
		JTFLastName.setText(PA.getPdl().getPerson().getLast_Name());
		// JTFYear.setEditable(false);
		JTFYear.setEnabled(false);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(PA.getPdl().getDateOfBirth());
		JTFYear.setText(calendar.get(Calendar.YEAR) + "");
		// JTFAns.setEditable(false);
		JTFAns.setEnabled(false);
		JTFAns.setText(PA.getPdl().getSecAns());
		// JTFPassword.setEditable(false);
		JTFPassword.setEnabled(false);
		JTFPassword.setText(PA.getPdl().getPassword());
		// JTFRePassword.setEditable(false);
		JTFRePassword.setEnabled(false);
		JTFRePassword.setText(PA.getPdl().getPassword());
		// JTFEmail.setEditable(false);
		JTFEmail.setEnabled(false);
		// JTFVerify.setEditable(false);
		JTFEmail.setText(PA.getPdl().getEmail());
		JTFVerify.setEnabled(false);
		JCMonth.setSelectedIndex(calendar.get(Calendar.MONTH));
		JCMonth.setEnabled(false);
		JCDay.setSelectedIndex(calendar.get(Calendar.DATE) - 1);
		JCDay.setEnabled(false);
		// Jsq.setEditable(false);
		Jsq.setEnabled(false);
		Jsq.setSelectedItem(PA.getPdl().getSecurityQ());
		ssndata.setEnabled(false);
		ssndata.setText(PA.getPdl().getSocialsecurityNumber());
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				UpdateProfile frame = new UpdateProfile();

			}
		});
	}

}