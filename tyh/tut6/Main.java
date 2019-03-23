import java.util.function.IntFunction;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        // System.out.println(apporxPI(1000));
        // LongStream om = omega(10);
        // om.forEach(x->System.out.println(x));
        // System.out.println(sum(IntStream.range(1, 10), x->x));
        // Func f = new F();
        // Func g = new G();
        // System.out.println(f.compose(g).apply(10));
        IntFunction<IntUnaryOperator> f = new IntFunction<IntUnaryOperator>() {
            @Override
            public IntUnaryOperator apply(int value) {
                return new IntUnaryOperator(){
                
                    @Override
                    public int applyAsInt(int operand) {
                        return value + operand;
                    }
                };
            }
        };
        IntFunction<IntFunction<IntUnaryOperator>> g = new IntFunction<IntFunction<IntUnaryOperator>>() {
            @Override
            public IntFunction<IntUnaryOperator> apply(int a) {
                return new IntFunction<IntUnaryOperator>() {
                    @Override
                    public IntUnaryOperator apply(int b) {
                        return new IntUnaryOperator(){
                        
                            @Override
                            public int applyAsInt(int c) {
                                return a+b+c;
                            }
                        };
                    }
                };
            }
        };
        IntFunction<IntFunction<IntUnaryOperator>> h = x->y->z->x+y+z;
        System.out.println(g.apply(1).apply(2).applyAsInt(3));
        System.out.println(h.apply(1).apply(2).applyAsInt(3));
    }

    static double apporxPI(int n) {
        double ans = IntStream
                        .rangeClosed(1, n)
                        .mapToDouble(x -> {
                            double term = 4.0 * (x%2==0 ? -1 : 1) / (2*x-1);
                            return term;
                        })
                        .sum();
        return ans;
    }

    static LongStream omega(int n) {
        return LongStream.range(1, n+1).map(x -> {
            return LongStream.range(2, x+1).map(y -> x%y==0 && isPrime(y) ? 1 : 0).sum();
        });
    }

    static boolean isPrime(long n) {
        return LongStream.range(2,n).noneMatch(x -> n%x==0);
    }

    static int sum(IntStream str, IntUnaryOperator op) {
        return str.reduce(0, (x,y) -> (x+op.applyAsInt(y)));
    }
}

class F extends Func {
    @Override
    public int apply(int n) {
        return n*10;
    }
}

class G extends Func {
    @Override
    public int apply(int n) {
        return n+2;
    }
}