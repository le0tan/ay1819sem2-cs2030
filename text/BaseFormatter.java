abstract class BaseFormatter implements TextFormatter, Comparable<TextFormatter> {
	@Override int compareTo(TextFormatter t) {
		return this.sumChars(this.format()) - this.sumChars(t.format());
	}

	private int sumChars(String s) {
		char[] arr = s.toCharArray();
		int sum = 0;
		for(char c: arr) {
			sum += (int) c;
		}
		return sum;
	}
}
