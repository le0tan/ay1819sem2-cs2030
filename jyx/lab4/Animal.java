package cs2030.catsanddogs;
import java.util.*;

public abstract class Animal implements Comparable<Animal>{
    protected String name, sound;
    protected int appetite;
    protected int hasEat = 0;

    public abstract void sayHello();

    public abstract boolean canEat(Food food);

    public void eat(List<Food> food) {
        ListIterator<Food> it = food.listIterator();
        while(hasEat < appetite && it.hasNext()) {
            Food cur = it.next();
            if(canEat(cur)) {
                System.out.println(toString() + " eats " + cur);
                it.remove();
                hasEat++;
            }
        }
    }

    @Override
    public int compareTo(Animal an) {
        return name.compareTo(an.name);
    }
}

