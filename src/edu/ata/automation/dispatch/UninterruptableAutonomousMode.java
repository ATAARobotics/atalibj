package edu.ata.automation.dispatch;

import edu.ata.automation.autonomous.Autonomous;
import edu.ata.user.Logger;

/**
 * Similar to {@link AutonomousMode}, but cannot be 'interrupted'. This is
 * useful in the case of autonomous mode that have the possibility of going over
 * the time limit, but should just continue going until it is done.
 *
 * @author Joel Gallant
 */
public abstract class UninterruptableAutonomousMode extends GameMode implements Autonomous {

    private boolean finished = false;
    private final Object lock = new Object();

    /**
     * Creates the autonomous mode with its name.
     *
     * @param name name of the autonomous mode
     */
    public UninterruptableAutonomousMode(String name) {
        super(name, 7);
    }

    /**
     * Returns whether the mode is finished running.
     *
     * @return whether it is finished.
     */
    public final boolean isFinished() {
        return finished;
    }

    /**
     * Method to be run when autonomous mode is run. Handles it's inner methods.
     */
    public final void run() {
        finished = false;
        init();
        main();
        close();
        finished = true;
        synchronized (lock) {
            lock.notify();
        }
    }

    /**
     * Waits until the autonomous mode is over. Does not ever timeout. (Could
     * never finish)
     *
     * <p> Is equivalent to calling
     * {@code UninterruptableAutonomousMode.stop(0)}.
     *
     * @see Object#wait()
     */
    public void stop() {
        stop(0);
    }

    /**
     * Waits until the autonomous mode is over. Timeouts after the timeout
     * given. Should only wait if the mode is currently running
     * ({@link UninterruptableAutonomousMode#finished}).
     *
     * @param timeout how long to wait
     * @see UninterruptableAutonomousMode#stop()
     * @see Object#wait(long)
     */
    public void stop(long timeout) {
        if (!finished) {
            synchronized (lock) {
                try {
                    lock.wait(timeout);
                } catch (InterruptedException ex) {
                    Logger.log(Logger.Urgency.WARNING, ex.getMessage());
                }
            }
        }
    }
}