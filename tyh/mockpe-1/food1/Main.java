import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OrderTaker ot = new OrderTaker();
			while(sc.next().equals("add")) {
				String type = sc.next();
				String desc = sc.next();
				int price = sc.nextInt();
				ot.addFood(type,desc,price);
			}
			ot.printFoods();
	}
}
