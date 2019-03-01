class Drink extends Food {
	public Drink(String s, int p) {
		super(s,p);
	}
	@Override
	public String toString() {
		return String.format("#%d Drink: %s (%d)", id, name, price);
	}
}
