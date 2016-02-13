package person;

import java.io.Serializable;
import java.util.Date;

public class PersonDetails implements Serializable {

	private Person person;
	private Date dateOfBirth;
	private String securityQ;
	private String SecAns;
	private String socialsecurityNumber;
	private String password;
	private String email;
	private int identity; // 0 ,1 2;

	public PersonDetails(Person person, String que, String ans, String ssn, String pass, Date dob, String email) {
		this.setPerson(person);
		this.dateOfBirth = dob;
		this.password = pass;
		this.SecAns = ans;
		this.securityQ = que;
		this.socialsecurityNumber = ssn;
		this.email = email;
		this.identity = 0;
	}

	public PersonDetails(Person person, String que, String ans, String ssn, String pass, Date dob, String email,
			int identity) {
		this.setPerson(person);
		this.dateOfBirth = dob;
		this.password = pass;
		this.SecAns = ans;
		this.securityQ = que;
		this.socialsecurityNumber = ssn;
		this.email = email;
		this.identity = identity;
	}

	public void setIdentity(int identity) {
		this.identity = identity;

	}

	public int getIdentity() {

		return identity;

	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getSecurityQ() {
		return securityQ;
	}

	public void setSecurityQ(String securityQ) {
		this.securityQ = securityQ;
	}

	public String getSecAns() {
		return SecAns;
	}

	public void setSecAns(String secAns) {
		SecAns = secAns;
	}

	public String getSocialsecurityNumber() {
		return socialsecurityNumber;
	}

	public void setSocialsecurityNumber(String socialsecurityNumber) {
		this.socialsecurityNumber = socialsecurityNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "PersonDetails [dateOfBirth=" + dateOfBirth + ", securityQ=" + securityQ + ", SecAns=" + SecAns
				+ ", socialsecurityNumber=" + socialsecurityNumber + ", password=" + password + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}