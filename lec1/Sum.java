import java.util.Scanner;

public class Sum {
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		int a = 0;
		for(int i=0;i<9;i++)
			a += sc.nextInt();
		System.out.println(a);
	}
}
