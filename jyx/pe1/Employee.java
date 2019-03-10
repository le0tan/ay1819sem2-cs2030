public class Employee {
    private String firstName;
    private String lastName;
    private double salary;
    private double raiseRate;
    private SalaryAdjust adjust;

    public Employee(String firstName, String lastName, double salary, SalaryAdjust adjust) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary =salary;
        this.adjust = adjust;
    }

    public double getSalary(){
        return salary;
    }

    public void setSalaryIncrease(double rate) {
        this.raiseRate = (adjust.adjust(rate));
    }

    @Override
    public String toString() {
        return String.format("%s %s: salary is %dK, annual raise is %d%%",firstName, lastName, (int)(salary/1000), (int)raiseRate);
    }

    public double getSalary(int year) {
        return salary * Math.pow((1 + raiseRate/100), year);
    }
}
