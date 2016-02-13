package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import util.*;
import bankAccount.*;
import java.util.*;
import person.*;

class forgetPassWord extends JFrame implements ActionListener {
	public JPanel contentpane;
	public JLabel l1, email, verifycode, resend, newPassword, reNewPassword, SecurityQuestion, id, passWordDisMatch,
			passWordLen;
	public JTextField email_, verifycode_, id_;
	public JPasswordField newPassword_, reNewPassword_;
	public int t = 20;
	public javax.swing.Timer timer;
	public JComboBox SecurityQuestion_;
	public JButton send, verify, submit, go;
	public ButtonGroup group;
	public JRadioButton JRbtn1, JRbtn2;
	// String [] sq={"What is the first name of the person you first
	// kissed?","What was the name of your elementary / primary school?","What
	// is your pet's name","In what year was your father born?","What is your
	// mother's last name","What is your favourite sports?","In what city or
	// town does your nearest sibling live?","What was your favourite place to
	// visit as a child?"};
	boolean isPassWordValid, isEmailValid, isVerifyCodeValid, isAnswerValid, isIdValid;
	String securityQuestion, answer;
	Person_Account tem;
	int accountNumber;
	public String randomString;
	String content = "Hello there:\n This email confirms you that you have successfully changed your password.\n  Thank you! \n MVM TEAM";

	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == send) {
			AccountManager AM = new AccountManager("data/users.txt");
			ArrayList list = AM.load();
			ListIterator it = list.listIterator();

			while (it.hasNext()) {
				tem = (Person_Account) it.next();
				if (tem.getPdl().getEmail().equals(email_.getText().trim())) {
					isEmailValid = true;
					securityQuestion = tem.getPdl().getSecurityQ();
					answer = tem.getPdl().getSecAns();
					accountNumber = tem.getAccountNumber();
					break;
				}
			}

			if (isEmailValid) {
				randomString = new GetRandomCode().getVerifyCode();
				content = "Hello there:\n This is Email which verifies your account and the following is your verification code: "
						+ randomString + ". pLease enter the code to change your password! \n Thank you! \nMVM TEAM";
				try {
					OurMail.sendMail(email_.getText().trim(), content);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(send, e.toString());
				}
				timer.start();
				send.setEnabled(false);
				email_.setEditable(false);
				email_.setEnabled(false);
			}

			else {
				JOptionPane.showMessageDialog(send, "Your email is not valid.");

			}
		} else {
			if (t > 1)
				resend.setText("Resend after " + t + " s");
			else
				resend.setText("Resend after " + t + " s");

			resend.setVisible(true);
			t--;
			if (t < 0) {
				timer.stop();
				resend.setVisible(false);
				send.setEnabled(true);
				email_.setEditable(true);
				email_.setEnabled(true);
				t = 20;
			}
		}
	}

	public forgetPassWord() {
		timer = new javax.swing.Timer(1000, this);
		setSize(400, 400);
		setVisible(true);
		contentpane = new JPanel();
		setContentPane(contentpane);
		contentpane.setLayout(null);
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		l1 = new JLabel("Use");
		// l1.setFont(new Font("Serif",Font.BOLD,18));
		l1.setBounds(60, 130, 30, 24);
		contentpane.add(l1);
		group = new ButtonGroup();
		JRbtn1 = new JRadioButton("Email");
		JRbtn1.setBounds(100, 130, 70, 24);
		group.add(JRbtn1);
		contentpane.add(JRbtn1);
		JRbtn2 = new JRadioButton("Security Question");
		JRbtn2.setBounds(170, 130, 160, 24);
		group.add(JRbtn2);
		contentpane.add(JRbtn2);
		id = new JLabel("User ID");
		id.setBounds(60, 160, 80, 24);
		contentpane.add(id);

		id_ = new JTextField();
		id_.setBounds(120, 160, 100, 24);
		contentpane.add(id_);

		go = new JButton("Go");
		go.setBounds(250, 160, 40, 24);
		go.setPreferredSize(new Dimension(40, 24));

		id.setVisible(false);
		id_.setVisible(false);
		go.setVisible(false);

		// send.setBorderPainted(false);

		go.setBorder(BorderFactory.createRaisedBevelBorder());
		go.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentpane.add(go);

		go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AccountManager AM = new AccountManager("data/users.txt");
				ArrayList list = AM.load();
				ListIterator it = list.listIterator();

				while (it.hasNext()) {
					tem = (Person_Account) it.next();
					if (tem.getPdl().getEmail().equals(id_.getText().trim())) {
						isIdValid = true;
						securityQuestion = tem.getPdl().getSecurityQ();
						answer = tem.getPdl().getSecAns();
						accountNumber = tem.getAccountNumber();
						break;
					}
				}

				if (isIdValid) {
					id.setVisible(false);
					id_.setVisible(false);
					go.setVisible(false);
					l1.setBounds(60, 130 - 60, 30, 24);
					JRbtn1.setBounds(100, 130 - 60, 70, 24);
					JRbtn2.setBounds(170, 130 - 60, 160, 24);
					email.setText(securityQuestion);
					email.setBounds(10, 180 - 60, 350, 24);
					email.setVisible(true);
					email_.setBounds(140, 180 - 60, 100, 24);
					email_.setVisible(false);

					verifycode.setText("Answer");

					verifycode.setBounds(10, 210 - 60, 70, 24);
					verifycode.setVisible(true);
					verifycode_.setBounds(140, 210 - 60, 100, 24);
					verifycode_.setVisible(true);
					verifycode_.setText("");

					newPassword.setBounds(10, 240 - 60, 100, 24);
					newPassword.setVisible(true);
					newPassword_.setVisible(true);
					newPassword_.setBounds(140, 240 - 60, 100, 24);
					newPassword_.setText("");

					reNewPassword.setBounds(10, 270 - 60, 120, 24);
					reNewPassword.setVisible(true);
					reNewPassword_.setBounds(140, 270 - 60, 100, 24);
					reNewPassword_.setVisible(true);
					reNewPassword_.setText("");
					submit.setBounds(140, 320 - 60, 100, 24);
					submit.setVisible(true);
					newPassword_.setBackground(Color.WHITE);
					reNewPassword_.setBackground(Color.WHITE);
					passWordLen.setVisible(false);
					passWordDisMatch.setVisible(false);
					validate();

				} else {
					JOptionPane.showMessageDialog(go, "User Id does not exist!");

				}
			}

		});

		JRbtn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JRbtn1.isSelected()) {
					id.setVisible(false);
					id_.setVisible(false);
					go.setVisible(false);
					l1.setBounds(60, 130 - 60, 30, 24);
					JRbtn1.setBounds(100, 130 - 60, 70, 24);
					JRbtn2.setBounds(170, 130 - 60, 160, 24);
					email.setText("Email");
					email.setBounds(10, 180 - 60, 120, 24);
					email.setVisible(true);
					email_.setBounds(140, 180 - 60, 100, 24);
					email_.setVisible(true);
					email_.setEditable(true);
					email_.setEnabled(true);
					email_.setText("");
					verifycode.setBounds(10, 210 - 60, 70, 24);
					verifycode.setText("Verify code");
					verifycode.setVisible(true);
					verifycode_.setBounds(140, 210 - 60, 100, 24);
					verifycode_.setVisible(true);
					verifycode_.setText("");

					newPassword.setBounds(10, 240 - 60, 100, 24);
					newPassword.setVisible(true);
					newPassword_.setVisible(true);
					newPassword_.setBounds(140, 240 - 60, 100, 24);
					newPassword_.setText("");
					reNewPassword.setBounds(10, 270 - 60, 120, 24);
					reNewPassword.setVisible(true);
					reNewPassword_.setBounds(140, 270 - 60, 100, 24);
					reNewPassword_.setVisible(true);
					reNewPassword_.setText("");
					newPassword_.setBackground(Color.WHITE);
					reNewPassword_.setBackground(Color.WHITE);
					submit.setBounds(140, 320 - 60, 100, 24);
					submit.setVisible(true);
					passWordLen.setVisible(false);
					passWordDisMatch.setVisible(false);
					validate();
				}

			}

		});

		JRbtn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JRbtn2.isSelected()) {
					send.setVisible(false);
					timer.stop();
					resend.setVisible(false);

					l1.setBounds(60, 130, 30, 24);
					JRbtn1.setBounds(100, 130, 70, 24);
					JRbtn2.setBounds(170, 130, 160, 24);
					passWordLen.setVisible(false);
					passWordDisMatch.setVisible(false);
					email.setBounds(10, 180 - 60, 120, 24);
					email.setVisible(false);
					email_.setBounds(140, 180 - 60, 100, 24);
					email_.setVisible(false);
					verifycode.setBounds(10, 210 - 60, 70, 24);
					verifycode.setVisible(false);
					verifycode_.setBounds(140, 210 - 60, 100, 24);
					verifycode_.setVisible(false);

					newPassword.setBounds(10, 240 - 60, 100, 24);
					newPassword.setVisible(false);
					newPassword_.setVisible(false);
					newPassword_.setBounds(140, 240 - 60, 100, 24);

					reNewPassword.setBounds(10, 270 - 60, 120, 24);
					reNewPassword.setVisible(false);
					reNewPassword_.setBounds(140, 270 - 60, 100, 24);
					reNewPassword_.setVisible(false);

					submit.setBounds(140, 320 - 60, 100, 24);
					submit.setVisible(false);
					id.setVisible(true);
					id_.setVisible(true);
					go.setVisible(true);
					validate();
				}

			}

		});

		// email=new JLabel("What is the first name of the person you first
		// kissed?");
		email = new JLabel("Email");
		email.setVisible(false);
		// email.setBounds(10, 180, 70, 24);
		email.setBounds(10, 180 - 60, 120, 24);
		// email.setBounds(25, 180,350, 24);
		contentpane.add(email);

		email_ = new JTextField();
		email_.setBounds(140, 180 - 60, 100, 24);
		email_.setVisible(false);
		contentpane.add(email_);
		email_.addKeyListener(new KeyAdapter() {

			public void keyTyped(KeyEvent e) {

			}

			public void keyReleased(KeyEvent e) {

				if (email_.getText().trim().indexOf('@') > -1) {

					send.setEnabled(true);
				} else {

					send.setEnabled(false);
				}

			}

		});

		email_.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				send.setVisible(true);

				email_.setBackground(Color.WHITE);

				validate();
			}

			public void focusLost(FocusEvent e) {
				if (email_.getText().trim().length() > 0) {
					if (email_.getText().trim().indexOf('@') > -1) {

						send.setEnabled(true);
					} else {

						email_.setBackground(Color.RED);

					}

				}

				validate();

			}
		});
		send = new JButton("Send");
		send.setVisible(false);
		send.setEnabled(false);
		send.setBounds(280, 180 - 60, 45, 24);
		// send.setContentAreaFilled(false);
		send.setPreferredSize(new Dimension(45, 24));
		// send.setBorderPainted(false);

		send.setBorder(BorderFactory.createRaisedBevelBorder());
		send.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentpane.add(send);

		send.addActionListener(this);

		verifycode = new JLabel("Verify code");
		verifycode.setVisible(false);
		verifycode.setBounds(10, 210 - 60, 70, 24);
		contentpane.add(verifycode);

		verifycode_ = new JTextField();
		verifycode_.setVisible(false);
		verifycode_.setBounds(140, 210 - 60, 100, 24);
		contentpane.add(verifycode_);

		resend = new JLabel("Resent after 20 s");
		resend.setVisible(false);
		resend.setBounds(250, 210 - 60, 150, 24);
		contentpane.add(resend);

		newPassword = new JLabel("New Password");
		newPassword.setVisible(false);
		newPassword.setBounds(10, 240 - 60, 100, 24);
		contentpane.add(newPassword);

		newPassword_ = new JPasswordField();
		newPassword_.setVisible(false);
		newPassword_.setBounds(140, 240 - 60, 100, 24);
		contentpane.add(newPassword_);

		passWordLen = new JLabel("Min_len is 6");
		passWordLen.setBounds(250, 240 - 60, 100, 24);
		passWordLen.setForeground(Color.RED);
		passWordLen.setVisible(false);
		contentpane.add(passWordLen);

		passWordDisMatch = new JLabel("Passwords mismatch !");
		passWordDisMatch.setBounds(250, 240 - 60 + 30, 200, 24);
		passWordDisMatch.setForeground(Color.RED);
		passWordDisMatch.setVisible(false);
		contentpane.add(passWordDisMatch); // newPassword_,reNewPassword_;
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
		reNewPassword.setVisible(false);
		reNewPassword.setBounds(10, 270 - 60, 120, 24);
		contentpane.add(reNewPassword);

		reNewPassword_ = new JPasswordField();
		reNewPassword_.setVisible(false);
		reNewPassword_.setBounds(140, 270 - 60, 100, 24);
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
		submit = new JButton("Submit");
		submit.setBounds(140, 320 - 60, 100, 24);
		submit.setVisible(false);
		submit.setBorderPainted(false);
		submit.setBorder(BorderFactory.createRaisedBevelBorder());
		submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentpane.add(submit);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JRbtn2.isSelected()) {
					if (answer.equals(verifycode_.getText())) {
						if (isPassWordValid) {
							AccountManager AM = new AccountManager("data/users.txt");

							tem.getPdl().setPassword(new String(newPassword_.getPassword()));
							AM.update(accountNumber, tem);

							try {
								content = "Hello there:\n This email confirms you that you have successfully changed your password.\n  Thank you! \n MVM TEAM";
								OurMail.sendMail(tem.getPdl().getEmail(), content);
							} catch (Exception E) {
								JOptionPane.showMessageDialog(submit, e.toString());
							}

							JOptionPane.showMessageDialog(submit,
									"You have successfully changed your password. An email has been sent to you");

						} else {
							JOptionPane.showMessageDialog(submit, "Your new password is not valid!");

						}

					} else {

						JOptionPane.showMessageDialog(submit, "Your answer is not correct!");

					}

				}

				else {

					if (verifycode_.getText().trim().equals(randomString)) {
						if (isPassWordValid) {
							AccountManager AM = new AccountManager("data/users.txt");

							tem.getPdl().setPassword(new String(newPassword_.getPassword()));
							AM.update(accountNumber, tem);

							try {
								content = "Hello there:\n This email confirms you that you have successfully changed your password.\n  Thank you! \n MVM TEAM";
								OurMail.sendMail(tem.getPdl().getEmail(), content);
							} catch (Exception E) {
								JOptionPane.showMessageDialog(submit, e.toString());
							}

							JOptionPane.showMessageDialog(submit,
									"You have successfully changed your password. An email has been sent to you");

						} else {
							JOptionPane.showMessageDialog(submit, "Your new password is not valid!");

						}

					} else {

						JOptionPane.showMessageDialog(submit, "Your verify code is not valid!");

					}

				}

			}

		});

		/*
		 * JLabel jName,jQuestion, jAnswer; JTextField jTName, jTQuestion,
		 * jTAnswer; JPanel contentpane; JButton btn; ButtonGroup group;
		 * JRadioButton JRbtn;
		 * 
		 * setSize(400,400); setVisible(true); contentpane = new JPanel();
		 * setContentPane(contentpane); contentpane.setLayout(null);
		 * 
		 * 
		 * jName = new JLabel("User Name: "); jName.setBounds(10, 40, 100, 24);
		 * contentpane.add(jName);
		 * 
		 * jTName = new JTextField(); jTName.setBounds(140, 40, 100, 24);
		 * contentpane.add(jTName);
		 * 
		 * jQuestion = new JLabel("Security Question: ");
		 * jQuestion.setBounds(10, 65, 130, 24); jTQuestion = new JTextField();
		 * jTQuestion.setBounds(140, 65, 100, 24); contentpane.add(jQuestion);
		 * contentpane.add(jTQuestion);
		 * 
		 * jAnswer = new JLabel("Answer: "); jAnswer.setBounds(10, 90, 100, 24);
		 * jTAnswer = new JTextField(); jTAnswer.setBounds(140, 90, 100, 24);
		 * contentpane.add(jAnswer); contentpane.add(jTAnswer); btn = new
		 * JButton("Submit");
		 * btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		 * btn.setBounds(140, 125, 80, 24); contentpane.add(btn); group=new
		 * ButtonGroup(); JRbtn=new JRadioButton("Email"); JRbtn.setBounds(10,
		 * 155, 80, 24); group.add(JRbtn); contentpane.add(JRbtn); JRbtn=new
		 * JRadioButton("Security Question"); JRbtn.setBounds(120, 155, 160,
		 * 24); group.add(JRbtn); contentpane.add(JRbtn);
		 */

	}

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				forgetPassWord frame = new forgetPassWord();

			}
		});
	}

}