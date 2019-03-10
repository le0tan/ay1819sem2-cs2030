public class Employee {

   String firstName;
   String lastName;
   double salary,stSalary;
   double annualRaise;
   SalaryAdjust adjust1;

   public Employee (String firstName, String lastName, double salary, SalaryAdjust adjust) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.salary = stSalary = salary;
    adjust1 = adjust;
   }

   public void setSalaryIncrease (double raise) {
    this.annualRaise = raise;
    annualRaise =  adjust1.adjust(raise);
   }
   
   public double getSalary(){
    return salary;
   }

   public double annualRaise(){
    return annualRaise;
   }

   public void raiseSalary(){
    salary = salary*annualRaise;
   }

   @Override
   public String toString() {
    return String.format("%s %s: salary is %dK, annual raise is %d%c",
            firstName, lastName, (int)stSalary/1000, (int)(annualRaise*100-100), '%');
   } 
}
