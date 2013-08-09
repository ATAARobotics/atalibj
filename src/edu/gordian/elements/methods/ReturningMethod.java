package edu.gordian.elements.methods;

import edu.gordian.values.ReturningMethodBase;
import edu.gordian.values.Value;

/**
 * The method representation constructed to run the {@link ReturningMethodBase}.
 * This is meant for direct running of returning method, without it giving a
 * value.
 *
 * @author Joel Gallant
 */
public final class ReturningMethod implements Runnable {

    private final ReturningMethodBase base;
    private final Value[] args;

    /**
     * Constructs the returning method.
     *
     * @param base method to run
     * @param args arguments to provide
     */
    public ReturningMethod(ReturningMethodBase base, Value[] args) {
        if (base == null) {
            throw new NullPointerException("Base given was null");
        }
        if (args == null) {
            throw new NullPointerException("Args given was null");
        }
        this.base = base;
        this.args = args;
    }

    /**
     * Runs the returning method.
     */
    public void run() {
        base.runFor(args);
    }
}
