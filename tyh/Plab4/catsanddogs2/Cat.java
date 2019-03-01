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
			isLazy = false;
		} else {
			isLazy = true;
			System.out.println("meow");
		}
	}
}
