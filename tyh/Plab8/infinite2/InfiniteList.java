import java.util.function.*;
import java.util.*;


public interface InfiniteList<T> {
    
    static <T> InfiniteList<T> generate(Supplier<T> supplier) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                return Optional.of(supplier.get());
            }
        };
    }
    
    static <T> InfiniteList<T> iterate(T seed, Function<T,T> next) {
        return new InfiniteListImpl<T>() {
            T val = seed;
            Function<T,T> f =  x -> {
                f = next;
                return x;
            };
            public Optional<T> get() {
                return Optional.of(val = f.apply(val));
            }
        };
    }

    public Optional<T> get();
    public <R> InfiniteList<R> map(Function<T,R> mapper);
    public InfiniteList<T> limit(int n);
    public InfiniteList<T> filter(Predicate<T> p);
    public InfiniteList<T> takeWhile(Predicate<T> p);
}
