
import java.util.List;
import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.OptionalDouble;
import java.util.function.*;

public interface MyIntStream {
    static MyIntStream of(int... values) {
        List<Integer> t = new ArrayList<>();
        for(int i: values) {
            t.add(i);
        }
        return new MyIntStreamImpl(t);
    }
    static MyIntStream range(int start, int end) {
        List<Integer> t = new ArrayList<>();
        for(int i=start;i<end;i++) {
            t.add(i);
        }
        return new MyIntStreamImpl(t);
    }
    static MyIntStream rangeClosed(int start, int end) {
        List<Integer> t = new ArrayList<>();
        for(int i=start;i<=end;i++) {
            t.add(i);
        }
        return new MyIntStreamImpl(t);
    }
    long count();
    int sum();
    OptionalDouble average();
    OptionalInt max();
    OptionalInt min();
    void forEach(IntConsumer action);

    MyIntStream limit(int maxSize);
    MyIntStream distinct();
    MyIntStream filter(IntPredicate predicate);
    MyIntStream map(IntUnaryOperator mapper);
}
