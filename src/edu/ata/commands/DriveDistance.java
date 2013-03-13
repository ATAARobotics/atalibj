package edu.ata.commands;

import edu.first.module.target.MovingModule;

/**
 * Command to drive to a distance using PID.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class DriveDistance extends ThreadableCommand {

    private final MovingModule movingModule;
    private final double distance;

    /**
     * Constructs the command using the moving module and the setpoint.
     *
     * @param movingModule module to move with
     * @param distance encoder value to go to
     * @param newThread whether command should be run in a new thread
     */
    public DriveDistance(MovingModule movingModule, double distance, boolean newThread) {
        super(newThread);
        this.movingModule = movingModule;
        this.distance = distance;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                movingModule.moveForwards(distance);
            }
        };
    }
}
