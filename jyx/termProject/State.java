import java.lang.String;

/**
 * An enum class representing the state.
 */
public enum State {
    /**
     * ARRIVES state.
     * A customer arrives.
     */
    ARRIVES, 
    /**
     * SERVED state.
     * A customer is being served by a server.
     */
    SERVED, 
    /**
     * LEAVES state.
     * A customer leaves without being served.
     */
    LEAVES, 
    /**
     * DONE state.
     * The service for a customer is done.
     */
    DONE, 
    /** 
     * WAITS state.
     * A customer is waiting to be served by some server.
     */
    WAITS;
  
    /**
     * Returns a string representation of the type.
     * The enum type name is converted to lower case to represent the type.
     *
     * @return a string representation of the type.
     */
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
