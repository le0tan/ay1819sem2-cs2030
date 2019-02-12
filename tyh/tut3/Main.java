class Main {
    static void f() throws IllegalArgumentException {
        try {
            System.out.println("Before throw");
            throw new IllegalArgumentException();
            System.out.println("After throw");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught in f");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("Before f");
            f();
            System.out.println("After f");
        } catch (Exception e) {
            System.out.println("Caught in main");
        }
    }
}