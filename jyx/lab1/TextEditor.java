import java.util.*;

public class TextEditor{
    private List<TextFormatter> formatter;
    private Queue<TextFormatter> q = new PriorityQueue<>();
    
    public TextEditor(List<TextFormatter> formatter) {
        this.formatter=formatter;
    }

    public void printAll(){
        while(!q.isEmpty())
            System.out.println(q.poll().format());
    }

    public void addString(String str){
        for(TextFormatter f: formatter)
            q.add(f.clone(str));
    }
}
