class Circle {
    Point center;
    double radius;
    Circle(Point c, double r) {
        this.center = c; this.radius = r;
    }
    public String toString() {
        return "Circle at "+center.toString()+" with r "+Double.toString(radius);
    }
}
