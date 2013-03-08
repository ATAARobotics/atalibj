package edu.ata.commands;

import edu.first.commands.CommandGroup;
import edu.first.module.sensor.EncoderModule;
import edu.first.module.target.PIDModule;

/**
 * Command to drive to a distance using PID.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class DriveDistance extends CommandGroup {

    /**
     * Constructs the command using the encoder and PID being used to move.
     *
     * @param encoderModule encoder measuring distance
     * @param pid system used to go to distances
     * @param setpoint distance to move to
     */
    public DriveDistance(EncoderModule encoderModule, PIDModule pid, double setpoint) {
        addSequential(new ResetEncoderCommand(encoderModule, false));
        addSequential(new MoveToSetpoint(pid, setpoint, false));
    }
}
