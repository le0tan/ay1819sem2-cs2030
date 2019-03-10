public class Bank {
    public static Bank[] BANKS;
    String name;
    double interest;

    public Bank(String a, double b) {
        name=a;
        interest=b;
    }

    public static Bank getBankByName(String a) {
        for(Bank b: BANKS) {
            if(b.name.equals(a)) {
                return b;
            }
        }
        return null;
    }
}
