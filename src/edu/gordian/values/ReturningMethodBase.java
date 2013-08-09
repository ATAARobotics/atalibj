package edu.gordian.values;

/**
 * The base for methods that return a value instead of just completing.
 *
 * @author Joel Gallant
 */
public interface ReturningMethodBase {

    /**
     * Runs the method, and returns a value.
     *
     * @param arguments the arguments provided to the method
     * @return value that was returned
     */
    public Object runFor(Value[] arguments);
}
