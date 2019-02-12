import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CruiseCenter cc = new CruiseCenter();
        int n = sc.nextInt();
        sc.nextLine();
        for(int i=0;i<n;i++) {
            cc.serve(new Cruise(sc.next(),sc.next()));
        }
        for(Loader l: cc.getLoaders()) {
            l.printServed();
        }
    }
}
