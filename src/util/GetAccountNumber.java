package util;

public class GetAccountNumber {
	static int getNum() {
		return (int) (Math.random() * 10);

	}

	static int getNum_() {
		return (int) (Math.random() * 8 + 1);
	}

	public static int getAccountNumber_() {
		String out = getNum_() + "";
		for (int i = 1; i < 6; i++) {
			out += getNum();
		}

		return Integer.parseInt(out);
	}

	public static int getAccountNumber() {
		int out = getAccountNumber_();
		AccountManager AM = new AccountManager("data/users.txt");
		while (AM.isExists(out)) {

			out = getAccountNumber_();
		}
		return out;

	}

	public static void main(String[] args) {
		System.out.println(GetAccountNumber.getAccountNumber());
	}

}
