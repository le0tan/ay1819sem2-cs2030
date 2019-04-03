import java.util.function.*;

abstract class Compute<T> {

	Supplier<T> sup;
	Supplier<Compute<T>> rec_sup;

	Compute<T> recurse() {
		return rec_sup.get();
	}

	T evaluate() {
		return sup.get();
	}

	boolean isRecursive() {
		return this instanceof Recursive;
	}
}