public class Cruise {
    String code;
    boolean isBig;
    Time time;

    Cruise(String code, String time) {
        this.code = code;
        if(code.charAt(0) == 'B')
            isBig = true;
        else
            isBig = false;
        while(time.length() < 4) {
            time = "0" + time;
        }
        this.time = new Time(Integer.parseInt(time.substring(0,2)), Integer.parseInt(time.substring(2,4)));
    }

    @Override
    public String toString() {
        return code + "@" + time.toString();
    }
}

