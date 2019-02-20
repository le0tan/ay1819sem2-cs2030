import java.util.*;

public class KebabCase extends AbstractFormatter {
    
    public KebabCase(String str) {
        this.str=str;
    }
    public TextFormatter clone(String s){
        return new KebabCase(s);
    }
    public String format(){
        str=str.toLowerCase();
        StringBuilder strb=new StringBuilder();
        int prev=0;
        int cur=str.indexOf(' ');
        while(cur!=-1){
            strb.append(str.substring(prev, cur));
            strb.append('-');
            prev=++cur;
            cur=str.indexOf(' ',prev);
        }
        strb.append(str.substring(prev));
        return strb.toString();
    }
}

