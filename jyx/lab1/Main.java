import java.util.*;

public class Main {
    public static void main(String[] args){
        String str;
        Scanner in = new Scanner(System.in);
        List<TextFormatter> list = new ArrayList<>();
        list.add(new SnakeCase(""));
        list.add(new KebabCase(""));
        list.add(new PascalCase(""));
        list.add(new MixedFormatter(""));
        TextEditor editor = new TextEditor(list);
        while(in.hasNextLine()){
            str=in.nextLine(); 
            editor.addString(str);
        }
        editor.printAll();
    }
}

