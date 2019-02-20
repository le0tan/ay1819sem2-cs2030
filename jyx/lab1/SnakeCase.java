import java.util.*;

public class SnakeCase extends AbstractFormatter {
    public SnakeCase(String str) {
        this.str=str;
    }
    public TextFormatter clone(String s){
        return new SnakeCase(s);
    }

    public String format(){
        str=str.toLowerCase();
        StringBuilder strb=new StringBuilder();
        int prev=0;
        int cur=str.indexOf(' ');
        while(cur!=-1){
            strb.append(str.substring(prev, cur));
            strb.append('_');
            prev=++cur;
            cur=str.indexOf(' ',prev);
        }
        strb.append(str.substring(prev));
        return strb.toString();
    }
}
