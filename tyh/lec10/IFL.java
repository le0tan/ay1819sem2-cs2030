import java.util.function.*;
import java.util.*;

interface IFL<T> {
	static <T> IFL<T> generate(Supplier<T> sup) {
		return new IFLImpl<T>() {
			@Override
			public Optional<T> get() {
				return Optional.of(sup.get());
			}
		};
	}


	static <T> IFL<T> iterate(T seed, Function<T,T> next) {
		return new IFLImpl<T>() {
			T elem = seed;
			Function<T,T> func = x -> {
				func = next;
				return seed;
			};
			@Override
			public Optional<T> get() {
				elem = func.apply(elem);
				return Optional.of(elem);
			}
		};
	}

	public Optional<T> get();
	public <R> IFL<R> map(Function<T,R> mapper);
	public IFL<T> limit(int n);

	public void forEach(Consumer<T> cs);
	public T reduce(T identity, BiFunction<T,T,T> func);
}
