import java.util.Scanner;
import java.util.*;

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		OrderTaker ot = new OrderTaker();
			while(sc.next().equals("add")) {
				String type = sc.next();
				if(type.equals("Combo")) {
					List<Integer> comboNo = new ArrayList<>();
					while(sc.hasNextInt()) {
						comboNo.add(sc.nextInt());
					}
					ot.addCombo(comboNo);
				} else {
					String desc = sc.next();
					int price = sc.nextInt();
					ot.addFood(type,desc,price);
				}
			}
			ot.printFoods();
			List<Integer> temp = new ArrayList<>();
			while(sc.hasNext()) {
				temp.add(sc.nextInt());
			}
			Order order = ot.takeOrder(temp);
			order.print();
	}
}
