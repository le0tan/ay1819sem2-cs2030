package cs2030.simulator;

import java.util.Comparator;

/**
 * Comparator for two events so that they are processed in the right order.
 * 
 * <p>The order of comparison is:
 * (1) time of event
 * (2) if both are customers, first compare their IDs, when IDs are different, 
 * the one with a smaller ID comes first, otherwise returns the event with
 * higher priority (as defined in <code>Event</code> class) comes first.
 * (3) if one of which is a <code>ServerEvent</code>, SERVER_REST comes last.
 * 
 * @author Tan Yuanhong
 */

public class EventComparator implements Comparator<Event> {


    /**
     * Compares two events.
     * @param o1 o1 two events o1, o2 to be compared
     * @return negative if o1 is smaller than o2, 
     *         zero if o1 is equal to o2, 
     *         positive if o1 is greater than o2
     */
    @Override
    public int compare(Event o1, Event o2) {
        if (o1.getTime() != o2.getTime()) {
            return (o1.getTime() - o2.getTime()) < 0 ? -1 : 1;
        } else {
            if ((o1 instanceof CustomerEvent) && (o2 instanceof CustomerEvent)) {
                CustomerEvent c1 = (CustomerEvent) o1;
                CustomerEvent c2 = (CustomerEvent) o2;
                if (c1.getCustomer().getCustomerID() - c2.getCustomer().getCustomerID() == 0) {
                    return c1.getType().compareTo(c2.getType());
                } else {
                    return c1.getCustomer().getCustomerID() - c2.getCustomer().getCustomerID();
                }
            } else {
                if (o1 instanceof CustomerEvent) {
                    if (o2.getType() == EventType.SERVER_REST) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    if (o1.getType() == EventType.SERVER_REST) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        }
    }

}