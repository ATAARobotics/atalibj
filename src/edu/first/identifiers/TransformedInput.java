package edu.first.identifiers;

/**
 * An input that abstracts a function on top of it to change input.
 *
 * @since June 13 13
 * @author Joel Gallant
 */
public final class TransformedInput implements Input {

    private final Input input;
    private final Function function;

    /**
     * Constructs the input using the composed input and function to apply.
     *
     * @param input input to derive from
     * @param function function to apply to input
     */
    public TransformedInput(Input input, Function function) {
        this.input = input;
        this.function = function;
    }

    /**
     * Returns the input, transformed by the function.
     *
     * @return transformed input
     */
    @Override
    public double get() {
        return function.F(input.get());
    }
}
