package edu.ata.automation.dispatch;

/**
 * {@link GameMode} object designed for teleoperated modes.
 *
 * @author Joel Gallant
 */
public abstract class TeleoperatedMode extends InterruptableGameMode {

    /**
     * Method called during teleoperated period in a loop. Is called constantly
     * during teleoperated period.
     *
     * <p> It is recommended that you keep this method short, because
     * interruptions only occur after this method finishes.
     */
    public abstract void loop();

    /**
     * Creates the mode with a name.
     *
     * @param name name of the teleoperated mode
     */
    public TeleoperatedMode(String name) {
        super(name, 8);
    }

    /**
     * Method to be run when teleoperated mode is started. Handles it's inner
     * methods.
     */
    public void open() {
        while (!isInterrupted()) {
            loop();
            try {
                // Keeps loop from deadlocking (Does not always work, but stands in the way)
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }
        }
    }
}