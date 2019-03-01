package cs2030.catsanddogs;

public abstract class Animal {
	String name;
	int appetite;
	public Animal(String name, int appetite) {
		this.name = name;
		this.appetite = appetite;
	}
	abstract public void sayHello();
}
