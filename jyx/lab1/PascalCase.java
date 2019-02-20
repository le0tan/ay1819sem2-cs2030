import java.util.*;

public class PascalCase extends AbstractFormatter {
     public PascalCase(String str) {
        this.str=str;
    }
    public TextFormatter clone(String s){
        return new PascalCase(s);
    }
    public String format(){
        str=str.toLowerCase();
        StringBuilder strb=new StringBuilder();
        int prev=0;
        int cur=str.indexOf(' ');
        while(cur!=-1){
            strb.append(str.substring(prev,prev+1).toUpperCase());
            strb.append(str.substring(prev+1, cur));
            prev=++cur;
            cur=str.indexOf(' ',prev);
        }
        strb.append(str.substring(prev,prev+1).toUpperCase());
        strb.append(str.substring(prev+1));
        return strb.toString();
    }
}

