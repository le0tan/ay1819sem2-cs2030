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
}
