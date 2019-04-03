package cs2030.mystream;

import java.util.function.Consumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

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

    public InfiniteList<T> limit(long max) {
        return new InfiniteListImpl<T>() {
            long count = max;
            
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

    
    public long count() {
        int cnt = 0;
        while(this.get().isPresent()) cnt++;
        return cnt;
    }

    
    public void forEach(Consumer<T> cons) {
        Optional<T> cur;
        while(true) {
            cur = this.get();
            if(!cur.isPresent()) return;
            cons.accept(cur.get());
        }
    }

    
    public Optional<T> reduce(BiFunction<T,T,T> accum) {
        Optional<T> res = this.get();
        if(!res.isPresent()) return res;
        Optional<T> cur = this.get();
        while(cur.isPresent()) {
            res = Optional.of(accum.apply(res.get(),cur.get()));
            cur = this.get();
        }
        return res;
    }

    
    public T reduce(T identity, BiFunction<T,T,T> accum) {
        Optional<T> cur = this.get();
        if(!cur.isPresent()) return identity;
        while(cur.isPresent()) {
            identity = accum.apply(identity, cur.get());
            cur = this.get();
        }
        return identity;
    }

    
    public Object[] toArray() {
        List<Object> res = new ArrayList<>();
        Optional<T> cur = this.get();
        while(cur.isPresent()) {
            res.add(cur.get());
            cur = this.get();
        }
        return res.toArray();
    }
}
