import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;

public class Main {

	public static void main(String[] args) {
		int sum = IntStream.range(1,6)
								.parallel()
								.filter(x -> {
									System.out.printf("filter: %d with %s\n",
											x, Thread.currentThread().getName());
									return true;
								})
								.map(x -> {
									System.out.printf("map: %d with %s\n",
											x, Thread.currentThread().getName());
									return x;
								})
								.reduce(0,(x,y) -> {
									System.out.printf("reduce: %d+%d with %s\n",
											x,y, Thread.currentThread().getName());
									return x+y;
								});
		System.out.println(sum);
	}
}
