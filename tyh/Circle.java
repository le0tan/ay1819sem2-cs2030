public class Circle {
	Point centre;
    double radius;
	public Circle(Point centre, double radius) { 
		this.centre = centre;
		this.radius = radius;
	}
	@Override
	public boolean equals(Object obj) {
	System.out.println("equals(Object) called"); 
	if (obj == this) {
		return true;
	}
		if (obj instanceof Circle) {
			Circle circle = (Circle) obj;
			return (circle.centre.equals(centre) && circle.radius == radius);
		} else {
			return false;
		} 
	}
	public boolean equals(Circle circle) { 
		System.out.println("equals(Circle) called");
		return circle.centre.equals(centre) && circle.radius == radius;
	}
	public void method() {
		System.out.println("Yay!");
	}
}
