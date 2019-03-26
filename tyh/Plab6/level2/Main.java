import java.util.*;
import java.util.stream.IntStream;
class Main {
    static boolean isPerfect(int n) {
        return n != 1 && 
            n == IntStream.rangeClosed(1,n/2).map(x->n%x==0?x:0).sum();
    }
    static boolean isSquare(int n) {
        return 0 != IntStream.rangeClosed(0,n).map(x->n==x*x?1:0).filter(x->x==1).count();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(isSquare(sc.nextInt()));
    }
}
