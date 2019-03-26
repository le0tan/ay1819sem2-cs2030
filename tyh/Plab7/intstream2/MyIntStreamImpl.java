
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
    public String toString() {
        return data.toString();
    }
}
