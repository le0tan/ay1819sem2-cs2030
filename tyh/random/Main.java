public class Main {
	public static void main(String[] args) {
		Circle c1 = new Circle(new Point(0, 0), 10); 
    Circle c2 = new Circle(new Point(0, 0), 10); 
    Object o1 = c1;
		Object o2 = c2;
		o1.equals(o2);
		o1.equals((Circle)o2);
    System.out.println(((Circle)o2)instanceof Circle);
		o1.equals(c2);
		o1.equals(c1);
		c1.equals(o2);
		c1.equals((Circle)o2);
		c1.equals(c2);
		c1.equals(o1);
		c1.method();
	}
}
