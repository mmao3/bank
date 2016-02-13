package history;

import java.io.Serializable;
import java.util.Date;

public class LoginHistory implements Serializable {

	int accountNumber;
	Date lastlogin;
	Date logout;
	int duration;

	public LoginHistory(Date lastlogin, Date logout, int accountNumber, int duration) {
		this.lastlogin = lastlogin;
		this.accountNumber = accountNumber;
		this.duration = duration;
		this.logout = logout;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDuration() {
		return duration;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getLogout() {
		return logout;
	}

	public void setLogout(Date logout) {
		this.logout = logout;
	}

	public Date getLastlogin() {
		return lastlogin;
	}

	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}

}
