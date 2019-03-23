/**
 * Burger
 */
public class Burger {

    private final String bun;
    private final String patty;
    private final String vegetable;

    private Burger(String bun, String patty, String vegetable) {
        this.bun = bun;
        this.patty = patty;
        this.vegetable = vegetable;
    }

    public static Burger create(String bun) {
        return new Burger(bun, null, null);
    }

    public Burger patty(String patty) {
        return new Burger(this.bun, patty, this.vegetable);
    }

    public Burger vegetable(String veg) {
        return new Burger(this.bun, this.patty, veg);
    }

    @Override
    public String toString() {
        return (this.patty==null ? "no patty" : this.patty) + ", " 
                + (this.vegetable==null ? "no vegetable" : this.vegetable) + ", "
                + "on a " + this.bun + " bun";
    }
}