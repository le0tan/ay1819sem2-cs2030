import java.util.*;

public class Main {
  public static void main(String[] args) {
    A a = new A();
    Base b = a;
    b.f();
  }
}

class Base {
  public void f() {
    System.out.println("Base");
  }
}

class A extends Base {
  public void f() {
    System.out.println("A");
  }
}
