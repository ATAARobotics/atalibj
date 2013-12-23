package api.gordian;

import api.gordian.storage.Methods;
import api.gordian.storage.Variables;

/**
 * The basic representation of all values in Gordian. Primitives, classes and
 * instances are all objects. Methods are not.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Object {

    public Methods methods();

    public Variables variables();

    /**
     * Whether the object is semantically "equal" to another. Typically, this
     * relates to value - do both objects have the same value?
     *
     * If calling {@link #equals(api.gordian.Object)} returns true one way, it
     * should always return true the opposite direction.
     *
     * {@code if x.equals(a) then a.equals(x)}
     *
     * @param object adjacent object to compare it to
     * @return if both objects are equal to one another
     */
    public boolean equals(Object object);

    /**
     * The class that this object inherited its traits from.
     *
     * @return class object that made this object
     */
    public Class parentClass();

    /**
     * Returns an easy to read formatted string with information about what this
     * instance is. Should usually uniquely identify this instance compared to
     * others.
     *
     * @return string representation
     */
    public String toString();
}
