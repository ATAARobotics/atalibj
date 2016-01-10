package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.identifiers.Function;
import edu.first.identifiers.Input;
import edu.first.identifiers.Output;

/**
 * Command that takes an input, adjusts it with a function, and sends the value
 * to an output.
 *
 * @since June 18 13
 * @author Joel Gallant
 */
public class Adjust implements Command {

    private final Input input;
    private final Output output;
    private final Function adjustment;

    /**
     * Constructs the command using the input and output.
     *
     * @param input input for original value
     * @param output output to send adjusted value
     * @param adjustment change to make to input
     */
    public Adjust(Input input, Output output, Function adjustment) {
        this.input = input;
        this.output = output;
        this.adjustment = adjustment;
    }

    /**
     * Sets the output to the adjusted value.
     */
    @Override
    public void run() {
        output.set(adjustment.F(input.get()));
    }
}
