package cs2030.catsanddogs;

public class Tuna extends Food {

    public Tuna(String brand) {
        this.brand=brand;
    }

    public boolean forCat() {
        return true;
    }

    public boolean forDog() {
        return true;
    }

    @Override
    public String toString() {
        return brand + " tuna";
    }
}
