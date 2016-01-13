package conversion;

public class Token {
	char value;
	String type;
	int priority;

	public Token(char value) {
		this.value = value;
		if (Character.isAlphabetic(value)) {
			this.type = "operand";
		} else {
			if (value == ')' || value == '(')
				this.type = "bracket";
			else
				this.type = "operator";
		}
		setPriority();
	}

	public void setPriority() {
		if (this.type.equals("operand"))
			this.priority = 0;
		else {
			switch (this.value) {
			case '*':
				this.priority = 100;
				break;
			case '/':
				this.priority = 100;
				break;
			case '+':
				this.priority = 50;
				break;
			case '-':
				this.priority = 50;
				break;
			case ')':
				this.priority = 1;
			case '(':
				this.priority = 1;
				break;
			default:
				break;
			}
		}
	}

	public int getPriority() {
		return this.priority;
	}

	public String getType() {
		return this.type;
	}

	public char getValue() {
		return this.value;
	}

	public String toString() {
		return "Token is an " + getType() + ", and it's value is " + getValue() + ", and it's priority is "
				+ getPriority();
	}

	public static void main(String[] args) {
		Token t = new Token(')');
		System.out.println(t.toString());
	}
}
