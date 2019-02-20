public class MixedFormatter extends AbstractFormatter {
    public MixedFormatter(String str) {
        this.str=str;
    }
    public TextFormatter clone(String s){
        return new MixedFormatter(s);
    }
    public String format(){
        TextFormatter s,t=new SnakeCase("I Love CS2030");
        switch (str.charAt(0)%3) {
            case 0: s=new SnakeCase(str);break;
            case 1: s=new KebabCase(str);break;
            default: s=new PascalCase(str);
        }
    
        if(s.compareTo(t)>0) return s.format(); else return t.format();    
    }
}

