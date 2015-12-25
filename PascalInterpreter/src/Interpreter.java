public class Interpreter {
	String input;
	Token currentToken;
	int position;

	public Interpreter(String input) {
		this.input = input.replaceAll("\\s+", "");
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
		if (currentChar == '-') {
			position++;
			return new Token("minus", currentChar + "");
		}
		if (currentChar == '*') {
			position++;
			return new Token("times", currentChar + "");
		}
		if (currentChar == '/') {
			position++;
			return new Token("divide", currentChar + "");
		}
		return null;
	}

	public boolean eat(String type) {
		if (this.currentToken.type.equals(type.toUpperCase())) {
			this.currentToken = getNextToken();
			return true;
		} else {
			return false;
		}
	}

	public void expr() {
		this.currentToken = getNextToken();
		String left = "";
		String right = "";
		boolean p = false;
		char operator = 0;
		while (!currentToken.type.equals("EOF".toUpperCase())) {
			Token t = currentToken;
			if (eat("integer")) {
				if (!p)
					left += t.value;
				else
					right += t.value;
			}
			if (eat("plus")) {
				operator = '+';
				p = true;
			}
			if (eat("minus")) {
				operator = '-';
				p = true;
			}
			if (eat("times")) {
				operator = '*';
				p = true;
			}
			if (eat("divide")) {
				operator = '/';
				p = true;
			}
		}
		System.out.println(performOperation(operator, Integer.parseInt(left), Integer.parseInt(right)));
	}

	public int performOperation(char operator, int op1, int op2) {
		switch (operator) {
		case '+':
			return op1 + op2;
		case '-':
			return op1 - op2;
		case '*':
			return op1 * op2;
		case '/':
			return op1 / op2;
		default:
			return Integer.MIN_VALUE;
		}
	}

	public static void main(String[] args) {
		Interpreter in = new Interpreter("10*1010000");
		in.expr();
	}
}
