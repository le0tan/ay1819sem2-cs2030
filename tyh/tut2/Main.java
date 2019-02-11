/**
 * Main
 */
public class Main {

    static class A {
        protected int x = 0;
        }
        static class B extends A {
            public int x = 1;
        public void f() {
        System.out.println(super.x);
        }
        }
    public static void main(String[] args) {
        B b = new B();
        b.f();
    }
}