/**
 * Event
 */
public class Event implements Comparable<Event> {

    public static final int ARRIVES = 1;
    public static final int SERVED = 2;
    public static final int LEAVES = 3;
    public static final int WAITS = 4;
    public static final int DONE = 0;
    private double time;
    private int customerID;
    private int type;

    public Event(double time, int customerid, int type) {
        this.time = time;
        this.customerID = customerid;
        this.type = type;
        // System.out.println(type);
    }

    public double getTime() {
        return time;
    }

    public int getID() {
        return customerID;
    }

    public int getType() {
        return type;
    }

    @Override
    public int compareTo(Event o) {
        if(this.time > o.time) {
            return 1;
        } else if(this.time == o.time) {
            if(this.type > o.type) {
                return 1;
            } else if(this.type < o.type){
                return -1;
            } else {
                return 0;
            }
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        String status = new String();

        switch (getType()) {
            case Event.ARRIVES:
                status = "arrives";
                break;

            case Event.SERVED:
                status = "served";
                break;

            case Event.LEAVES:
                status = "leaves";
                break;
            
            case Event.DONE:
                status = "done";
                break;

            case Event.WAITS:
                status = "waits";
                break;
        
            default:
                break;
        }

        return String.format("%.3f %d %s", time, customerID, status);
    }
}