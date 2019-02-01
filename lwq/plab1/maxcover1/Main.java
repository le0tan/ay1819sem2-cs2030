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
        for(i = 1; i <= num; i++) {
            a = sc.nextDouble();
            b = sc.nextDouble();
            Point p = new Point(a, b);
            System.out.printf("(%.3f, %.3f)\n", p.getX(), p.getY());
        }
    }
}
