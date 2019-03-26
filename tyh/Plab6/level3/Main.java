import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;
class Main {
    static long countRepeats(int[] array) {
    	return IntStream.range(0, array.length-1)
    					.filter(i -> {
    						if(i==0) return i+1>=array.length ? false : array[i]==array[i+1];
    						return array[i-1] != array[i] && array[i+1] == array[i];
    					})
    					.count();
    }
}
