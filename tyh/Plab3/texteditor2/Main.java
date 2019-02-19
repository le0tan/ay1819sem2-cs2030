import java.util.*;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<TextFormatter> formatters = new ArrayList<TextFormatter>();
        formatters.add(new SnakeFormatter(""));
        formatters.add(new KebabFormatter(""));
        formatters.add(new PascalFormatter(""));
        TextEditor editor = new TextEditor(formatters);
        while(sc.hasNext()) {
            editor.addString(sc.nextLine());
        }
        editor.printAll();
    }
}
