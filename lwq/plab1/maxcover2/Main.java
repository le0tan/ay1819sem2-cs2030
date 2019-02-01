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
            System.out.printf("(%.3f, %.3f) and (%.3f, %.3f) has mid-point (%.3f, %.3f) and angle %.3f\n", p.getX(), p.getY(), q.getX(), q.getY(), cir.getMiddleX(p, q), cir.getMiddleY(p, q), cir.getAngle(cir.disX(p, q), cir.disY(p, q)));
            p = q;
        }
    }
}
