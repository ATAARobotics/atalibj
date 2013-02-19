package edu.ATA.commands;

import edu.first.command.Command;
import edu.first.module.target.PIDModule;
import edu.first.utils.DriverstationInfo;
import edu.wpi.first.wpilibj.Timer;

/**
 * The command class to move the PID to a setpoint and set the percentage
 * tolerance.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class MoveToSetpoint implements Command {

    private final PIDModule pid;
    private final double setpoint;

    /**
     * Sets the PID setpoint and percentage tolerance,
     *
     * @param pid the PID object used
     * @param setpoint the setpoint for the PID
     */
    public MoveToSetpoint(PIDModule pid, double setpoint) {
        this.pid = pid;
        this.setpoint = setpoint;
    }

    public void run() {
        pid.enable();
        pid.setSetpoint(setpoint);
        String mode = DriverstationInfo.getGamePeriod();
        while (!pid.onTarget() && mode.equals(DriverstationInfo.getGamePeriod())) {
            Timer.delay(0.02);
        }
        pid.disable();
    }
}
