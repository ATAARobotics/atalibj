package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.identifiers.Position;

/**
 * Command that will run if the condition returns true.
 *
 * @since June 17 13
 * @author Joel Gallant
 */
public class ConditionalCommand implements Command {

    private final Position run;
    private final Command command;

    /**
     * Constructs the command using the condition ({@code run}) that will
     * determine if {@code command} will run.
     *
     * @param run position to check to see if command should run
     * @param command command to run if position is true
     */
    public ConditionalCommand(Position run, Command command) {
        if (run == null || command == null) {
            throw new NullPointerException();
        }
        this.run = run;
        this.command = command;
    }

    /**
     * Runs the command if the condition returns true.
     */
    public void run() {
        if (run.getPosition()) {
            command.run();
        }
    }
}
