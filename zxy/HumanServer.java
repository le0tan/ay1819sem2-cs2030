/**                                                                              
 * Encapsulates the state information of a Human Server object. It inherits 
 * from <code>Server</code>. It has almost the same attributes with              
 * <code>Server</code> except that it overrides <code>toString()</code> method.
 * 
 * @author Zhang Xiaoyu                                                          
 */

package cs2030.simulator;

public class HumanServer extends Server {

    public HumanServer(int id) {
        super(id);
    }

    /**
     * Represents the human server as "server" and its id.
     *
     * @return a string representation of this server's information
     */
    @Override
    public String toString() {
        return "server " + id;
    }
}

