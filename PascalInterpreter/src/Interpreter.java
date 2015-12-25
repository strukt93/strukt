public class Interpreter {
	String input;
	Token currentToken;
	int position;

	public Interpreter(String input) {
		this.input = input;
		this.position = 0;
	}

	public Token getNextToken() {
		if (position == input.length()) {
			return new Token("eof", "NONE");
		}
		char currentChar = input.charAt(position);
		if (Character.isDigit(currentChar)) {
			position++;
			return new Token("integer", currentChar + "");
		}
		if (currentChar == '+') {
			position++;
			return new Token("plus", currentChar + "");
		}
		return null;
	}

	public void eat(String type) {
		if (this.currentToken.type.equals(type.toUpperCase())) {
			this.currentToken = getNextToken();
		} else {
			System.out.println("Parseing Error: Current Token Type is " + currentToken.type
					+ ", while given argument is " + type.toUpperCase());
		}
	}

	public void expr() {
		this.currentToken = getNextToken();
		Token left = currentToken;
		eat("integer");
		Token plus = currentToken;
		eat("plus");
		Token right = currentToken;
		eat("integer");
		Token eof = currentToken;
		eat("eof");
		System.out.println(Integer.parseInt(left.value) + Integer.parseInt(right.value));
	}

	public static void main(String[] args) {
		Interpreter in = new Interpreter("5+9");
		in.expr();
	}
}
