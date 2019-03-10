public class SavingsAccount implements Comparable<SavingsAccount> {
    private Employee employee;
    private Bank bank;
    private double balance;

    public SavingsAccount(Employee e, Bank b) {
        employee = e;
        bank = b;
        balance = 0;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getBalance() {
        return balance;
    }

    public void compute(int months) {
        double s = 0;
        for(int i = 0; i < months; i++) {
            s += employee.getSalary(i/12);
            s = s * (1 + bank.getMonthlyRate());
        }
        balance = s;
    }

    @Override 
    public int compareTo(SavingsAccount sa) {
        return Double.compare(balance, sa.balance);
    }
}
