package edu.first.commands;

import edu.first.command.Command;
import edu.first.command.Commands;

/**
 * Command to give threading functionality to a command.
 *
 * @since May 26 13
 * @author Joel Gallant
 */
public final class ThreadedCommand implements Command {

    private final Command command;

    /**
     * Constructs the command with the underlying command to run.
     *
     * @param command command to run
     */
    public ThreadedCommand(Command command) {
        this.command = command;
    }

    /**
     * Runs the command in a new thread.
     */
    @Override
    public final void run() {
        Commands.runInNewThread(command);
    }
}
