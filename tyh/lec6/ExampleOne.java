/**
 * ExampleOne
 * Parameterized types can be used in both class or
 * method definitions
 * Once a parameterized type is defined in a class, 
 * it can be used in 4 ways (see below). Note that it can't 
 * be used to define a static variable or method 
 * One can also use a parameterized type for a method. 
 * This declaration is ok for both static or non-static methods
 */
public class ExampleOne<T> {

    //1) Use parametered type to declare a class variable
    private T value; // 1)
    
     //2) Use parametered type to define a return type
    public T getValue(){ // 2)
        return value;
    }

    //3) Use parametered type to define a method variable
    //4) Use it to define a local variable 
    public void setValue(T value){ // 3)
        T temp = value; // 4) 
        this.value = temp;
    }
    
    //Use parameterized type for a method 
    public static <Z> Z noOp(Z val){
        return val;
    }
}