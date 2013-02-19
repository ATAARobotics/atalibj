package edu.ATA.commands;

import edu.ATA.twolf.subsystems.ShiftingDrivetrain;
import edu.first.commands.CommandGroup;
import edu.first.module.sensor.GyroModule;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class Turn extends CommandGroup {

    public Turn(GyroModule gyro, ShiftingDrivetrain drivetrain, double left, double right, double setpoint) {
        addSequential(new ResetAngleCommand(gyro, false));
        addSequential(new TurnToAngle(left, right, setpoint, gyro, drivetrain, false));
    }
}
