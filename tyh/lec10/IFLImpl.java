import java.util.*;
import java.util.function.*;

abstract class IFLImpl<T> implements IFL<T> {
	@Override
	public <R> IFL<R> map(Function<T,R> mapper) {
		return new IFLImpl<R>() {
			@Override
			public Optional<R> get() {
				return Optional.of(mapper.apply(IFLImpl.this.get().get()));
			}
		};
	}

	@Override
	public IFL<T> limit(int n) {
		return new IFLImpl<T>() {
			int count = n;
			@Override
			public Optional<T> get() {
				if(count>0) {
					count--;
					return IFLImpl.this.get();
				}
				else return Optional.empty();
			}
		};
	}

	@Override
	public void forEach(Consumer<T> cs) {
		Optional<T> cur;
		while(true) {
			cur = this.get();
			if(cur.isPresent()) cs.accept(cur.get());
			else break;
		}
	}

	@Override
	public T reduce(T identity, BiFunction<T,T,T> func) {
		Optional<T> cur;
		T res = identity;
		while(true) {
			cur = this.get();
			if(cur.isPresent()) {
				res = func.apply(res, cur.get());
			} else break;
		}
		return res;
	}
}
