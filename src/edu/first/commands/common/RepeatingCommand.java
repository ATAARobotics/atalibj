package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.identifiers.Input;
import edu.first.identifiers.StaticInput;

/**
 * Runs the given command a given amount of times.
 *
 * @since June 17 13
 * @author Joel Gallant
 */
public class RepeatingCommand implements Command {

    private final Input t;
    private final Command command;

    /**
     * Constructs the command using how many times to run it.
     *
     * @param t how many times the command should run
     * @param command command to run {@code t} amount of times
     */
    public RepeatingCommand(int t, Command command) {
        this.t = new StaticInput(t);
        this.command = command;
    }

    /**
     * Constructs the command using how many times to run it. Checks input at
     * <b>start</b> of execution, and converts to int using lossy conversion.
     *
     * @param t how many times the command should run
     * @param command command to run {@code t} amount of times
     */
    public RepeatingCommand(Input t, Command command) {
        this.t = t;
        this.command = command;
    }

    /**
     * Runs the command the given amount of times.
     */
    @Override
    public void run() {
        int times = (int) t.get();
        for (int x = 0; x < times; x++) {
            command.run();
        }
    }
}
