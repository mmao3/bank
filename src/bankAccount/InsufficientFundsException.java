package bankAccount;

public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 1L;

	public InsufficientFundsException(String exc) {
		super(exc);
	}

	public String getMessage() {
		return super.getMessage();
	}

}
