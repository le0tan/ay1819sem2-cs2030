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
    public Point move(Point q) {
        double d = Math.sqrt(1-0.25*((x-q.x)*(x-q.x)+(y-q.y)*(y-q.y)));
        double theta = this.angleTo(q) + 0.5*Math.PI;
        Point m = this.midPoint(q);
        double xx = m.x+d*Math.cos(theta);
        double yy = m.y+d*Math.sin(theta);
        return new Point(Math.abs(xx)<0.0001?0:xx,Math.abs(yy)<0.0001?0:yy);
    }
}
