import java.util.ArrayList;
import java.util.List;

/**
 * UseList
 */
public class UseList {
    public static void main(String[] args) {
        
        //this line will cause both statement 1 and 2 unable to compile 
        //List<?> numbers = new ArrayList<Number>(); 

        //this line will cause statement 2 compile, but statement 1 fail to compile
        //the reason is this specify the list to contain objects with type of Number and its 
        //subclasses, and this could be type that's not compatible to the integer type, such as float
        //This makes numbers read only 
        //List<? extends Number> numbers = new ArrayList<Number>();

        //this line will cause statement 1 compile, but statement 2 fail to compile
        //this makes numbers write only
        List<? super Number> numbers = new ArrayList<Number>();

        //This line is ok 
        //List<Number> numbers = new ArrayList<Number>(); 

        //This line won't compile as inheritance in generic type is not supported
        //List<Number> numbers = new ArrayList<Integer>(); //this line won't compile

        //statement 1
        //numbers.add(Integer.valueOf(4)); 
        numbers.add(new Object());
        //numbers.add(Float.valueOf(4));

        //statement 2
        //Number numericValue = numbers.get(0);
        Object numericValue = numbers.get(0);

        System.out.println(numericValue);
    }
    
}