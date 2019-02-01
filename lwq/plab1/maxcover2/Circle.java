/**
 * Circle
 */
import java.math.*;
public class Circle {
    Point p;
    Point q;
    public Circle(Point a, Point b) {
        this.p = a;
        this.q = b;
    }    
    public double getMiddleX(Point a, Point b) {
        return (a.getX() + b.getX()) / 2;
    }

    public double getMiddleY(Point a, Point b) {
        return (a.getY() + b.getY()) / 2;
    }
    
    public double disX(Point a, Point b) {
        return (b.getX() - a.getX());
    }

    public double disY(Point a, Point b) {
        return (b.getY() - a.getY());
    }
    
    public double getAngle(double a, double b) {
        return Math.atan2(b, a);
    }
}
