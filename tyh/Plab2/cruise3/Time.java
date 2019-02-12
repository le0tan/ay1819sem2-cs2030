public class Time {
    public int hour;
    public int minute;
    Time(int h, int m) {
        hour = h; minute = m;
    }
    public Time plus(Time t) {
        int th = 0;
        int tm = 0;
        if(minute + t.minute >= 60) {
            tm = minute + t.minute - 60;
            th += 1;
        } else {
            tm = minute + t.minute;
        }
        if(th + hour + t.hour >= 24) {
            th = (th+hour+t.hour)-24;
        } else {
            th = th+hour+t.hour;
        }
        return new Time(th, tm);
    }
    public boolean laterThan(Time t) {
        if(hour > t.hour) {
            return true;
        } else if(hour == t.hour && minute >= t.minute) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        String hrs = Integer.toString(hour);
        while(hrs.length()<2)
            hrs = "0"+hrs;
        String mins = Integer.toString(minute);
        while(mins.length()<2)
            mins = "0"+mins;
        return hrs+mins;
    }
}
