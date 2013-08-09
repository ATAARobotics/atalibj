package edu.gordian.elements.methods;

import edu.gordian.values.Value;

/**
 * The representation of a runnable method.
 *
 * @author Joel Gallant
 */
public interface MethodBase {

    /**
     * Runs the method with the given arguments. The array's length is the same
     * as the amount of given arguments.
     *
     * @param arguments all arguments given (empty if none)
     */
    public void run(Value[] arguments);
}
