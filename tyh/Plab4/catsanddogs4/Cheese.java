package cs2030.catsanddogs;

public class Cheese extends Food {
	public Cheese(String s) {
		super(s);
		System.out.printf("%s cheese was added\n", brand);
	}
	@Override
	public String toString() {
		return brand+" cheese";
	}
}
