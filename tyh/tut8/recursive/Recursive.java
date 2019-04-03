import java.util.function.*;

class Recursive<T> extends Compute<T> {

	public Recursive(Supplier<Compute<T>> s) {
		this.rec_sup = s;
	}
}