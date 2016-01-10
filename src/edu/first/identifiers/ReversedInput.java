package edu.first.identifiers;

/**
 * Returns the reversed value of an input.
 *
 * @since June 17 13
 * @author Joel Gallant
 */
public class ReversedInput implements Input {

    private final Input input;

    /**
     * Constructs the object using the actual input to get.
     *
     * @throws NullPointerException when input is null
     * @param input the input to return the reverse of
     */
    public ReversedInput(Input input) {
        if (input == null) {
            throw new NullPointerException("Null input given");
        }
        this.input = input;
    }

    /**
     * Returns the equivalent of {@code -input}.
     *
     * @return reversed value
     */
    @Override
    public double get() {
        return -input.get();
    }
}
