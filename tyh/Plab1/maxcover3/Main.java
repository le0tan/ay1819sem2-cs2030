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
			if(pts[i].distanceTo(pts[i+1])>2) continue;
            System.out.print(pts[i].toString()+" and "+pts[i+1].toString()+" coincides with circle of centre ");
            System.out.println(pts[i].move(pts[i+1]));
        }
    }
}
