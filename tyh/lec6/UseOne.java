/**
 * UseOne
 */
public class UseOne {

    public static void main(String[] args) {
        //here the T of ExampleOne is set to be Number type
        ExampleOne<Number> numbers = new ExampleOne<Number>();
        
        //The method in ExampleOne setValue(T value) now
        //becomes setValue(Number value). Since Integer is a subtype of Number
        //we can safely pass it into the setValue method as a parameter. 
        numbers.setValue(Integer.valueOf(4));

        //get value will return type T, which is now set to Number, so this statement is valid
        Number num = numbers.getValue();
        System.out.println(num);
    }
}