package cs2030.catsanddogs;
import java.util.*;
public class ScrollReader {
	public static int helloNo=0;
	public static void read(String s, FarmManager fm) {
		String[] args = s.split(" ");
		try {
			switch(args[0]) {
				case "new":
					try {
						if(args[1].equals("cat")) {
							fm.addAnimal(new Cat(args[2],Integer.parseInt(args[3]),args[4]));
						} else if(args[1].equals("dog")) {
							fm.addAnimal(new Dog(args[2],Integer.parseInt(args[3]),args[4]));
						} else {
							throw new IllegalInstructionException(args[1]+" is not a valid species");
						}
					} catch (IndexOutOfBoundsException ex) {
						throw new IllegalInstructionException("Too few arguments");
					}
					break;
				case "add":
					try {
						switch(args[1]) {
							case "cheese":
								fm.addFood(new Cheese(args[2]));
								break;
							case "tuna":
								fm.addFood(new Tuna(args[2]));
								break;
							case "chocolate":
								fm.addFood(new Chocolate(args[2], args[3]));
								break;
							default:
								throw new IllegalInstructionException(args[1]+" is not a valid food type");
						}
					} catch (IndexOutOfBoundsException ex) {
						throw new IllegalInstructionException("Too few arguments");
					}
					break;
				case "eat":
					for(Animal a: fm.animals) {
						ListIterator<Food> it = fm.foods.listIterator();
						while(a.appetite>0 && it.hasNext()) {
							if(a.eat(it.next())) { 
								it.remove();
							}
						}
					}
					break;
				case "hello":
					for(Animal a: fm.animals) {
						a.sayHello();
					}
					break;
				default:
					throw new IllegalInstructionException(args[0]+" is not a valid instruction");
			}
		} catch (IllegalInstructionException e) {
			System.err.println(e);
		}
	}

}
