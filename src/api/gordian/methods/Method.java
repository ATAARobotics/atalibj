package api.gordian.methods;

import api.gordian.Arguments;
import api.gordian.Signature;
import api.gordian.Object;

/**
 * Basic representation of a stored method.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Method {

    /**
     * Returns the arguments acceptable for this method.
     *
     * @return signature of the arguments
     */
    public Signature signature();

    /**
     * Runs the method, with the given arguments.
     *
     * @param arguments values to change behavior of the method
     * @return the returned value
     */
    public Object run(Arguments arguments);
}
