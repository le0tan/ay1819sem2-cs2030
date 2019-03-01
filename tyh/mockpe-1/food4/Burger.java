class Burger extends Food {
	public Burger(String n, int p) {
		super(n,p);
	}
	@Override
	public String toString() {
		return "#"+id+" Burger: "+name+" ("+price+")";
	}
}
