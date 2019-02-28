package cs2030.catsanddogs;

public class Cheese extends Food {

    public Cheese(String brand) {
        this.brand=brand;
    }

    public boolean forCat() {
        return false;
    }

    public boolean forDog() {
        return true;
    }

    @Override
    public String toString(){
        return brand+" cheese";
    }
}
