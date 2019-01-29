import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Point[] pts = new Point[n];
        for(int i=0;i<n;i++) {
            pts[i] = new Point(sc.nextDouble(), sc.nextDouble());
        }
        for(int i=0;i<n-1;i++) {
            System.out.print(pts[i].toString()+" and "+pts[i+1].toString()+" has mid-point ");
            System.out.print(pts[i].midPoint(pts[i+1]));
            System.out.print(" and angle ");
            System.out.println(String.format("%.3f", pts[i].angleTo(pts[i+1])));
        }
    }
}
