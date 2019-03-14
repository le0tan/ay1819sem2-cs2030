package cs2030.simulator;

import java.util.Comparator;

/**
 * EventComparator
 */
public class EventComparator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        if (o1.getTime() != o2.getTime()) {
            return (o1.getTime() - o2.getTime()) < 0 ? -1 : 1;
        } else {
            if (o1.getCustomerID() - o2.getCustomerID() == 0) {
                return o1.getEventType() - o2.getEventType();
            } else {
                return o1.getCustomerID() - o2.getCustomerID();
            }
        }
    }
    
}