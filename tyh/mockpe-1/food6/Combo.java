import java.util.*;
class Combo extends Food {
	List<Food> foods;
	public Combo(List<Food> content) {
		super("", 0);
		this.foods = content;
		for(Food f: foods) {
			price += f.price;
		}
		price-=50;
	}
	@Override public String toString() {
		String res = String.format("#%d Combo (%d)\n", id, price);
		for(Food f: foods) {
			res += "   "+f.toString()+"\n";
		}
		return res.substring(0,res.length()-1);
	}
}
