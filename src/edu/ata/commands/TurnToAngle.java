package edu.ata.commands;

import edu.first.module.target.MovingModule;

/**
 * Command to turn the robot to a certain angle using a gyro and drivetrain.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class TurnToAngle extends ThreadableCommand {

    private final double angle;
    private final MovingModule movingModule;

    /**
     * Constructs the command using the moving module and the angle to move to.
     *
     * @param movingModule module to move with
     * @param angle gyro value from current position to move to
     * @param newThread whether command should run in a new thread
     */
    public TurnToAngle(MovingModule movingModule, double angle, boolean newThread) {
        super(newThread);
        this.angle = angle;
        this.movingModule = movingModule;
    }

    public final Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                movingModule.turn(angle);
            }
        };
    }
}
