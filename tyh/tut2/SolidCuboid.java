/**
 * SolidCuboid
 */
public class SolidCuboid extends Cuboid implements Solid3D{

    private double density;

    SolidCuboid(double density, double length, double breadth, double height) {
        super(length, breadth, height);
        this.density = density;
    }

    SolidCuboid(double length, double breadth, double height) {
        super(length, breadth, height);
        this.density = 1.0;
    }

    @Override
    public double getMass() {
        return this.getVolume() *density;
    }

    @Override
    public double getDensity() {
        return density;
    }
}