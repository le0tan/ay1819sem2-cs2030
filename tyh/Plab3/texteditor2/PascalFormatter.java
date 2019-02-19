class PascalFormatter extends BaseFormatter {
    private String rawString;
    private String formattedString;
    
    public TextFormatter clone(String s) {
        return new PascalFormatter(s);
    }

    public String format() throws NullPointerException {
        try {
            StringBuilder sb = new StringBuilder();
            boolean firstWord = true;
            for(int i=0;i<rawString.length();i++) {
                Character cur = rawString.charAt(i);
                if(firstWord) {
                    sb.append(Character.toUpperCase(cur));
                    firstWord = false;
                } else if(cur.equals(' ')) {
                    firstWord = true;
                } else {
                    sb.append(Character.toLowerCase(cur));
                }
            }
            formattedString = sb.toString();
            return formattedString;
        } catch (NullPointerException e) {
            throw e;
        }
    }

    PascalFormatter (String s) {
        rawString = s;
    }
}
