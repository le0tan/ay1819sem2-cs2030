class SnakeFormatter extends BaseFormatter {
    private String rawString;
    private String formattedString;

    public TextFormatter clone(String s) {
        return new SnakeFormatter(s);
    }

    public String format() throws NullPointerException {
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < rawString.length(); i++) {
                Character cur = rawString.charAt(i);
                if (Character.isUpperCase(cur)) {
                    sb.append(Character.toLowerCase(cur));
                } else if (cur.equals(' ')) {
                    sb.append("_");
                } else {
                    sb.append(cur);
                }
            }
            formattedString = sb.toString();
            return formattedString;
        } catch (NullPointerException e) {
            throw e;
        }
    }

    SnakeFormatter(String s) {
        rawString = s;
    }
}
