public interface TextFormatter extends Comparable<TextFormatter> {
    public TextFormatter clone(String s);
    public String format();
    public Integer sum();
}
