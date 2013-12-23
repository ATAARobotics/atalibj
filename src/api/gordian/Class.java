package api.gordian;

/**
 * The basic representation of classes inside of Gordian. A class constructs
 * objects which can be used as separate entities inside of a program.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public interface Class extends Object {

    /**
     * Constructs a unique instance of this class using arguments.
     *
     * @param arguments the arguments used to construct the new instance
     * @return a unique instance made by the class
     */
    public Object contruct(Arguments arguments);

    /**
     * Returns a list of the acceptable constructors available for usage.
     *
     * Construction with arguments that don't fit one of these signatures will
     * fail.
     *
     * @return ways to construct instances
     */
    public Signature[] contructors();

    /**
     * Returns whether the class is the same or a child of this one.
     *
     * @param object class to test
     * @return if {@code object} is this class, or a child of it
     */
    public boolean equals(Object object);
}
