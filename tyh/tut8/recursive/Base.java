import java.util.function.*;

class Base<T> extends Compute<T> {

	public Base(Supplier<T> s) {
		this.sup = s;
	}
	
}