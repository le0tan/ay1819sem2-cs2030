import java.util.concurrent.RecursiveTask;

class FibTask extends RecursiveTask<Integer> {
  int n;

  FibTask(int n) {
    this.n = n;
  }
  
  @Override
  protected Integer compute() {
    if(n<=1) {
      return n;
    } else {
      FibTask a = new FibTask(n-1);
      FibTask b = new FibTask(n-2);
      b.fork();
      return a.compute() + b.join();
    }
  }
}
