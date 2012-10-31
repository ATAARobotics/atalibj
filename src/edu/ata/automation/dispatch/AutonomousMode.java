package edu.ata.automation.dispatch;

import edu.ata.automation.autonomous.Autonomous;

/**
 * {@link GameMode} object designed for autonomous modes.
 *
 * @author Joel Gallant
 */
public abstract class AutonomousMode extends InterruptableGameMode implements Autonomous {

    private boolean finished = false;

    /**
     * Creates the autonomous mode with a name.
     *
     * @param name name of the autonomous mode
     */
    public AutonomousMode(String name) {
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
        if (!isInterrupted()) {
            main();
        }
        close();
        finished = true;
    }
}