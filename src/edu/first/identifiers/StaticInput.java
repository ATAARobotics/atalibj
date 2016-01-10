package edu.first.identifiers;

/**
 * Never changing input that will always return the same thing.
 *
 * @since June 13 13
 * @author Joel Gallant
 */
public final class StaticInput implements Input {

    private final double input;

    /**
     * Constructs the input using the value to use.
     *
     * @param input value that will always be returned
     */
    public StaticInput(double input) {
        this.input = input;
    }

    /**
     * Returns the input given in the constructor.
     *
     * @return value that will always be used
     */
    @Override
    public double get() {
        return input;
    }
}
