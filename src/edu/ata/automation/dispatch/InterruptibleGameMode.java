package edu.ata.automation.dispatch;

/**
 * Game mode that can be interrupted. Does not implicitly tell anything to stop
 * when it is interrupted. It is simply for the purpose of being able to tell
 * the mode that it should stop, and having the mode be able to understand and
 * react to that.
 *
 * @see GameMode
 * @author Joel Gallant
 */
public abstract class InterruptibleGameMode extends GameMode {

    /**
     * Whether the game mode is interrupted. Is volatile to keep thread safe.
     */
    private volatile boolean interrupted = false;
    private final Object lock = new Object();

    /**
     * Creates the game mode with a name. Priority is set to
     * {@link Thread#NORM_PRIORITY}.
     *
     * @param name name of the thread
     */
    public InterruptibleGameMode(String name) {
        super(name);
    }

    /**
     * Creates the game mode with a name and its priority.
     *
     * @param name name of the thread
     * @param priority priority of the thread (1 - 10)
     */
    public InterruptibleGameMode(String name, int priority) {
        super(name, priority);
    }

    /**
     * Gets whether or not the mode has been interrupted. Should be used to
     * check whether the mode is finished when it is possible to stop the mode.
     *
     * @return thread is interrupted
     */
    protected final boolean isInterrupted() {
        return interrupted;
    }

    /**
     * Starts the game mode. Is run within a new thread.
     */
    public abstract void open();

    /**
     * Stops the game mode. Is run when {@link InterruptableGameMode#stop()} is
     * called, and only after {@link InterruptibleGameMode#run()} finishes.
     *
     * <p> There is a 10 second timeout when {@code run()} can tell
     * {@code stop()} that it can run, and after 10 seconds, regardless of
     * whether {@code run()} is finished, {@code stop()} will be run.
     */
    public abstract void close();

    public final void run() {
        interrupted = false;
        open();
        synchronized (lock) {
            // Tells stop that it can run.
            lock.notifyAll();
        }
    }

    public final void stop() {
        interrupted = true;
        synchronized (lock) {
            try {
                // Waits until running is complete. Waits a maximum of 10 seconds.
                lock.wait(10000);
            } catch (InterruptedException ex) {
            }
        }
        close();
    }
}