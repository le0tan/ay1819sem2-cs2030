public class SavingsAccount {
    Employee owner;
    Bank bank;
    double balance;

    public SavingsAccount(Employee a, Bank b) {
        owner = a;
        bank = b;
        balance = 0.0;
    }

    public double compute(int months) {
        double curSal = owner.salary;
        for(int i=1;i<=months;i++) {
            if(i%12==1 && i!=1) {
                curSal*=(1.0+owner.increase);
            }
            balance += curSal;
            balance += balance*(bank.interest/12.0);
        }
        return balance;
    }
}
