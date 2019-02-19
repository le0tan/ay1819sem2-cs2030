class MixedFormatter extends BaseFormatter {
    private String rawString;
    private String formattedString;
    
    public TextFormatter clone(String s) {
        return new MixedFormatter(s);
    }

    public String format() throws NullPointerException {
        try {
            Character firstChar = rawString.charAt(0);
            int r = firstChar % 3;
            BaseFormatter s = null;
            BaseFormatter t = new SnakeFormatter("I Love CS2030");
            if(r == 0) {
                s = new SnakeFormatter(rawString);
            } else if(r == 1) {
                s = new KebabFormatter(rawString);
            } else if(r == 2) {
                s = new PascalFormatter(rawString);
            }

            if(s.compareTo(t) > 0) {
                formattedString = s.format();
            } else {
                formattedString = t.format();
            }
            return formattedString;
        } catch (NullPointerException e) {
            throw e;
        }
    }

    MixedFormatter (String s) {
        rawString = s;
    }
}