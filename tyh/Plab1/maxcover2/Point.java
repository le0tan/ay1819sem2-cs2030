class Point {
    private double x;
    private double y;
    Point(double x, double y) {
        this.x = x; this.y = y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public String toString() {
        return String.format("(%.3f, %.3f)", x, y);
    }
    public Point midPoint(Point q) {
        return new Point((this.x+q.x)/2, (this.y+q.y)/2);
    }
    public double angleTo(Point q) {
        return Math.atan2(q.y-y,q.x-x);
    }
}
