package edu.ATA.commands;

import edu.ATA.command.Command;
import edu.ATA.module.target.PIDModule;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class MoveToSetpoint implements Command {

    private final PIDModule pid;
    private final double setpoint;
    private final double percentageTolerance;

    public MoveToSetpoint(PIDModule pid, double setpoint, double percentageTolerance) {
        this.pid = pid;
        this.setpoint = setpoint;
        this.percentageTolerance = percentageTolerance;
    }

    public void run() {
        pid.setPercentTolerance(percentageTolerance);
        pid.setSetpoint(setpoint);
        while(!pid.onTarget()) {
            Timer.delay(0.02);
        }
    }
}
