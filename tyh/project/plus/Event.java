package cs2030.simulator;


/**
 * Created for <code>CustomerEvent</code> and <code>ServerEvent</code> 
 * so that we can store and compare them in a homogeneous array.
 * 
 * <p>Note that all implementations of <code>Event</code> should be 
 * immutable. For such purpose, all instance fields of this class 
 * are <code>final</code>.
 * 
 * <p>It specifies two basic methods of an event: (1) <code>getTime</code>, 
 * (2) <code>getType</code> whose purposes are self-explanatory.
 * 
 * @author Tan Yuanhong
 */

public abstract class Event {

    protected final double time;
    protected final EventType type;

    public Event(double time, EventType type) {
        this.time = time;
        this.type = type;
    }

    /**
     * Getter for the time field.
     * 
     * @return a double representing time of the event
     */
    public abstract double getTime();


    /**
     * Getter for the type of event.
     * 
     * @return an EventType representing the type of event.
     */
    public abstract EventType getType();
    
}