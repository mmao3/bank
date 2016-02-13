package frames;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import custom_Jcomponent.*;
import util.*;
import bankAccount.*;
import person.*;

public class LogInFrame extends JFrame implements MouseListener, FocusListener {
	ImagePanel panel;
	final ImageButton register;
	final ImageButton login;
	final ImageButton remember;
	final ImageButton forgetpassword;
	final JPasswordField password;
	final JTextField email;
	boolean isRememberAccount;
	int rememberAccount = 0;
	boolean isAccountValid;
	AccountManager AM, AM1, AM2;
	ArrayList list;
	int timeTried = 0;
	final double locktime = 20;
	final double LOCKTIME = locktime * 1000;
	Person_Account current_user;

	public LogInFrame() {
		addNewUsers();
		addNewUsers1();
		panel = new ImagePanel(new ImageIcon("image/LogIn.png").getImage());
		register = new ImageButton(new ImageIcon("image/register.png"));
		login = new ImageButton(new ImageIcon("image/login_.png"));
		remember = new ImageButton(new ImageIcon("image/Uncheck.png"));
		forgetpassword = new ImageButton(new ImageIcon("image/forgetpassword.png"));
		password = new JPasswordField();
		password.setSize(265, 43);
		email = new JTextField();
		email.setSize(265, 43);
		add(register, 344, 358);
		add(login, 174, 348);
		add(remember, 175, 308);
		add(forgetpassword, 322, 311);
		add(password, 174, 248);
		add(email, 174, 188);
		login.addMouseListener(this);
		password.addMouseListener(this);
		remember.addMouseListener(this);
		forgetpassword.addMouseListener(this);
		register.addMouseListener(this);
		password.addFocusListener(this);
		getContentPane().add(panel);
		pack();
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Image img = new ImageIcon("image/icon_banking.png").getImage();
		setIconImage(img);
		AM = new AccountManager("data/RememberMe.txt");
		AM1 = new AccountManager("data/users.txt");
		AM2 = new AccountManager("data/current_user.txt");
		list = AM.load();
		email.setText(getRememberedUsername());
		password.setText(getRememberedPassword());

		System.out.println(password.getPassword().length);
		if (password.getPassword().length > 0) {
			password.requestFocus();
			password.setCaretPosition(password.getPassword().length);

		}

	}

	public void addRemember() {
		AM.insert(new RememberMe(email.getText(), new String(password.getPassword())));

	}

	public void removeRemember() {

		AM.clear();

	}

	public void focusGained(FocusEvent e) {
		if (e.getSource() == password) {
			password.setOpaque(true);
			repaint();
		}

	}

	public String getRememberedUsername() {

		if (AM.getLength() != 0) {
			remember.setIcon(new ImageIcon("image/checked.png"));
			rememberAccount = 1;
			isRememberAccount = true;
			password.setOpaque(true);
			repaint();
			return ((RememberMe) list.get(0)).getUsername();
		} else {
			remember.setIcon(new ImageIcon("image/uncheck.png"));
			rememberAccount = 0;
			isRememberAccount = false;
			password.setOpaque(false);
			repaint();
			return "";
		}
	}

	public String getRememberedPassword() {
		if (AM.getLength() != 0)
			return ((RememberMe) list.get(0)).getPassword();
		else
			return "";

	}

	public void focusLost(FocusEvent e) {

		if (e.getSource() == password) {
			if (new String(password.getPassword()).length() == 0) {
				password.setOpaque(false);
				repaint();
			}

		}

	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == remember) {
			rememberAccount = (++rememberAccount) % 2;
			if (rememberAccount == 1) {
				remember.setIcon(new ImageIcon("image/checked.png"));
				isRememberAccount = true;
				System.out.println(isRememberAccount);
			} else {
				remember.setIcon(new ImageIcon("image/uncheck.png"));
				isRememberAccount = false;
				removeRemember();
				System.out.println(isRememberAccount);
			}
		}

		else if (e.getSource() == register) {
			new SignUp();

		} else if (e.getSource() == login) {
			if (email.getText().trim().length() == 0 || new String(password.getPassword()).trim().length() == 0) {

				JOptionPane.showMessageDialog(login, "User ID or Password is blank!");
			} else {

				ArrayList list = AM1.load();
				ListIterator it = list.listIterator();
				Person_Account tem = null;
				while (it.hasNext()) {
					tem = (Person_Account) it.next();
					if (tem.getPdl().getEmail().equals(email.getText().trim())) {
						if (tem.getLock().isLocked() == true
								&& Calendar.getInstance().getTimeInMillis() - tem.getLock().getLockTime() < LOCKTIME) {

							JOptionPane.showMessageDialog(login, "Your Account was locked for " + locktime + " s."); // Already
																														// Locked

						} else if (tem.getLock().isLocked() == true
								&& Calendar.getInstance().getTimeInMillis() - tem.getLock().getLockTime() > LOCKTIME) {
							tem.getLock().removeLock(); // fished Lock
							timeTried = 0; // reset
							AM1.update(tem.getAccountNumber(), tem); // Update
																		// to
																		// the
																		// fil1
							if (tem.getPdl().getPassword().equals(new String(password.getPassword()).trim())) {
								current_user = tem;
								// add log in logiic if(is)
								logedIn();

							} else {
								timeTried++;
								JOptionPane.showMessageDialog(login, "Password is invalid!");

							}

						} else {

							if (tem.getPdl().getPassword().equals(new String(password.getPassword()).trim())) {
								current_user = tem;
								logedIn();

							} else {
								timeTried++;
								if (timeTried == 3) {
									tem.setLock(new Lock(true, Calendar.getInstance().getTimeInMillis()));
									AM1.update(tem.getAccountNumber(), tem);
									JOptionPane.showMessageDialog(login,
											"You exceeded the maximum allowed time. Your account will be locked for "
													+ locktime + " s.");

								} else {

									JOptionPane.showMessageDialog(login, "Password is invalid!");
								}

							}

						}

						break;
					}

				} // end of while
				if (tem != null && (!it.hasNext()) && !(tem.getPdl().getEmail().equals(email.getText().trim()))) {
					JOptionPane.showMessageDialog(login, "User Id does not exist!");
					System.out.println(!it.hasNext());
					System.out.println(tem.getPdl().getEmail());

				}
				if (AM1.getLength() == 0) {
					JOptionPane.showMessageDialog(login, "User Id does not exist!");

				}

			}

		} else if (e.getSource() == forgetpassword) {

			new forgetPassWord();

		} else {

		}

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == login)
			login.setIcon(new ImageIcon("image/login_new.png"));
		else if (e.getSource() == forgetpassword) {
			forgetpassword.setIcon(new ImageIcon("image/forgetpassword_new.png"));
		} else if (e.getSource() == register) {

			register.setIcon(new ImageIcon("image/register_new.png"));
		} else {

		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == login)
			login.setIcon(new ImageIcon("image/login_.png"));
		else if (e.getSource() == forgetpassword) {
			forgetpassword.setIcon(new ImageIcon("image/forgetpassword.png"));
		} else if (e.getSource() == register) {

			register.setIcon(new ImageIcon("image/register.png"));
		} else {

		}
	}

	public void mousePressed(MouseEvent e) {
		if (e.getSource() == login)
			login.setIcon(new ImageIcon("image/login_pressed.png"));
		else if (e.getSource() == forgetpassword) {
			forgetpassword.setIcon(new ImageIcon("image/forgetpassword_pressed.png"));
		} else if (e.getSource() == register) {

			register.setIcon(new ImageIcon("image/register_pressed.png"));
		} else {

		}

	}

	public void mouseReleased(MouseEvent e) {
		if (e.getSource() == login)
			login.setIcon(new ImageIcon("image/login_new.png"));
		else if (e.getSource() == forgetpassword) {
			forgetpassword.setIcon(new ImageIcon("image/forgetpassword_new.png"));
		} else if (e.getSource() == register) {

			register.setIcon(new ImageIcon("image/register_new.png"));
		} else {

		}

	}

	public void add(Component c, int x, int y) {
		c.setLocation(x, y);
		panel.add(c);

	}

	public void logedIn() {
		if (isRememberAccount == true) {
			removeRemember();
			addRemember();

		} else {
			removeRemember();

		}
		AM2.clear();
		AM2.insert(current_user);
		System.out.println(current_user.getPdl().getIdentity());
		if (current_user.getPdl().getIdentity() == 0)
			new AccountFrame();
		else
			new AdminFrame();

	}

	public void addNewUsers() {

		AccountManager f = new AccountManager("data/users.txt");
		Calendar c = Calendar.getInstance();
		c.set(1991, 3, 19);

		int accountNumber = 107383;
		Person n1 = new Person("MAO", "MAO", "");
		PersonDetails p1 = new PersonDetails(n1, "What is the first name of the person you first kissed?", "none",
				"124634022", "123456", c.getTime(), "mmao3@binghamton.edu", 1);

		if (!(f.isExists(accountNumber)))
			f.insert(new Person_Account(p1, new CheckingAccount(), new SavingsAccount(), accountNumber));

	}

	public void addNewUsers1() {

		AccountManager f = new AccountManager("data/users.txt");
		Calendar c = Calendar.getInstance();
		c.set(1990, 5, 1);

		int accountNumber = 112563;
		Person n1 = new Person("SAI", "VINEELA", "GANTI");
		PersonDetails p1 = new PersonDetails(n1, "What is the first name of the person you first kissed?", "none",
				"125605182", "123456", c.getTime(), "vineela.ganti@gmail.com", 1);

		if (!(f.isExists(accountNumber)))
			f.insert(new Person_Account(p1, new CheckingAccount(), new SavingsAccount(), accountNumber));

	}

	public static void main(String[] args) {
		new LogInFrame();

	}

}