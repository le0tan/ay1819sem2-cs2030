package cs2030.mystream;
import java.util.*;
import java.util.function.*;
import java.util.OptionalDouble;
import java.util.OptionalInt;

public class MyIntStreamImpl implements MyIntStream {
    private List<Integer> data;
    MyIntStreamImpl(List<Integer> l) {
        data = new ArrayList<>();
        for(int i: l) data.add(i);
    }
    @Override
    public long count() {
        return data.size();
    }
    @Override
    public int sum() {
        int res = 0;
        for(int i: data) res += i;
        return res;
    }
    @Override
    public OptionalDouble average() {
        if(data.isEmpty()) return OptionalDouble.empty();
        return OptionalDouble.of((double)this.sum() / (double)this.count());
    }
    @Override
    public OptionalInt max() {
        if(data.isEmpty()) return OptionalInt.empty();
        int max = data.get(0);
        for(int i:data) max = Math.max(max, i);
        return OptionalInt.of(max);
    }
    @Override
    public OptionalInt min() {
        if(data.isEmpty()) return OptionalInt.empty();
        int min = data.get(0);
        for(int i:data) min = Math.min(min, i);
        return OptionalInt.of(min);
    }
    @Override
    public void forEach(IntConsumer action) {
        for(int i:data) action.accept(i);
    }

    @Override
    public MyIntStream limit(int maxSize) {
        List<Integer> t = new ArrayList<>();
        for(int i=0;i<maxSize && i<data.size();i++)
            t.add(data.get(i));
        return new MyIntStreamImpl(t);
    }
    @Override
    public MyIntStream distinct() {
        Set<Integer> set = new LinkedHashSet<>();
        for(int i: data) set.add(i);
        List<Integer> t = new ArrayList<>();
        for(int i: set) t.add(i);
        return new MyIntStreamImpl(t);
    }
    @Override
    public MyIntStream filter(IntPredicate predicate) {
        List<Integer> t = new ArrayList<>();
        for(int i: data) if(predicate.test(i)) t.add(i);
        return new MyIntStreamImpl(t);
    }
    @Override
    public MyIntStream map(IntUnaryOperator mapper) {
        List<Integer> t = new ArrayList<>();
        for(int i: data) t.add(mapper.applyAsInt(i));
        return new MyIntStreamImpl(t);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for(int i: data) {
            identity = op.applyAsInt(identity, i);
        }
        return identity;
    }
    @Override
    public OptionalInt reduce(IntBinaryOperator op) {
        if(data.isEmpty()) return OptionalInt.empty();
        int res = data.get(0);
        for(int i=1;i<data.size();i++)
            res = op.applyAsInt(res, data.get(i));
        return OptionalInt.of(res);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
