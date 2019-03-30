import java.util.function.Function;
import java.util.function.*;

abstract class IFLImpl<T> implements IFL<T> {

  public <R> IFL<R> map(Function<T,R> mapper) {
    return new IFLImpl<R>() {
      public R get() {
        return mapper.apply(IFLImpl.this.get());
      }
    };
  }
  
  public void forEach(Consumer<T> f) {
    while(true) {
      f.accept(this.get());
    }
  }
}
