package edu.ATA.commands;

import edu.first.commands.CommandGroup;
import edu.first.module.sensor.EncoderModule;
import edu.first.module.target.PIDModule;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class DriveDistance extends CommandGroup {

    public DriveDistance(EncoderModule encoderModule, PIDModule pid, double setpoint) {
        addSequential(new ResetEncoderCommand(encoderModule, false));
        addSequential(new MoveToSetpoint(pid, setpoint, false));
    }
}
