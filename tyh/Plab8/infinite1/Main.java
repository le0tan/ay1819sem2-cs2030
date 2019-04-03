import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        InfiniteList<Integer> ifll = InfiniteList.iterate(1,x->x+1);
        IntStream.range(1, 5).forEach(x -> System.out.println(ifll.get()));
        InfiniteList<String> ifl = InfiniteList.iterate("A", x -> "A" + x);
        System.out.println(ifl.get()+ifl.get()+ifl.get());
    }
}
