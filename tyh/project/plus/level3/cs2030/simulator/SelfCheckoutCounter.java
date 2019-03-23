package cs2030.simulator;

/**
 * SelfCheckoutCounter
 */
public class SelfCheckoutCounter extends Server {

    public SelfCheckoutCounter(int queueLength, double restingProbability) {
        super(queueLength, restingProbability);
        this.needRest = false;
    }

}