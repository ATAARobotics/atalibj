package edu.first.identifiers;

import edu.first.util.MathUtils;

/**
 * Input that is rounded to the nearest integer.
 *
 * @since Oct 21 14
 * @author Joel Gallant
 */
public class RoundedInput implements Input {

    private final Input input;

    /**
     * Constructs the input using the value to use.
     *
     * @param input value that will be returned
     */
    public RoundedInput(Input input) {
        this.input = input;
    }

    /**
     * Returns the input rounded to the nearest integer.
     *
     * @return rounded value
     */
    public double get() {
        return MathUtils.round(input.get());
    }
}
