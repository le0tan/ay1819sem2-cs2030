/**
 * Question
 */
public abstract class Question {

    String question;
    char answer;

    abstract void getAnswer() throws InvalidQuestionException;
}