package cs2030.catsanddogs;
import java.util.*;

public class FarmManager {
	List<Animal> animals;
	LinkedList<Food> foods;

	public FarmManager() {
		animals = new ArrayList<Animal>();
		foods = new LinkedList<Food>();
	}

	public void addAnimal(Animal a) {
		animals.add(a);
		Collections.sort(animals, new AnimalComparator());
	}

	public void addFood(Food f) {
		foods.add(f);
	}
}
