import java.util.*;
class OrderTaker {
	List<Food> foods;

	public OrderTaker() {
		foods = new ArrayList<Food>();
	}

	public Order takeOrder(List<Integer> arr) {
		Order order = new Order();
		for(int i: arr) {
			for(Food f: foods) {
				if(f.id==i) {
					order.add(f);
					break;
				}
			}
		}
		return order;
	}

	public void addCombo(List<Integer> arr) {
		List<Food> temp = new ArrayList<>();
		boolean ok = true;
		if(arr.size() == 3) {
			for(int i:arr) {
				boolean added=false;
				for(Food f: foods) {
					if(f.id==i && !(f instanceof Combo)) {
						temp.add(f);
						added=true;
						break;
					}
				}
				if(!added) {
					ok = false;
					break;
				}
			}
		} else {
			ok = false;
		}
		if(ok) {
			if(!(temp.get(0) instanceof Burger && temp.get(1) instanceof Snack && temp.get(2) instanceof Drink)) ok = false;
		}
		if(ok)
			foods.add(new Combo(temp));
		else {
			System.out.print("Error: Invalid combo input");
			for(int i: arr) {
				System.out.print(" "+i);
			}
			System.out.println();
		}
	}

	public void addFood(String t, String d, int p) {
		switch(t) {
			case "Burger":
				foods.add(new Burger(d,p));
				break;
			case "Snack":
				foods.add(new Snack(d,p));
				break;
			case "Drink":
				foods.add(new Drink(d,p));
				break;
			default:
				break;
		}
	}

	public void printFoods() {
		for(Food f: foods)
			if(f instanceof Burger) System.out.println(f);
		for(Food f: foods)
			if(f instanceof Snack) System.out.println(f);
		for(Food f: foods)
			if(f instanceof Drink) System.out.println(f);
	}
}
