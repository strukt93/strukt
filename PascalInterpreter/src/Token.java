
public class Token {
	String type;// INTEGER, PLUS , or EOF
	String value;// 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, +, or NONE

	public Token(String type, String value) {
		this.type = type.toUpperCase();
		this.value = value;
	}

	public String toString() {
		return "Token(" + this.type + ", " + this.value + ")";
	}

	public String getType() {
		return this.type;
	}

	public String getValue() {
		return this.value;
	}

	public static void main(String[] args) {
		Token t = new Token("PLUS", "+");
		System.out.println(t.toString());
	}
}
