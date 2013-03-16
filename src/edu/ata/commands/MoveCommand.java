package edu.ata.commands;

import edu.first.commands.CommandGroup;
import edu.first.module.target.MovingModule;

/**
 * Command to move the robot to a position.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class MoveCommand extends CommandGroup {

    /**
     *
     * @param movingModule
     * @param forwards
     * @param right
     */
    public MoveCommand(MovingModule movingModule, double forwards, double right) {
        double firstAngle;
        double secondAngle;

        if ((forwards > 0 && right > 0) || (forwards < 0 && right < 0)) {
            firstAngle = 90;
            secondAngle = -90;
        } else {
            // ((forwards > 0 && right < 0) || (forwards < 0 && right > 0)) || right == 0 || forwards == 0
            firstAngle = -90;
            secondAngle = 90;
        }

        addSequential(new DriveDistance(movingModule, forwards / 2.0, false));
        addSequential(new TurnToAngle(movingModule, firstAngle, false));
        addSequential(new DriveDistance(movingModule, right, false));
        addSequential(new TurnToAngle(movingModule, secondAngle, false));
        addSequential(new DriveDistance(movingModule, forwards / 2.0, false));
    }
}