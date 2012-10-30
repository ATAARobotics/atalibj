package edu.ata.automation.dispatch;

/**
 * Game mode that can be interrupted.
 *
 * @author joel
 */
public abstract class InterruptableGameMode extends GameMode {

    /**
     * Whether the game mode is interrupted. Is volatile to keep thread safe.
     */
    private volatile boolean interrupted = false;

    /**
     * Creates the game mode with a name and it's runnable. Priority is set to
     * {@link Thread#NORM_PRIORITY}.
     *
     * @param name name of the thread
     */
    public InterruptableGameMode(String name) {
        super(name);
    }

    /**
     * Creates the game mode with a name, priority and it's runnable.
     *
     * @param name name of the thread
     * @param priority priority of the thread (1 - 10)
     */
    public InterruptableGameMode(String name, int priority) {
        super(name, priority);
    }

    /**
     * Gets whether or not the mode has been interrupted.
     *
     * @return thread is interrupted
     */
    protected final boolean isInterrupted() {
        return interrupted;
    }

    public final void stop() {
        interrupted = true;
    }
}
