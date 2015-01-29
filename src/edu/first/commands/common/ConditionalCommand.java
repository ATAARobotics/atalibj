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
    private final Command command, none;

    /**
     * Constructs the command using the condition ({@code run}) that will
     * determine if {@code command} will run.
     *
     * @throws NullPointerException when position or command are null
     * @param run position to check to see if command should run
     * @param command command to run if position is true
     */
    public ConditionalCommand(Position run, Command command) {
        this(run, command, null);
    }

    /**
     * Constructs the command using the condition ({@code run}) that will
     * determine if {@code command} will run.
     *
     * @throws NullPointerException when position or command are null
     * @param run position to check to see if command should run
     * @param command command to run if position is true
     * @param none command to run if position is false
     */
    public ConditionalCommand(Position run, Command command, Command none) {
        if (run == null) {
            throw new NullPointerException("Null position given");
        } else if (command == null) {
            throw new NullPointerException("Null command given");
        }
        this.run = run;
        this.command = command;
        this.none = none;
    }

    /**
     * Runs the command if the condition returns true.
     */
    @Override
    public void run() {
        if (run.getPosition()) {
            command.run();
        } else if (none != null) {
            none.run();
        }
    }
}
