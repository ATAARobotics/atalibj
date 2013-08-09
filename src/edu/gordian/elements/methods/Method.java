package edu.gordian.elements.methods;

import edu.gordian.values.Value;

/**
 * The method representation constructed to run the {@link MethodBase}.
 *
 * @author Joel Gallant
 */
public final class Method implements Runnable {

    private final MethodBase base;
    private final Value[] args;

    /**
     * Constructs the full method.
     *
     * @param base method to run
     * @param args arguments to provide
     */
    public Method(MethodBase base, Value[] args) {
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
     * Runs the method.
     */
    public void run() {
        base.run(args);
    }
}
