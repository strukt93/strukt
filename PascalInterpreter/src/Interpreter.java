import java.lang.management.ThreadInfo;
import java.util.ArrayList;
import java.util.Stack;

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
		String num = "";
		ArrayList<String> elements = new ArrayList<String>();
		while (!currentToken.type.equals("EOF".toUpperCase())) {
			Token t = currentToken;
			if (eat("integer")) {
				num += t.value;
			}
			if (eat("plus")) {
				elements.add(num);
				elements.add("+");
				num = "";
			}
			if (eat("minus")) {
				elements.add(num);
				elements.add("-");
				num = "";
			}
			if (eat("times")) {
				elements.add(num);
				elements.add("*");
				num = "";
			}
			if (eat("divide")) {
				elements.add(num);
				elements.add("/");
				num = "";
			}
		}
		elements.add(num);
		// toPrefixNotation(elements);
	}

	public void performOperation(ArrayList<String> elements) {
		int value = Integer.parseInt(elements.get(0));
		String operator = elements.get(1);
		for (int i = 2; i < elements.size(); i++) {
			if (i % 2 == 0) {
				switch (operator) {
				case "+":
					value += Integer.parseInt(elements.get(i));
					break;
				case "-":
					value -= Integer.parseInt(elements.get(i));
					break;
				case "*":
					value *= Integer.parseInt(elements.get(i));
					break;
				case "/":
					value /= Integer.parseInt(elements.get(i));
					break;
				default:
					break;
				}
			} else {
				operator = elements.get(i);
			}
		}
	}

	public void toPrefixNotation(String input) {
		
	}

	public ArrayList<String> reverse(ArrayList<String> elements) {
		ArrayList<String> output = new ArrayList<String>();
		for (int i = elements.size() - 1; i >= 0; i--) {
			output.add(elements.get(i));
		}
		return output;
	}

	public String reverse(String input) {
		String output = "";
		for (int i = input.length() - 1; i >= 0; i--) {
			output += input.charAt(i);
		}
		return output;
	}

	public String replaceBrackets(String input) {
		String output = "";
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '(')
				output += ')';
			else if (input.charAt(i) == ')')
				output += '(';
			else
				output += input.charAt(i);
		}
		return output;
	}

	public static void main(String[] args) {
		Interpreter in = new Interpreter("7 * 4 / 2 * 3");
		in.toPrefixNotation("(A+B^C)*D+E^5");
	}
}
