package cs2030.catsanddogs;
import java.util.*;
public class AnimalComparator implements Comparator<Animal> {
	@Override
	public int compare(Animal a, Animal b) {
		return a.name.compareTo(b.name);
	}
}
