/**
 * Main
 */
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num;
        int i;
        num = sc.nextInt();
        double a, b;
        double c, d;
        a = sc.nextDouble();
        b = sc.nextDouble();
        Point p = new Point(a, b);
        for(i = 2; i <= num; i++) {
            c = sc.nextDouble();
            d = sc.nextDouble();
            Point q = new Point(c, d);
            Circle cir = new Circle(p, q);
            if(cir.checkCircle()) {
                System.out.printf("(%.3f, %.3f) and (%.3f, %.3f) coincides with circle of centre (%.3f, %.3f)\n", p.getX(), p.getY(), q.getX(), q.getY(), cir.getNewCentreX(), cir.getNewCentreY());
            }
            p = q;
        }
    }
}
