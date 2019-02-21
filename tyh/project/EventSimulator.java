import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * EventSimulator
 */
public class EventSimulator {
    public static final int SERVING = 1;
    public static final int WAITING = 2;
    public static final int IDOL = 0;
    public static final double DURATION_OF_SERVICE = 1.0;
    private double nextTimeOfService;
    private int systemStatus;
    List<Event> eventQueue;

    public EventSimulator() {
        eventQueue = new LinkedList<>();
        systemStatus = IDOL;
        nextTimeOfService = 0.0;
    }

    public void addEvent(Event event) {
        eventQueue.add(event);
        Collections.sort(eventQueue);
        // System.out.println(event);
    }

    public boolean getNextEvent() {
        if(eventQueue.isEmpty()) {
            return false;
        } else {
            Event ev = eventQueue.get(0);
            eventQueue.remove(0);
            System.out.printf("%.3f %d ", ev.getTime(), ev.getID());
            String status = new String();

            switch (ev.getType()) {
                case Event.ARRIVES:
                    status = "arrives";
                    if(systemStatus == IDOL) {
                        addEvent(new Event(ev.getTime(), ev.getID(), Event.SERVED));
                        systemStatus = SERVING;
                    } else if (systemStatus == SERVING) {
                        addEvent(new Event(ev.getTime(), ev.getID(), Event.WAITS));
                        systemStatus = WAITING;
                    } else if (systemStatus == WAITING) {
                        addEvent(new Event(ev.getTime(), ev.getID(), Event.LEAVES));
                    }
                    break;

                case Event.SERVED:
                    status = "served";
                    nextTimeOfService = ev.getTime() + DURATION_OF_SERVICE;
                    addEvent(new Event(nextTimeOfService, ev.getID(), Event.DONE));
                    break;

                case Event.LEAVES:
                    status = "leaves";
                    break;

                case Event.DONE:
                    status = "done";
                    systemStatus = IDOL;
                    break;

                case Event.WAITS:
                    status = "waits";
                    addEvent(new Event(nextTimeOfService, ev.getID(), Event.SERVED));
                    break;
            
                default:
                    break;
            }

            System.out.println(status);
            // printQueue();
            // System.out.println();

            return true;
        }
    }

    public void printQueue() {
        Iterator<Event> iter = eventQueue.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}