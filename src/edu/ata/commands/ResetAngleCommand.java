package edu.ata.commands;

import edu.first.module.sensor.GyroModule;
import edu.wpi.first.wpilibj.Timer;

/**
 * Command to reset the gyro value. Waits until the FPGA is truly zeroed. (<1)
 *
 * @author denis
 * @author Joel Gallant
 */
public class ResetAngleCommand extends ThreadableCommand {

    private final GyroModule gyro;

    /**
     * Constructs the command with the gyro to reset.
     *
     * @param gyro gyro to reset
     * @param newThread if command should run in a new thread
     */
    public ResetAngleCommand(GyroModule gyro, boolean newThread) {
        super(newThread);
        this.gyro = gyro;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                gyro.reset();
                while (gyro.getAngle() > 1) {
                    Timer.delay(0.02);
                }
            }
        };
    }
}
