package edu.ata.automation.dispatch;

/**
 * Basic template for autonomous modes. has no structure, and allows the user to
 * run whatever they want with any interruption method they see fit.
 *
 * @author Joel Gallant
 */
public abstract class AutonomousMode extends GameMode {

    /**
     * Creates the autonomous mode with a name. Sets its thread priority to 8
     * (Very high).
     *
     * @param name name of the mode
     */
    public AutonomousMode(String name) {
        super(name, 8);
    }
}
