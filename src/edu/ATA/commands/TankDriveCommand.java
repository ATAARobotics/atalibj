package edu.ATA.commands;

import edu.ATA.twolf.subsystems.ShiftingDrivetrain;

/**
 *
 * @author denis
 */
public final class TankDriveCommand extends ThreadableCommand {

    private final ShiftingDrivetrain drive;
    private final double left;
    private final double right;

    public TankDriveCommand(ShiftingDrivetrain drive, double left, double right, boolean newThread) {
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
