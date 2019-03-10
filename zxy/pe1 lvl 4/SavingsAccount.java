public class SavingsAccount implements Comparable<SavingsAccount>{
    Employee employee;
    Bank bank;
    double balance;

    public SavingsAccount(Employee employee, Bank bank) {
        this.employee = employee;
        this.bank = bank;
    }

    public double compute(int months) {
        int years = months/12;
        
        for (int i = 1; i <= months; i++) {
            if(i!=1&i%12==1){
                employee.raiseSalary();
            }
            balance = (balance + employee.getSalary())*(1+bank.getMonthInsterest()); 
        }
    return balance;
    }
    public int compareTo(SavingsAccount a){
     return a.balance>balance?-1:1;
    }
}
