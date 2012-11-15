package edu.ata.commands;

/**
 * Static utility class used to manipulate {@link Command commands}.
 *
 * @author Joel Gallant
 */
public class Scheduler {

    /**
     * Runs the command within the same thread. Same as calling
     * {@code command.run()}.
     *
     * @param command command to run
     */
    public static void run(Command command) {
        command.run();
    }

    /**
     * Runs the command within a new thread. Is not finished when this method
     * is.
     *
     * @param command command to run
     */
    public static void runInThread(Command command) {
        new Thread(command).start();
    }

    /**
     * Runs the command within a new thread at the priority given. Is not
     * finished when this method is.
     *
     * @param command command to run
     * @param priority priority to run command at
     */
    public static void runInThread(Command command, int priority) {
        Thread t = new Thread(command);
        t.setPriority(priority);
        t.start();
    }

    /**
     * Runs the commands in the order of the index of the array within the same
     * thread.
     *
     * @param commands commands to run
     */
    public static void runSequencially(Command[] commands) {
        for (int x = 0; x < commands.length; ++x) {
            commands[x].run();
        }
    }

    /**
     * Runs the commands in the order of the index of the array within a new
     * thread.
     *
     * @param commands commands to run
     */
    public static void runSequenciallyInThread(final Command[] commands) {
        new Thread(new Runnable() {
            public void run() {
                runSequencially(commands);
            }
        }).start();
    }

    /**
     * Runs all the commands at the same time within the current thread.
     *
     * @param commands commands to run
     */
    public static void runConcurrently(Command[] commands) {
        Thread[] threads = new Thread[commands.length];
        for (int x = 0; x < commands.length; ++x) {
            threads[x] = new Thread(commands[x]);
            threads[x].start();
        }
        for (int x = 0; x < threads.length; ++x) {
            try {
                threads[x].join();
            } catch (InterruptedException ex) {
            }
        }
    }

    /**
     * Runs all the commands at the same time within a new thread.
     *
     * @param commands commands to run
     */
    public static void runConcurrentlyInThread(final Command[] commands) {
        new Thread(new Runnable() {
            public void run() {
                runConcurrently(commands);
            }
        }).start();
    }
}
