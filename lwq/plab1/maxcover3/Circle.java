/**
 * Circle
 */
import java.math.*;
public class Circle {
    private double unit = 1;
    public Point p;
    public Point q;
    public Circle(Point a, Point b) {
        this.p = a;
        this.q = b;
    }    
    public double getMiddleX() {
        return (p.getX() + q.getX()) / 2;
    }

    public double getMiddleY() {
        return (p.getY() + q.getY()) / 2;
    }
    
    public double disX() {
        return (p.getX() - q.getX());
    }

    public double disY() {
        return (p.getY() - q.getY());
    }
    
    public double getAngle() {
        double m = disX();
        double n = disY();
        return Math.atan2(n, m);
    }

    public double getDis() {
        return Math.sqrt(disX() * disX() + disY() * disY());
    }

    public boolean checkCircle() {
        if(getDis() > 2 * unit) {
            return false;
        } else {
            return true;
        }
    }

    public double countMoveDis() {
        double halfDis = getDis() / 2;
        double moveDis = Math.sqrt(1 - halfDis * halfDis);
        return moveDis;
    }

    public double getNewCentreX() {
        double newCentreX = getMiddleX() + Math.sin(getAngle()) * countMoveDis();
        if(Math.abs(newCentreX) < 0.001) {
            newCentreX = Math.abs(newCentreX);
        }
        return newCentreX;
    }

    public double getNewCentreY() {
        double newCentreY = getMiddleY() - Math.cos(getAngle()) * countMoveDis();
        if(Math.abs(newCentreY) < 0.001) {
            newCentreY = Math.abs(newCentreY);
        }
        return newCentreY;
    }
} 
