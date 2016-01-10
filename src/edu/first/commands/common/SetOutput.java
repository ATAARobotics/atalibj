package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.identifiers.Input;
import edu.first.identifiers.Output;
import edu.first.identifiers.StaticInput;

/**
 * Sets the output of an output object.
 *
 * @see Output#set(double)
 * @since June 13 13
 * @author Joel Gallant
 */
public final class SetOutput implements Command {

    private final Output output;
    private final Input input;

    /**
     * Constructs the command using the output to set and the input to get the
     * values from.
     *
     * @param output the output to set
     * @param input the input to get values from
     */
    public SetOutput(Output output, Input input) {
        this.output = output;
        this.input = input;
    }

    /**
     * Constructs the command using the output to set and the input.
     *
     * @param output the output to set
     * @param input the value to set
     */
    public SetOutput(Output output, double input) {
        this(output, new StaticInput(input));
    }

    /**
     * Sets the output to the given input.
     */
    @Override
    public void run() {
        output.set(input.get());
    }
}
