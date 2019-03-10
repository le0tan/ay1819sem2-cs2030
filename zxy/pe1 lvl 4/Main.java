import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<SavingsAccount> accounts  = new ArrayList<>();
        SalaryAdjust adjustment = new Type1Adjustment();

        /* read number of months */
        int months = sc.nextInt();
        sc.nextLine();

        /* included to read the list of banks */
        Bank.BANKS = new Bank[4];
        for (int i = 0; i < Bank.BANKS.length; i++) {
            Bank.BANKS[i] = new Bank(sc.next(), sc.nextDouble());
        }

        while (sc.hasNext()) {
            Employee employee = new Employee(sc.next(), sc.next(), 
                    sc.nextDouble(), adjustment);
            employee.setSalaryIncrease(sc.nextDouble());
            SavingsAccount account = new SavingsAccount(employee,    // added
                    Bank.getBankByName(sc.next()));
            accounts.add(account);
     account.compute(months);
        }
 Collections.sort(accounts);
 for(SavingsAccount a : accounts){
            System.out.println(a.employee + " " + "has balance of " +  // modified 
                    String.format("%.2f", a.balance)); 
 }
    }
}

