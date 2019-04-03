import java.util.stream.IntStream;

class Main {
    public static void main(String[] args) {
        InfiniteList<Integer> ifl = InfiniteList.iterate(1,x->x+1);
        IntStream.range(1, 5).forEach(x -> System.out.println(ifl.get()));
        InfiniteList<Integer> ifl2 = InfiniteList.iterate(1, x -> x + 1).filter(x -> x % 2 == 0);
        IntStream.range(1, 5).forEach(x -> System.out.println(ifl2.get()));
        InfiniteList<Integer> ifl3 = InfiniteList.iterate(1, x -> x + 1).limit(2);
        IntStream.range(1, 5).forEach(x -> System.out.println(ifl3.get()));
        InfiniteList<Integer> ifl4 = InfiniteList.iterate(1, x -> x + 1).limit(2).filter(x -> x % 2 == 0);
        IntStream.range(1, 5).forEach(x -> System.out.println(ifl4.get()));
        InfiniteList<Integer> ifl5 = InfiniteList.iterate(1, x -> x + 1).takeWhile(x -> x < 3);
        IntStream.range(1, 5).forEach(x -> System.out.println(ifl5.get()));
    }
}
