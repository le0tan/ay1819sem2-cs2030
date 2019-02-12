import java.util.*;
/**
 * TFQ
 */
public class TFQ extends Question {
    String question;
    char answer;

    public TFQ(String question) {
        this.question = question;
    }

    void getAnswer() throws InvalidTFQException{
        System.out.print(question + " ");
        answer = (new Scanner(System.in)).next().charAt(0);
        if (answer != 'T' && answer != 'F') {
            throw new InvalidTFQException("Invalid TFQ answer");
        }
    }

}