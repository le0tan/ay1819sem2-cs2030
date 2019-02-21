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
        char answer = askQuestion();
        if (answer != 'T' && answer != 'F') {
            throw new InvalidTFQException("Invalid TFQ answer");
        }
    }

}