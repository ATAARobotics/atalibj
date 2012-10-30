package edu.ata.automation.dispatch;

/**
 * Similar to {@link AutonomousMode}, but cannot be 'interrupted'. This is
 * useful in the case of autonomous mode that have the possibility of going over
 * the time limit, but should just continue going until it is done.
 *
 * @author Joel Gallant
 */
public abstract class UninterruptableAutonomousMode extends GameMode implements Autonomous {

    private boolean finished = false;

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
    }
}
