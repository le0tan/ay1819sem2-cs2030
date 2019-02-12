// class A {
//     int x;

//     A(int x) {
//         this.x = x;
//     }

//     public B method() {
//         return new B(x);
//     }
// }

// class B extends A {
//     B(int x) {
//         super(x);
//     }

//     @Override
//     public A method() {
//         return new A(x);
//     }
// }

class A {
    static void f() throws Exception {
        try {
            throw new Exception();
        } finally {
            System.out.print("1");
        }
    }

    static void g() throws Exception {
        System.out.print("2");
        f();
        System.out.print("3");
    }

    public static void main(String[] args) {
        try {
            g();
        } catch (Exception e) {
            System.out.print("4");
        }
    }
}