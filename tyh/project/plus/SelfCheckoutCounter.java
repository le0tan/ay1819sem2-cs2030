package cs2030.simulator;

public class SelfCheckoutCounter extends Server {

    /**
     * Constructor for <code>SelfCheckoutCounter</code>.
     * @param queueLength length of waiting queue
     * @param restingProbability the probability of resting
     */
    public SelfCheckoutCounter(int queueLength, double restingProbability) {
        super(queueLength, restingProbability);
        this.needRest = false;
    }

}