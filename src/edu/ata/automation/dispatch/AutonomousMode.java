package edu.ata.automation.dispatch;

/**
 * {@link GameMode} object designed for autonomous modes. <b>Can only be run
 * once by convention.</b>
 *
 * <p> Create new object to restart mode.
 *
 * @author joel
 */
public abstract class AutonomousMode extends InterruptableGameMode {

    /**
     * Whether the autonomous mode is finished. Is only true after being run and
     * closed.
     */
    public boolean finished = false;

    /**
     * Initializes the mode.
     */
    public abstract void init();

    /**
     * Runs the mode.
     */
    public abstract void main();

    /**
     * Closes the mode.
     */
    public abstract void close();

    /**
     * Creates the autonomous mode with a name.
     *
     * @param name name of the autonomous mode
     */
    public AutonomousMode(String name) {
        super(name, 7);
    }

    /**
     * Returns whether the mode is finished.
     *
     * @return whether it is finished.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Method to be run when autonomous mode is run. Handles it's inner methods.
     */
    public void run() {
        init();
        if (!interrupted) {
            main();
        }
        close();
        finished = true;
    }
}