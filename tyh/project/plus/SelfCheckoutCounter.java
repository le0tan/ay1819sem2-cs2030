package cs2030.simulator;

import java.util.function.Supplier;

/**
 * A special type of server that doesn't need rest.
 * 
 * <p>It has a special constructor that sets <code>needRest</code>
 * field to be false so that it doesn't rest.
 * 
 * @author Tan Yuanhong
 */

public class SelfCheckoutCounter extends Server {

    /**
     * Constructor for <code>SelfCheckoutCounter</code>.
     * 
     * @param queueLength           length of waiting queue
     * @param restingProbability    the probability of resting
     * @param randomRestSupplier    supplier for random rest time
     * @param restPeriodSupplier    supplier for random rest period
     */
    public SelfCheckoutCounter(int queueLength, double restingProbability,
        Supplier<Double> randomRestSupplier,
        Supplier<Double> restPeriodSupplier) {
        super(queueLength, restingProbability,
              randomRestSupplier,
              restPeriodSupplier);
        this.needRest = false;
    }

}