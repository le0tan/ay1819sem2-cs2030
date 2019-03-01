package cs2030.catsanddogs;

public class Dog extends Animal {
	int woofTimes;
	String woof;
	public Dog(String name, int appetite, String woof) {
		super(name, appetite);
		this.woof = woof;
		woofTimes=0;
		System.out.printf("%s was created\n", name);
	}
	@Override
	public void sayHello() {
		woofTimes++;
		System.out.print(this.toString()+" says ");
		for(int i=0;i<woofTimes;i++) {
			System.out.print(woof);
		}
		System.out.println();
	}
	@Override
	public String toString() {
		return String.format("%s", name);
	}
	public boolean eat(Food f) {
		if(appetite<=0 || f instanceof Chocolate) return false;
		appetite--;
		System.out.println(this.toString()+" eats "+f.toString());
		return true;
	}
}
