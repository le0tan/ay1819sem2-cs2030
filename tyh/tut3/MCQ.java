import java.util.*;
/**
 * MCQ
 */
public class MCQ extends Question {
    String question;
    char answer;

    public MCQ(String question) {
        this.question = question;
    }

    void getAnswer() throws InvalidMCQException{
        System.out.print(question + " ");
        answer = (new Scanner(System.in)).next().charAt(0);
        if (answer < 'A' || answer > 'E') {
            throw new InvalidMCQException("Invalid MCQ answer");
        }
    }
}