package edu.ATA.commands;

import edu.first.command.Command;
import edu.first.module.target.PIDModule;
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
    private final double percentageTolerance;

    /**
     * Sets the PID setpoint and percentage tolerance,
     *
     * @param pid the PID object used
     * @param setpoint the setpoint for the PID
     * @param percentageTolerance the percentage tolerance for the PID
     */
    public MoveToSetpoint(PIDModule pid, double setpoint, double percentageTolerance) {
        this.pid = pid;
        this.setpoint = setpoint;
        this.percentageTolerance = percentageTolerance;
    }

    public void run() {
        pid.enable();
        pid.setPercentTolerance(percentageTolerance);
        pid.setSetpoint(setpoint);
        while (!pid.onTarget()) {
            Timer.delay(0.02);
        }
        pid.disable();
    }
}
