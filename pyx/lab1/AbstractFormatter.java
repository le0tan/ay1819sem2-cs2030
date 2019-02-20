import java.util.*;

public abstract class AbstractFormatter implements TextFormatter{
    protected String str;
    public Integer sum(){
        String formattedString = this.format();
        int s=0;
        for(int i=0;i<formattedString.length();i++)
            s+=formattedString.charAt(i);
        return s;
    }

    public int compareTo(TextFormatter formatter) {
        return this.sum().compareTo(formatter.sum());
    }
}


