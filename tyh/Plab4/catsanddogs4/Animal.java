package cs2030.catsanddogs;

public abstract class Animal implements Comparable<Animal> {
	String name;
	int appetite;
	public Animal(String name, int appetite) {
		this.name = name;
		this.appetite = appetite;
	}
	abstract public void sayHello();
	abstract public String toString();
	@Override
	public int compareTo(Animal a) {
		return name.compareTo(a.name);
	}
	abstract public boolean eat(Food f);
}
