/**
 * Question
 */
public abstract class Question {

    String question;
    char answer;

    abstract void getAnswer() throws InvalidQuestionException;
    char askQuestion() {
        System.out.print(question + " ");
        return (new Scanner(System.in)).next().charAt(0);
    }
}