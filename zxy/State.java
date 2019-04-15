/**
  * Possible states for different events.
  * There are seven possible event <code>State</code>: (1)<code>ARRIVES</code>:
  * a customer arrives; (2)<code>SERVED</code>: the customer is being served; 
  * (3)<code>WAITS</code>: the customer waits; (4)<code>LEAVES</code>: the 
  * customer leaves; (5)<code>DONE</code>: the service is done; 
  * (6)<code>SERVER_REST</code>: the server is resting; 
  * (7)<code>SERVER_BACK</code> the server comes back to serve.
  */

package cs2030.simulator;

public enum State {
    ARRIVES,
    SERVED,
    WAITS,
    LEAVES,
    DONE,
    SERVER_REST,
    SERVER_BACK
}


