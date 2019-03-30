package cs2030.simulator;

import java.util.Comparator;

public class ResultComparator implements Comparator<Result> {

    /**
     * Compares two events.
     * @param o1 o1 two events o1, o2 to be compared
     * @return negative if o1 is smaller than o2, 
     *     zero if o1 is equal to o2, 
     *     positive if o1 is greater than o2
     */
    @Override
    public int compare(Result o1, Result o2) {
        if (o1.getTime() != o2.getTime()) {
            return (o1.getTime() - o2.getTime()) < 0 ? -1 : 1;
        } else {
            if (o1.isCustomerEvent() && o2.isCustomerEvent()) {
                if (o1.getCustomerID() - o2.getCustomerID() == 0) {
                    return o1.getEventType() - o2.getEventType();
                } else {
                    return o1.getCustomerID() - o2.getCustomerID();
                }
            } else {
                if (o1.isCustomerEvent()) {
                    if (o2.getEventType() == Server.SERVER_REST) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    if (o1.getEventType() == Server.SERVER_REST) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        }
    }
    
}