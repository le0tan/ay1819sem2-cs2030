package cs2030.catsanddogs;

public class Chocolate extends Food {
	String flavor;
	public Chocolate(String s, String f) {
		super(s);
		this.flavor = f;
		System.out.printf("%s %s chocolate was added\n", brand, flavor);
	}
}
