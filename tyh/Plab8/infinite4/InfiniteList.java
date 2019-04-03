package cs2030.mystream;

import java.util.function.Supplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.BiFunction;
import java.util.Optional;


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
    public InfiniteList<T> limit(long n);
    public InfiniteList<T> filter(Predicate<T> p);
    public InfiniteList<T> takeWhile(Predicate<T> p);

    public long count();
    public void forEach(Consumer<T> a);
    public Optional<T> reduce(BiFunction<T,T,T> accum);
    public T reduce (T iden, BiFunction<T,T,T> accum);
    public Object[] toArray();
}
