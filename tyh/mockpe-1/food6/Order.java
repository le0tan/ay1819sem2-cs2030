import java.util.*;
class Order {
	List<Food> order;
	int total;
	public Order() {
		total = 0;
		order = new ArrayList<Food>();
	}
	public Order(List<Food> l) {
		order = l;
	}
	public void add(Food f) {
		order.add(f);
		total += f.price;
	}

	public void print() {
		System.out.println("--- Order ---");
		for(Food f: order) {
			System.out.println(f);
		}
		System.out.println("Total: "+total);
	}
}
