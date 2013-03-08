package edu.ata.commands;

import edu.first.module.driving.RobotDriveModule;

/**
 * Command to arcade drive.
 *
 * @author denis
 */
public class ArcadeDriveCommand extends ThreadableCommand {

    private final RobotDriveModule drive;
    private final double forward;
    private final double turn;

    /**
     * Constructs the command with the drivetrain and values.
     * 
     * @param drive drivetrain to move with
     * @param forward forward value
     * @param turn turning value
     * @param newThread if command should be run in a new thread
     */
    public ArcadeDriveCommand(RobotDriveModule drive, double forward, double turn, boolean newThread) {
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
