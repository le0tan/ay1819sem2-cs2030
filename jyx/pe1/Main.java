import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        SalaryAdjust adjustment = new Type1Adjustment();

        int months = sc.nextInt();

        Bank.BANKS = new Bank[4];
        ArrayList<SavingsAccount> account = new ArrayList<>();
        for(int i = 0; i < Bank.BANKS.length;i++) {
            Bank.BANKS[i] = new Bank(sc.next(), sc.nextDouble());
        }
        while (sc.hasNext()) {
            Employee employee = new Employee(sc.next(), sc.next(), sc.nextDouble(), adjustment);
            employee.setSalaryIncrease(sc.nextDouble());

            SavingsAccount sa = new SavingsAccount(employee,
                        Bank.getBankByName(sc.next()));
            sa.compute(months);
            account.add(sa);
        }
        Collections.sort(account);
        for(SavingsAccount sa: account) {
            System.out.println(sa.getEmployee() + " " + "has balance of " + 
                        String.format("%.2f",sa.getBalance()));
        }
    }
}
