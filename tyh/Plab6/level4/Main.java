import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
class Main {
    static OptionalDouble variance(int[] arr) {
        if(arr.length < 2) return OptionalDouble.empty();
        IntStream str = Arrays.stream(arr);
        OptionalDouble avg = str.average();
        str = Arrays.stream(arr);
        return OptionalDouble.of(str.mapToDouble(x->(x-avg.getAsDouble())*(x-avg.getAsDouble())).sum() / (arr.length-1));
    }
}
