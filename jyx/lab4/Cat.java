package cs2030.catsanddogs;

public class Cat extends Animal {
    private String colour;
    private boolean isLazy;

    public Cat(String name, int appetite, String colour) {
        this.name = name;
        this.appetite = appetite;
        this.colour = colour;
        this.sound = "meow";
        isLazy = false;
    }

    @Override
    public boolean canEat(Food food) {
        return food.forCat();
    }

    @Override
    public void sayHello(){
        if(isLazy) {
            System.out.println(toString() + " is lazy");
            isLazy = false;
        } else {
            System.out.println(toString() + " says meow and gets lazy");
            isLazy = true;
        }
    }

    @Override
    public String toString() {
        return name + "("+colour+")";
    }
}
