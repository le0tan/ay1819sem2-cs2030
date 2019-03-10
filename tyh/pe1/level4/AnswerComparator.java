import java.util.*;
public class AnswerComparator implements Comparator<Answer> {
    public int compare(Answer a, Answer b) {
        double res = a.balance - b.balance;
        return res>0 ? 1: res<0 ? -1 : 0;
    }
}
