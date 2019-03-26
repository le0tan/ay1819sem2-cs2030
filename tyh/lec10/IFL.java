import java.util.function.Supplier;
import java.util.function.Function;

interface IFL<T> {
  
  static <U> IFL<U> generate(Supplier<U> sup) {
    return new IFLImpl<U>() {
      @Override
      public U get() {
        return sup.get();
      }
    };
  }

  static <U> IFL<U> iterate(U seed, Function<U,U> next) {
    return new IFLImpl<U>() {
      U elem = seed;
      Function<U,U> func = x -> {
        func = next;
        return elem;
      };
      @Override
      public U get() {
        elem = func.apply(elem);
        return elem;
      }
    };
  }

  public T get();
  public <R> IFL<R> map(Function<T,R> mapper);
}
