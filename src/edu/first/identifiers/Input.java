package edu.first.identifiers;

/**
 * The general contract for classes which give back an input. The uses of this
 * interface can vary a lot, which is done on purpose. To achieve the greatest
 * amount of flexibility, instead of specifying types as an input for something,
 * use this interface to specify that the input returns a double value. This
 * makes it possible to quickly change between things like different kinds of
 * sensors.
 *
 * <p>
 * <b> If the only function you are using from an object is {@code get()}, use
 * this interface to reference it. </b> Otherwise just be as general in type as
 * possible.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public interface Input {

    /**
     * Returns the value of the input. This should always be the "current"
     * value, in whatever context it is in.
     *
     * @return current input
     * @throws IllegalStateException when input cannot be reached
     */
    public double get();
}
