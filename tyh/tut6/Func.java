import java.util.function.IntUnaryOperator;

abstract class Func {
    public abstract int apply(int n);
    public Func compose(Func another) {
        return new Func() {
            @Override
            public int apply(int n) {
                return this.apply(another.apply(n));
            }
        };
    }
}