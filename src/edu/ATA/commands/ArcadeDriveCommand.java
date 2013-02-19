package edu.ATA.commands;

import edu.ATA.twolf.subsystems.ShiftingDrivetrain;

/**
 *
 * @author denis
 */
public class ArcadeDriveCommand extends ThreadableCommand {

    private final ShiftingDrivetrain drive;
    private final double forward;
    private final double turn;

    public ArcadeDriveCommand(ShiftingDrivetrain drive, double forward, double turn, boolean newThread) {
        super(newThread);
        this.drive = drive;
        this.forward = forward;
        this.turn = turn;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                drive.arcadeDrive(forward, turn);
            }
        };
    }
}
