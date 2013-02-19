
package edu.ATA.commands;

import edu.ATA.twolf.subsystems.ShiftingDrivetrain;
import edu.first.command.Command;

/**
 *
 * @author denis
 */
public class ArcadeDriveCommand implements Command {

    private final ShiftingDrivetrain drive;
    private final double forward;
    private final double turn;

    public ArcadeDriveCommand(ShiftingDrivetrain drive, double forward, double turn) {
        this.drive = drive;
        this.forward = forward;
        this.turn = turn;
    }

    public void run() {
        drive.arcadeDrive(forward, turn);
    }
}
