public class Bank{
    
    public static Bank[] BANKS;

    String name;
    double annualInterest;

    public Bank (String name, double interest) {
        this.name = name;
        this.annualInterest = interest;
    }

    public double getMonthInsterest() {
        return annualInterest/12.0;
    }

    public static Bank[] getBanks() {
        return BANKS;
    }

    public static Bank getBankByName(String bankName) {
        for (int i = 0; i < BANKS.length; i++) {
            if(bankName.equals(BANKS[i].name)) {
                return BANKS[i];
            }
        }
        return null;
    }
}
