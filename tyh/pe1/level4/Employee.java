public class Employee {
    String firstName;
    String lastName;
    double salary;
    double increase;
    SalaryAdjust adjust;

    public Employee(String a, String b, double c, SalaryAdjust adj) {
        firstName=a;
        lastName=b;
        salary=c;
        adjust=adj;
    }

    public void setSalaryIncrease(double a) {
        increase = adjust.adjust(a-1.0);
    }

    @Override
    public String toString() {
        return String.format("%s %s: salary is %dK, annual raise is %d", firstName, lastName, (int)salary/1000, (int)(increase*100))+"%";
    }
}
