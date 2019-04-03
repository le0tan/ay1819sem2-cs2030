import java.util.function.Supplier;
import java.util.function.Function;
import java.util.Optional;


interface InfiniteList<T> {
    
    static <T> InfiniteList<T> generate(Supplier<T> supplier) {
        return new InfiniteListImpl<T>() {
            @Override
            public T get() {
                return supplier.get();
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
            @Override
            public T get() {
                return val = f.apply(val);
            }
        };
    }

    public T get();
}
