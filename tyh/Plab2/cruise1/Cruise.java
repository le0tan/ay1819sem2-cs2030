public class Cruise {
    String code;
    boolean isBig;
    String time;

    Cruise(String code, String time) {
        this.code = code;
        if(code.charAt(0) == 'B')
            isBig = true;
        else
            isBig = false;
        while(time.length() < 4) {
            time = "0" + time;
        }
        this.time = time;
    }

    @Override
    public String toString() {
        return code + "@" + time;
    }
}

