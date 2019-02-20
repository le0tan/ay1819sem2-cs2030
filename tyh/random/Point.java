public class Point {
	double x;
	double y;
	public boolean equals(Point p) {
		return x==p.x && y==p.y;
	}
	Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
