package edu.gordian.values;

/**
 * The representation given to all values in Gordian. This abstraction allows
 * for values to be changed internally and for type to change.
 *
 * @author Joel Gallant
 */
public interface Value {

    /**
     * Returns the internal value. This should usually be a standard Java object
     * type.
     *
     * @return actual value of this object
     */
    public Object getValue();
}
