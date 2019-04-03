import java.util.function.*;
import java.util.*;

public abstract class InfiniteListImpl<T> implements InfiniteList<T> {
//    abstract public T get(); 
    public <R> InfiniteList<R> map(Function<T,R> mapper) {
        return new InfiniteListImpl<R>() {
            public Optional<R> get() {
                Optional<T> cur = InfiniteListImpl.this.get();
                if(cur.isPresent()) return Optional.of(mapper.apply(cur.get()));
                else return Optional.empty();
            }
        };
    }

    public InfiniteList<T> limit(int max) {
        return new InfiniteListImpl<T>() {
            int count = max;
            public Optional<T> get() {
                if(count<=0) return Optional.empty();
                count--;
                return InfiniteListImpl.this.get();
            }
        };
    }

    public InfiniteList<T> filter(Predicate<T> pred) {
        return new InfiniteListImpl<T>() {
            public Optional<T> get() {
                Optional<T> cur = InfiniteListImpl.this.get();
                while(cur.isPresent()) {
                    if(pred.test(cur.get())) return cur;
                    else cur = InfiniteListImpl.this.get();
                }
                return Optional.empty();
            }
        };
    }

    public InfiniteList<T> takeWhile(Predicate<T> pred) {
        return new InfiniteListImpl<T>() {
            boolean ok = true;
            public Optional<T> get() {
                if(!ok) return Optional.empty();
                Optional<T> cur = InfiniteListImpl.this.get();
                if(!pred.test(cur.get())) {
                    ok = false;
                    return Optional.empty();
                } else {
                    return cur;
                }
            }
        };
    }
}
