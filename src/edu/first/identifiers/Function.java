package edu.first.identifiers;

/**
 * General interface for classes that perform some kind of change to a number.
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public interface Function {

    /**
     * Transforms the number to a different number.
     *
     * @param in input number
     * @return output number
     */
    public double F(double in);
}
