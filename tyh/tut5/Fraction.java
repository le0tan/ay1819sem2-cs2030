import java.lang.IllegalArgumentException;

/**
 * Fraction
 */
public class Fraction {
    private final int _numerator;
    private final int _denominator;
    public static Fraction ZERO = new Fraction(0, 1);
    public static Fraction ONE = new Fraction(1, 1);

    public Fraction(int num, int deno) {
        int neg = 1;
        if(num<0) {
            neg *= -1;
            num *= -1;
        }
        if(deno<0) {
            neg *= -1;
            deno *= -1;
        }
        if(deno == 0) throw new IllegalArgumentException("Divison by zero error");
        this._numerator = neg * num;
        this._denominator = deno;
    }

    public Fraction add(Fraction other) {
        return new Fraction(this.numerator() * other.denominator() + other.numerator() * this.denominator(),
                            this.denominator() * other.denominator()).simplify();
    }
    public Fraction subtract(Fraction other) {
        return new Fraction(this.numerator() * other.denominator() - other.numerator() * this.denominator(),
                            this.denominator() * other.denominator()).simplify();
    }
    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator() * other.numerator(),
                            this.denominator() * other.denominator()).simplify();
    }
    public Fraction divideBy(Fraction other) {
        return new Fraction(this.numerator() * other.denominator(),
                            this.denominator() * other.numerator()).simplify();
    }
    public Fraction simplify() {
        int gcd = gcd(this._numerator, this._denominator);
        return new Fraction(this._numerator/gcd, this._denominator/gcd);
    }
    public static int gcd(int a, int b) {
        if(a<b) return gcd(b,a);
        if(b==0) return a;
        return gcd(b, a%b);
    }
    public int numerator() {
        return this._numerator;
    }
    public int denominator() {
        return this._denominator;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Fraction)) return false;
        Fraction other = ((Fraction) obj).simplify();
        Fraction cur = this.simplify();
        return other.numerator()==cur.numerator() 
                && other.denominator()==cur.denominator();
    }
    @Override
    public String toString() {
        return this.numerator() + "/" + this.denominator();
    }

    public static Fraction valueOf(int num, int deno) {
        return new Fraction(num, deno);
    }
}