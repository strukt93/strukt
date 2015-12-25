package Exceptions;

@SuppressWarnings("serial")
public class ParseException extends Exception {
	public ParseException() {
		super();
	}

	public ParseException(String error) {
		super(error);
	}

	public ParseException(String error, Throwable reason) {
		super(error, reason);
	}

	public ParseException(Throwable reason) {
		super(reason);
	}
}
