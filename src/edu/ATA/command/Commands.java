package edu.ATA.command;

/**
 * Utility class used to manipulate all {@link Command commands}.
 *
 * @see Command
 * @author Joel Gallant
 */
public final class Commands {

    // cannot be subclassed or instantiated
    private Commands() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    /**
     * Converts the command into a {@link Runnable} that simply runs the
     * command.
     *
     * @param command command to run in the runnable
     * @return a runnable instance that will run the command
     */
    public static Runnable convertToRunnable(final Command command) {
        if (command == null) {
            throw new NullPointerException();
        }
        return new RunnableCommand(command);
    }

    /**
     * Returns a thread that runs the command inside.
     *
     * @param command command to run in the thread
     * @return thread that runs the command
     */
    public static Thread convertToThread(final Command command) {
        if (command == null) {
            throw new NullPointerException();
        }
        return new Thread(convertToRunnable(command));
    }

    /**
     * Runs the command inside of a new thread.
     *
     * @param command command to run in new thread
     */
    public static void runInNewThread(final Command command) {
        if (command == null) {
            throw new NullPointerException();
        }
        convertToThread(command).start();
    }

    /**
     * Runs all of the commands in the array at the same time. All of the
     * threads are started in the order of the array index, but do not finish at
     * defined times.
     *
     * @param commands commands to run concurrently
     */
    public static void runConcurrently(final Command[] commands) {
        if (commands == null) {
            throw new NullPointerException();
        }
        Thread[] threads = new Thread[commands.length];
        for (int x = 0; x < threads.length; x++) {
            threads[x] = new Thread(convertToRunnable(commands[x]));
        }
        for (int x = 0; x < threads.length; x++) {
            threads[x].start();
        }
        for (int x = 0; x < threads.length; x++) {
            try {
                threads[x].join();
            } catch (InterruptedException ex) {
            }
        }
    }

    private static final class RunnableCommand implements Runnable {

        private final Command command;

        private RunnableCommand(Command command) {
            if(command == null) {
                throw new NullPointerException();
            }
            this.command = command;
        }

        public void run() {
            command.runCommand();
        }
    }
}
