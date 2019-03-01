class Snack extends Food {
	public Snack(String s, int p) {
		super(s,p);
	}
	@Override
	public String toString() {
		return String.format("#%d Snack: %s (%d)", id, name, price);
	}
}
