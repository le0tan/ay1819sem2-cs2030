package cs2030.catsanddogs;

public class Dog extends Animal {
    private int helloAcc = 0;
    
    public Dog(String name, int appetite, String sound) {
        this.name=name;
        this.appetite=appetite;
        this.sound=sound;
    }

    @Override
    public boolean canEat(Food food) {
        return food.forDog();
    }

    @Override
    public void sayHello(){
        System.out.print(toString() + " says ");
        helloAcc++;
        for(int i = 0;i<helloAcc;i++){
            System.out.print(sound);
        }
        System.out.println();
    }

    @Override
    public String toString(){
        return name;
    }
}
