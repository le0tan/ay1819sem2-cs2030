class Fib {
  public static void main(String[] args) {
    FibTask task = new FibTask(10);
    System.out.println(task.compute());
  }
}
