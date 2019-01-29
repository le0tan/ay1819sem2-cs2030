import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Point[] pts = new Point[n];
        for(int i=0;i<n;i++) {
            pts[i] = new Point(sc.nextDouble(), sc.nextDouble());
        }
        int max = 0;
        for(int i=0;i<n-1;i++) {
            for(int j=i+1;j<n;j++) {
				if(pts[i].distanceTo(pts[j])>2) continue;
                Circle c = new Circle(pts[i].move(pts[j]), 1);
//                System.out.println(c);
                int count = 0;
                for(int k=0;k<n;k++)
                    if(pts[k].inCircle(c)) count++;
                if(count>max) max = count;
            }
        }
        System.out.println(String.format("Maximum Disc Coverage: %d",max));
    }
}
