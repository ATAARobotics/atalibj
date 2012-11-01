package edu.ata.automation.dispatch;

/**
 * Game mode that can be interrupted. Does not implicitly tell anything to stop
 * when it is interrupted. It is simply for the purpose of being able to get out
 * of deadlocks that last beyond the correct amount of time.
 *
 * @author Joel Gallant
 */
public abstract class InterruptableGameMode extends GameMode {

    /**
     * Whether the game mode is interrupted. Is volatile to keep thread safe.
     */
    private volatile boolean interrupted = false;

    /**
     * Creates the game mode with a name. Priority is set to
     * {@link Thread#NORM_PRIORITY}.
     *
     * @param name name of the thread
     */
    public InterruptableGameMode(String name) {
        super(name);
    }

    /**
     * Creates the game mode with a name and its priority.
     *
     * @param name name of the thread
     * @param priority priority of the thread (1 - 10)
     */
    public InterruptableGameMode(String name, int priority) {
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
     * called.
     */
    public abstract void close();

    public void run() {
        interrupted = false;
        open();
    }

    public final void stop() {
        interrupted = true;
        close();
    }
}