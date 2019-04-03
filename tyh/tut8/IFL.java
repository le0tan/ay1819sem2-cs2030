public interface IFL<T> {
	public static <T> IFL<T> iterate(T seed, Predicate<T> cond, Function<T, T> next) { 
		return new IFLImpl<T>() {
			T element = seed; 
			boolean ok = true;
			Function<T, T> func = x -> {
								func = next;
				                return element;
				        		};
			public Optional<T> get() {
				if(!ok) return Optional.empty();
				element = func.apply(element);
				if(!cond.test(element)) { ok = false; return Optional.empty(); }
				return Optional.of(element);
			}
		};
	}

	public static <T> IFL<T> concat(IFL<T> l1, IFL<T> l2) {
		return new IFLImpl<T>() {
			boolean first = true;
			public Optional<T> get() {
				if(!first) return l2.get();
				Optional<T> t = l1.get();
				if(!t.isPresent()) {
					first = false;
					return l2.get();
				} else {
					return t;
				}
			}
		};	
	}

	public void forEach(Consumer<T> action);
	public <R> IFL<R> map(Function<T, R> mapper); 
	Optional<T> get();
}