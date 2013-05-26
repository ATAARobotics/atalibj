package edu.first.commands;

import edu.first.command.Command;

/**
 * Abstract command to give threading functionality to the command. Only one
 * "session" in the command object can be run at one time, and other requests to
 * run it are ignored.
 *
 * @since May 26 13
 * @author Joel Gallant
 */
public abstract class ThreadableCommand implements Command {

    private final boolean newThread;
    private boolean running;

    /**
     * Constructs the command with the option to run in a new thread.
     *
     * @param newThread if command should run in a new thread
     */
    protected ThreadableCommand(boolean newThread) {
        this.newThread = newThread;
    }

    /**
     * Returns the main running {@link Runnable} object.
     *
     * @return runnable to run when command is run
     */
    public abstract Runnable getRunnable();

    /**
     * Runs the command in the current thread or a new one, depending on option
     * given in the constructor.
     */
    public final void run() {
        if (!running) {
            running = true;
            if (newThread) {
                new Thread(getRunnable()).start();
            } else {
                getRunnable().run();
            }
            running = false;
        }
    }
}
