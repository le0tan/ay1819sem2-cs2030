import java.util.*;
import java.util.function.*;

class LazyInt {
  Supplier<Integer> sup;
  public LazyInt(Supplier<Integer> s) {
    this.sup = s;
  }
  public LazyInt map(IntFunction<Integer> func) {
    return new LazyInt( () -> func.apply(this.get()));
  }
  public LazyInt flatMap(Function<Integer, LazyInt> func) {
    return func.apply(this.get());
  }
  public int get() {
    return sup.get();
  }
}
