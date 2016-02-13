package person;

import java.io.Serializable;

public class Person implements Serializable {
	private String First_Name;
	private String Last_Name;
	private String Middle_Name;

	public Person(String fname, String lname, String mname) {
		this.First_Name = fname;
		this.Last_Name = lname;
		this.Middle_Name = mname;
	}

	@Override
	public String toString() {
		return "Person [First_Name=" + First_Name + ", Last_Name=" + Last_Name + ", Middle_Name=" + Middle_Name + "]";
	}

	public String getFirst_Name() {
		return First_Name;
	}

	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}

	public String getLast_Name() {
		return Last_Name;
	}

	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}

	public String getMiddle_Name() {
		return Middle_Name;
	}

	public void setMiddle_Name(String middle_Name) {
		Middle_Name = middle_Name;
	}
}