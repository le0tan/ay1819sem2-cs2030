import java.util.ArrayList;
import java.util.List;

/**
 * GenericExample
 */
public class GenericExample {

    
    //Draw method 1 
    /*void draw(List list){
        System.out.println("draw a raw list");
    }*/

    //Draw method 2  
    void draw(List<Shape> list){
        for(Shape s: list){
            s.draw();
        }
        System.out.println("draw an list of objects");
        list.add(new Circle()); //this is allowed, but it will be wrong if we can pass in a list of rectangles as argument 
    }

    //covarience example 
    void draw1(List<? extends Shape> list){
        for(Shape s: list){
            s.draw();
        }
        System.out.println("draw an list of objects");
        //list.add(new Circle()); //this is allowed, but it will be wrong if we can pass in a list of rectangles as argument         
    }

    //contra-variance example
    void draw2(List<? super Circle> list){
        /*for(Shape s: list){
            s.draw();
        }*/
        for(Object o: list){
            System.out.println(o.toString());
        }
        list.add(new Circle());
        list.add(new FilledCircle());
        //list.add(new Rectangle());
        //list.add(new Shape());
    }

    //wildcard example 
    //java allows you to retrieve things, but don't allow you to add things
    void draw3(List<?> list){
        for(Object o: list){
            System.out.println(o.toString());            
        }
    }

    //Draw method 3  
    /*void draw(List<Circle> list){
        System.out.println("draw an list of Circle");
    }*/

    //Draw method 4
    void draw(Shape s){
        System.out.println("draw a shape "+ s);
    }

    void draw(Shape[] shapeArray){
        System.out.println("draw a shape array");
        //shapeArray[0] = new Rectangle();
    }

    //Note that method 1, 2, 3 can't co-exist within
    //the same class, why? 

    public static void main(String[] args) {
        List rawList = new ArrayList();
        List<Shape> shapeList = new ArrayList<>();
        List<Circle> circleList = new ArrayList<>();

        GenericExample ge = new GenericExample();
        ge.draw(rawList); //ok with draw method 1 & 2
        ge.draw(shapeList); //ok with draw method 1 & 2
        //ge.draw(circleList); //ok with draw method 2
        ge.draw2(shapeList);
        ge.draw3(shapeList);
        ge.draw3(circleList);

        //let's print out the class type of each of the 
        //arraylist here 
        System.out.println("rawList's class type is " + rawList.getClass());
        System.out.println("shapeList's class type is " + shapeList.getClass());
        System.out.println("circleList's class type is " + circleList.getClass());

        Shape s = new Shape();
        ge.draw(s);

        s = new Circle();
        ge.draw(s);

        s = new FilledCircle();
        ge.draw(s);

        Shape[] sA = new Circle[10];
        ge.draw(sA);

        Integer[] nums = {19, 28, 37};
        String[] strs = {"hello", "world", "cs2030"};
        System.out.println(max3(nums));
        System.out.println(max3(strs));
    }

    public static void myFunction(Shape s){
        System.out.println("apply myFunction on " + s);
    }

    public static <T extends Comparable<T>> T max3(T[] nums)       	    {
	    T max = nums[0];
	    if (nums[1].compareTo(max) > 0) {
		 max = nums[1];
        }
        if (nums[2].compareTo(max) > 0) {
             max = nums[2];
        }
        return max;
    }
}