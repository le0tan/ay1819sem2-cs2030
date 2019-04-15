/**
 * Compares two events according to their event happening times and then
 * customer Id. The event happening ealier or (when happening at the same
 * time) with a smaller customer Id will be put ahead during sorting. It is
 * not possible for two events happening at the same time and with same
 * customer Id.
 *
 */

package cs2030.simulator; 

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {

    /** Compares two events according to their event happening times and then
     * customer Id.
     * 
     * @param e1 a <code>Event</code> being compared
     * @param e2 a <code>Event</code> being compared
     * @return   a negative integer when this event happens earlier; or
     *           happening at the same time but this event has a smaller
     *           customer Id
     *           a postive integer when this event happens later; or
     *           happening at the same time but this event has a
     *           bigger customer Id
     */
    public int compare(Event e1, Event e2) {

        if (e1.getTime() > e2.getTime()) {
            return 1;
        } else if (e1.getTime() == e2.getTime()) {
            return e1.getCustomer().getId() - e2.getCustomer().getId();
        } else {
            return -1;
        }
    }
}

