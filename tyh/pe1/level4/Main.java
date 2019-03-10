import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SalaryAdjust adjustment = new Type1Adjustment();
        int months = sc.nextInt();
        Bank.BANKS = new Bank[4];
        for(int i=0;i<Bank.BANKS.length;i++) {
            Bank.BANKS[i]=new Bank(sc.next(), sc.nextDouble());
        }
        List<Answer> answers = new ArrayList<>();
        AnswerComparator comp = new AnswerComparator();
        while (sc.hasNext()) {
            Employee employee = new Employee(sc.next(), sc.next(), sc.nextDouble(),adjustment);
            employee.setSalaryIncrease(sc.nextDouble());
            SavingsAccount account = new SavingsAccount(employee,Bank.getBankByName(sc.next()));
            answers.add(new Answer(employee, account.compute(months)));
            //System.out.println(employee + " " + "has balance of " +String.format("%.2f", account.compute(months)));
        }
        Collections.sort(answers, comp);
        for(Answer a: answers) {
            System.out.println(a);
        }
    }
}
