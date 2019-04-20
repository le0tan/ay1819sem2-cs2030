package cs2030.simulator;

/**
 * Defines the different event types involved 
 * in the simulation. The order of event types 
 * is important for event sorting.
 * 
 * @author Tan Yuanhong
 */

public enum EventType {
    SERVER_BACK,
    SERVER_REST,
    ARRIVES,
    DONE,
    LEAVES,
    WAITS,
    SERVED,
    BACK
}