import java.util.*;
public class SnakeFormatter implements TextFormatter {
    private String rawString;
    private String formattedString;
    
    public String format() throws NullPointerException {
        try {
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<rawString.length();i++) {
                Character cur = rawString.charAt(i);
                if(Character.isUpperCase(cur)) {
                    sb.append(Character.toLowerCase(cur));
                } else if(cur.equals(' ')) {
                    sb.append("_");
                } else {
                    sb.append(cur);
                }
            }
            return sb.toString();
        } catch (NullPointerException e) {
            throw e;
        }
    }

    SnakeFormatter(String s) {
        rawString = s;
    }

    public void setString(String r) {
        rawString = r;
    }
}
