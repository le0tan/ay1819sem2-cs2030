public class SavingsAccount {
    private Employee employee;
    private Bank bank;

    public SavingsAccount(Employee e, Bank b) {
        employee = e;
        bank = b;
    }

    public double compute(int months) {
        double s = 0;
        for(int i = 0; i < months; i++) {
            s += employee.getSalary(i/12);
            s = s * (1 + bank.getMonthlyRate());
        }
        return s;
    }
}
