public class Super {
	public static void main(String[] args) {
		System.out.println(C.g());
	}
}

class A {
	protected static int f() {
		return 1;
	}
}

class B extends A {
	protected static int f() {
		return 2;
	}
}

class C extends B {
	protected static int f() {
		return 3;
	}

	protected static int g() {
		B b = new B();
		return b.super.f();
	}
}
