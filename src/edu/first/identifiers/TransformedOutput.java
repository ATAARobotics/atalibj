package edu.first.identifiers;

/**
 * An output that abstracts a function on top of it to change input before
 * getting to the output.
 *
 * @since June 13 13
 * @author Joel Gallant
 */
public final class TransformedOutput implements Output {

    private final Output output;
    private final Function function;

    /**
     * Constructs the output using the function to apply.
     *
     * @param output underlying output to send input to
     * @param function transforming function that changes input
     */
    public TransformedOutput(Output output, Function function) {
        this.output = output;
        this.function = function;
    }

    /**
     * Returns the transformed input.
     *
     * @param value original value changed by function
     */
    @Override
    public void set(double value) {
        output.set(function.F(value));
    }
}
