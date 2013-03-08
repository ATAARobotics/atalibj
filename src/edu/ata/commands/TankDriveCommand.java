package edu.ata.commands;

import edu.first.module.driving.RobotDriveModule;

/**
 * Command to tank drive.
 *
 * @author denis
 */
public final class TankDriveCommand extends ThreadableCommand {

    private final RobotDriveModule drive;
    private final double left;
    private final double right;

    /**
     * Constructs the command with the drivetrain and values.
     *
     * @param drive drivetrain to move with
     * @param left left speed
     * @param right right speed
     * @param newThread if command should run in a new thread
     */
    public TankDriveCommand(RobotDriveModule drive, double left, double right, boolean newThread) {
        super(newThread);
        this.drive = drive;
        this.left = left;
        this.right = right;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                drive.tankDrive(left, right);
            }
        };
    }
}
