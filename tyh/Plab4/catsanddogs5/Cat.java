package cs2030.catsanddogs;

public class Cat extends Animal {
	String color;
	boolean isLazy;
	public Cat(String name, int appetite, String color) {
		super(name, appetite);
		this.color = color;
		isLazy = false;
		System.out.printf("%s(%s) was created\n", name, color);
	}
	@Override
	public void sayHello() {
		if(isLazy) {
			System.out.println(this.toString()+" is lazy");
			isLazy = false;
		} else {
			isLazy = true;
			System.out.println(this.toString()+" says meow and gets lazy");
		}
	}
	@Override
	public String toString() {
		return String.format("%s(%s)", name, color);
	}
	public boolean eat(Food f) {
		if(appetite<=0 || f instanceof Cheese) return false;
		appetite--;
		System.out.println(this.toString()+" eats "+f.toString());
		return true;
	}
}
