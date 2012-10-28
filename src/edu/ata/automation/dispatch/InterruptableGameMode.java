package edu.ata.automation.dispatch;

/**
 * Game mode that can be interrupted. <b>Can only be run once by convention.</b>
 *
 * <p> Create new object to restart mode.
 *
 * @author joel
 */
public abstract class InterruptableGameMode extends GameMode {

    /**
     * Whether the game mode is interrupted. Is volatile to keep thread safe.
     */
    protected volatile boolean interrupted = false;

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

    public final void stop() {
        interrupted = true;
    }
}
