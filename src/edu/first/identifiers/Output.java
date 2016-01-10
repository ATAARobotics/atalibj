package edu.first.identifiers;

/**
 * The general contract for classes that output to something. The uses of this
 * interface can vary a lot, which is done on purpose. To achieve the greatest
 * amount of flexibility, instead of specifying types as an output for
 * something, use this interface to specify that the class can be outputted to
 * with a number. This makes it possible to quickly change between things like
 * different kinds of speed controllers.
 *
 * <p>
 * <b> If the only function you are using from an object is {@code set(double)},
 * use this interface to reference it. </b> Otherwise just be as general in type
 * as possible.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public interface Output {

    /**
     * Changes the output to the designated value. In general, there should be
     * no delay between the time this method is called, and when the value is
     * actually set.
     *
     * <p>
     * It is possible that this method needs to be called frequently to have a
     * noticeable effect on the system, but is not always the case. Be sure to
     * fully document what the behavior of this method is.
     *
     * @param value output to change to
     * @throws IllegalStateException when value cannot be set
     */
    public void set(double value);
}
