import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
class Main {
    static IntStream factors(int x) {
        return IntStream.rangeClosed(1,x).filter(i->x%i==0);
    }
    static IntStream primeFactors(int x) {
        return factors(x).filter(i->factors(i).count()==2);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        IntStream str = primeFactors(n);
        System.out.print("Prime factors of "+n+" are:");
        str.forEach(x->System.out.printf(" %d", x));
        System.out.println();
    }
}
