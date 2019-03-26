import java.util.*;
import java.util.stream.IntStream;
class Main {
    static boolean isPerfect(int n) {
        return n != 1 && 
            n == IntStream.rangeClosed(1,n/2).map(x->n%x==0?x:0).sum();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(isPerfect(sc.nextInt()));
    }
}
