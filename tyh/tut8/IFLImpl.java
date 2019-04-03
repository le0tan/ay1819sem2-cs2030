abstract class IFLImpl<T> implements IFL<T> { 
	public <R> IFL<R> map(Function<T, R> mapper) {
		return new IFLImpl<R>() { 
			public Optional<R> get() {
                return IFLImpl.this.get().map(mapper);
            }
		};
	}
	public void forEach(Consumer<T> action) { 
		Optional<T> curr = get();
		while (curr.isPresent()) {
            action.accept(curr.get());
            curr = get();
        }
	}

	public abstract Optional<T> get();
}