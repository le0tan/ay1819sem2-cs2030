/**                                                                              
 * Encapsulates the state information of a Self-Check Counter object. It inherits 
 * from <code>Server</code>. It has almost the same attributes with 
 * <code>Server</code> except that it overrides <code>toString()</code> method.
 *                                                                               
 * @author Zhang Xiaoyu                                                          
 */

package cs2030.simulator;

public class SelfcheckCounter extends Server {

    public SelfcheckCounter(int id) {
        super(id);
    }

    /**
     * Represents the human server as "self-check" and its id.
     *
     * @return a string representation of this server's information
     */
    @Override
    public String toString() {
        return "self-check " + id;
    }
}

