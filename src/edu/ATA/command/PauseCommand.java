package edu.ATA.command;

import edu.wpi.first.wpilibj.Timer;

/**
 * Command that waits for a certain amount of time. Uses
 * {@link Timer#delay(double)}.
 *
 * @see Timer#delay(double)
 * @author Joel Gallant
 */
public class PauseCommand implements Command {

    private final double pauseLength;

    /**
     * Constructs the command with a specific amount of time to wait.
     *
     * @param pauseLength time to wait (seconds)
     */
    public PauseCommand(double pauseLength) {
        this.pauseLength = pauseLength;
    }

    /**
     * Waits for a specific amount of time given in the constructor using
     * {@link Timer#delay(double)}.
     */
    public void runCommand() {
        Timer.delay(pauseLength);
    }
}
