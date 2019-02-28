import java.io.*;
import java.util.*;
import cs2030.catsanddogs.Animal;
import cs2030.catsanddogs.Cat;
import cs2030.catsanddogs.Dog;
import cs2030.catsanddogs.Food;
import cs2030.catsanddogs.Cheese;
import cs2030.catsanddogs.Tuna;
import cs2030.catsanddogs.Chocolate;
import cs2030.catsanddogs.IllegalInstructionException;

public class Main {
    public static void main(String[] args) {
        String str;
        String[] words;
        ArrayList<Animal> animals = new ArrayList<>();
        LinkedList<Food> food = new LinkedList<>();
        try{
            BufferedReader scroll = new BufferedReader(new FileReader(args[0]));
            while(true) {
                str = scroll.readLine();
                if(str == null) break;
                words = str.split(" ");
                try{
                    switch(words[0]) {
                        case "new":
                            switch(words[1]) {
                                case "cat":
                                    if(words.length < 5) throw new IllegalInstructionException("Too few arguments");
                                    animals.add(new Cat(words[2],Integer.valueOf(words[3]), words[4]));
                                    break;
                                case "dog":
                                    if(words.length < 5) throw new IllegalInstructionException("Too few arguments");
                                    animals.add(new Dog(words[2],Integer.valueOf(words[3]), words[4]));
                                    break;
                                default:
                                    throw new IllegalInstructionException(words[1] + " is not a valid species");
                            }
                            System.out.println(animals.get(animals.size()-1) + " was created");
                            break;
                        case "add":
                            switch(words[1]) {
                                case "cheese":
                                    if(words.length < 3) throw new IllegalInstructionException("Too few arguments");
                                    food.add(new Cheese(words[2]));
                                    break;
                                case "tuna":
                                    if(words.length < 3) throw new IllegalInstructionException("Too few arguments");
                                    food.add(new Tuna(words[2]));
                                    break;
                                case "chocolate":
                                    if(words.length < 4) throw new IllegalInstructionException("Too few arguments");
                                    food.add(new Chocolate(words[2],words[3]));
                                    break;
                                default:
                                    throw new IllegalInstructionException(words[1] + " is not a valid food type");
                             }
                            System.out.println(food.get(food.size()-1) + " was added");
                            break;
                        case "eat":
                            Collections.sort(animals);
                            for(Animal cur: animals)
                                cur.eat(food);
                            break;
                        case "hello":
                            Collections.sort(animals);
                            for(Animal cur: animals)
                                cur.sayHello();
                            break;
                        default: 
                            throw new IllegalInstructionException(words[0] + " is not a valid instruction");
                    }
                }
                catch(IllegalInstructionException e) {
                    System.err.println(e);
                }
            }
        }
        catch(FileNotFoundException e) {
            System.err.println(e);
        }
        catch(IOException e) {
        }
    }
}
