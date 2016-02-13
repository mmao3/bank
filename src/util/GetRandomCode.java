package util;

public class GetRandomCode {
	public int getRandomNumber() {
		return (int) (Math.random() * 10);

	}

	public char getLowercase() {
		return (char) (Math.random() * 26 + 97);
	}

	public char getUpercase() {
		return (char) (Math.random() * 26 + 65);
	}

	public String getVerifyCode() {
		String out = "";
		for (int i = 0; i < 4; i++) {
			int tem = (int) (Math.random() * 3);
			switch (tem) {
			case (0):
				out += getRandomNumber();
				break;
			case (1):
				out += getLowercase();
				break;
			case (2):
				out += getUpercase();
				break;
			}
		}

		return out;
	}

	public static void main(String[] args) {
		System.out.println(new GetRandomCode().getVerifyCode());
	}

}
