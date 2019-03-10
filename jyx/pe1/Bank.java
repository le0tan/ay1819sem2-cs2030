public class Bank {
    public static Bank[] BANKS;
    
    private String name;
    private double monthlyRate;
    private double annualRate;
    public Bank(String name, double annualRate) {
        this.name  = name;
        this.annualRate = annualRate;
        this.monthlyRate = annualRate/12;
    }

    public double getAnnualRate() {
        return annualRate;
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public static Bank getBankByName(String name) {
        for(Bank b:BANKS) {
            if(b.name.equals(name)) return b;
        }
        return null;
    }
}


