abstract class Food {
	String name;
	int price;
	int id;
	static int numOfFoods=-1;
	public Food(String s, int p) {
		name=s;
		price=p;
		numOfFoods++;
		id=numOfFoods;
	}
	abstract public String toString();
}
