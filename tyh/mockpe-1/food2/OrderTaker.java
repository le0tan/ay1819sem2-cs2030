import java.util.*;
class OrderTaker {
	List<Food> foods;

	public OrderTaker() {
		foods = new ArrayList<Food>();
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
