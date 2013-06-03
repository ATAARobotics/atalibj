package edu.first.commands;

import edu.first.command.Command;
import edu.first.command.Commands;

/**
 * Command to give threading functionality to a command.
 *
 * @since May 26 13
 * @author Joel Gallant
 */
public final class ThreadableCommand implements Command {

    private final Command command;
    private final boolean newThread;

    /**
     * Constructs the command with the underlying command to run, and whether
     * the command should be run in a new thread.
     *
     * @param command command to run
     * @param newThread if command should run in a new thread
     */
    public ThreadableCommand(Command command, boolean newThread) {
        this.command = command;
        this.newThread = newThread;
    }

    /**
     * Runs the command in the current thread or a new one, depending on option
     * given in the constructor.
     */
    public final void run() {
        if (newThread) {
            Commands.runInNewThread(command);
        } else {
            Commands.run(command);
        }
    }
}
