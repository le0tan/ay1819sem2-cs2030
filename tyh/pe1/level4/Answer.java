public class Answer {
    Employee e;
    double balance;

    public Answer(Employee a, double b) {
        e=a;
        balance=b;
    }

    @Override
    public String toString() {
        return String.format("%s %s: salary is %dK, annual raise is %d", e.firstName, e.lastName, (int)(e.salary/1000),(int)(e.increase*100)) + "% has balance of "+String.format("%.2f",balance);
    }
}
