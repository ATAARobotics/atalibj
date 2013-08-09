package edu.first.commands.common;

import edu.first.command.Command;

/**
 * Runs the given command a given amount of times.
 *
 * @since June 17 13
 * @author Joel Gallant
 */
public class RepeatingCommand implements Command {

    private final int t;
    private final Command command;

    /**
     * Constructs the command using how many times to run it.
     *
     * @param t how many times the command should run
     * @param command command to run {@code t} amount of times
     */
    public RepeatingCommand(int t, Command command) {
        this.t = t;
        this.command = command;
    }

    /**
     * Runs the command the given amount of times.
     */
    public void run() {
        for (int x = 0; x < t; x++) {
            command.run();
        }
    }
}
