/**
 * Cuboid
 */
public class Cuboid implements Shape3D{

    private double length, breadth, height;

    public double getVolume() {
        return length * breadth * height;
    }

    Cuboid(double a, double b, double c) {
        length = a; breadth = b; height = c;
    }
}