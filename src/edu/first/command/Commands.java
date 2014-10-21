package edu.first.command;

import edu.first.util.log.Logger;

/**
 * Static utility class used to add functionality to commands.
 *
 * @since May 26 13
 * @author Joel Gallant
 */
public final class Commands {

    /**
     * Runs the command.
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

    /**
     * Runs the command in a different (new) thread. Waits until the thread is
     * completed running.
     *
     * <p>
     * If the thread is interrupted while running, this method will finish and
     * return the exception.
     *
     * @param command command to run
     * @return exception if one occurred while waiting (otherwise null)
     */
    public static Exception runInNewThreadAndWait(Command command) {
        Thread t = new Thread(command);
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Commands.class).error("Waiting for new thread interrupted", ex);
            return ex;
        }
        return null;
    }

    // cannot be subclassed or instantiated
    private Commands() throws IllegalAccessException {
        throw new IllegalAccessException();
    }
}
