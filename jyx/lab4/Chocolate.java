package cs2030.catsanddogs;

public class Chocolate extends Food {
    private String flavour;
    public Chocolate(String brand, String flavour){
        this.brand = brand;
        this.flavour = flavour;
    }

    public boolean forCat() {
        return true;
    }

    public boolean forDog() {
        return false;
    }

    @Override 
    public String toString(){
        return brand+" "+flavour+" chocolate";
    }
}
