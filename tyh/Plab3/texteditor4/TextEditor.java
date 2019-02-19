import java.util.*;
public class TextEditor {
    private List<TextFormatter> formatters;
    private StringBuilder sb;
    private PriorityQueue<TextFormatter> formatted;
    public TextEditor(List<TextFormatter> formatter) {
        formatters = formatter;
        formatted = new PriorityQueue<TextFormatter>();
        sb = new StringBuilder();
    }

    public void addString(String s) {
        for(TextFormatter f: formatters) {
            TextFormatter temp = f.clone(s);
            formatted.add(temp);
        }
    }

    public void printAll() {
        while(!formatted.isEmpty()) {
            System.out.println(formatted.poll().format());
        }
    }
}
