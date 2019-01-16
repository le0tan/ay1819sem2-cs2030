/**
 * Point
 */
public class Point {

    double x;
    double y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    };

    public static void main(String[] args) {
        Point pt = new Point(1.0,1.0);
        System.out.println(pt.x+pt.y);
    }
}