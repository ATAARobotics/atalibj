package edu.ATA.command;

/**
 * Static utility class used to add functionality to commands.
 *
 * @author joel
 */
public final class Commands {

    /**
     * Runs the command. Provides no functionality past that.
     *
     * @param command command to run
     */
    public static void run(Command command) {
        command.run();
    }

    /**
     * Runs the command in a different (new) thread. Does not wait for command
     * to finish.
     *
     * @param command command to run
     */
    public static void runInNewThread(Command command) {
        new Thread(command).start();
    }

    private Commands() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
