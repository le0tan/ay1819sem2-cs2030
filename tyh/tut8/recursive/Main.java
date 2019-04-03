import java.util.function.*;

class Main {
	static Compute<Long> sum(long n, long s) { 
		if (n == 0) {
			return new Base<>(() -> {
				System.out.printf("Created base () -> %d\n", s);	
				return s;
			}); 
		} else {
			return new Recursive<>(() -> {
				System.out.printf("Created intermediate sum(%d, %d)\n", n-1, n+s);	
				return sum(n - 1, n + s);
			}); 
		}
	}

	static long summer(long n) { 
		Compute<Long> result = sum(n, 0);
		while (result.isRecursive()) { 
			result = result.recurse();
        }
        return result.evaluate();
	}

	static Compute<Long> fact(long n, long res) {
		if(n==0) {
			return new Base<>(()->res);
		} else {
			return new Recursive<>(()->{
				// System.out.printf("(%d, %d)\n", n-1, res*n);
				return fact(n-1,res*n);
			});
		}
	}

	static long facter(long n) {
		Compute<Long> res = fact(n,1);
		while(res.isRecursive()) {
			res = res.recurse();
		}
		return res.evaluate();
 	}

	public static void main(String[] args) {
		System.out.println(facter(10));
	}
}