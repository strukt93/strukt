package conversion;

import java.util.ArrayList;
import java.util.Stack;

public class Convert {
	String expression;

	public Convert(String exp) {
		this.expression = exp;
	}

	public void tokenize() {
		ArrayList<Token> tokens = new ArrayList<Token>();
		for (int i = 0; i < expression.length(); i++) {
			tokens.add(new Token(expression.charAt(i)));
		}
		toPostfix(tokens);
	}

	public void toPostfix(ArrayList<Token> tokens) {
		Stack<Token> operators = new Stack<Token>();
		String finish = "";
		int i = 0;
		while (i < tokens.size()) {
			Token t = tokens.get(i);
			if (t.type.equals("operand")) {
				finish += t.value;
				i++;
			}
			if (t.value == '(') {
				operators.push(t);
				i++;
				while (true) {
					t = tokens.get(i);
					if (t.value != ')') {
						operators.push(t);
					} else {
						while (operators.peek().value != '(') {
							finish += operators.pop().value;
						}
						operators.pop();
						break;
					}
					i++;
				}
				i++;
			}
			if (t.type.equals("operator")) {
				while (true) {
					if (!operators.isEmpty()) {
						if (operators.peek().getPriority() < t.getPriority())
							finish += operators.pop().value;
					}
					break;
				}
				operators.push(t);
				i++;
			}
		}
		while (!operators.isEmpty()) {
			finish += operators.pop().value;
		}
		System.out.println(finish);
	}

	public static void main(String[] args) {
		Convert c = new Convert("A*B+C*D");
		c.tokenize();
	}
}
