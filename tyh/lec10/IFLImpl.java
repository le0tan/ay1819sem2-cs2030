import java.util.function.Function;

abstract class IFLImpl<T> implements IFL<T> {

  public <R> IFL<R> map(Function<T,R> mapper) {
    return new IFLImpl<R>() {
      @Override
      public R get() {
        return mapper.apply(IFLImpl.this.get());
      }
    };
  }

}
