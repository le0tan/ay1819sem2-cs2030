import java.util.*;
public class TextEditor {
    private List<TextFormatter> formatters;
    private StringBuilder sb;
    private List<String> formatted;
    public TextEditor(List<TextFormatter> formatter) {
        formatters = formatter;
        formatted = new ArrayList<String>();
        sb = new StringBuilder();
    }

    public void addString(String s) {
        for(TextFormatter f: formatters) {
            f.setString(s);
            formatted.add(f.format());
        }
    }

    public void printAll() {
        for(String s: formatted) {
            System.out.println(s);
        }
    }
}
