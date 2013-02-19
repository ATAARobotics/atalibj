package edu.ATA.commands;

import edu.first.module.target.PIDModule;
import edu.first.utils.DriverstationInfo;
import edu.wpi.first.wpilibj.Timer;

/**
 * The command class to move the PID to a setpoint and set the percentage
 * tolerance.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class MoveToSetpoint extends ThreadableCommand {

    private final PIDModule pid;
    private final double setpoint;

    public MoveToSetpoint(PIDModule pid, double setpoint, boolean newThread) {
        super(newThread);
        this.pid = pid;
        this.setpoint = setpoint;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                pid.enable();
                pid.setSetpoint(setpoint);
                String mode = DriverstationInfo.getGamePeriod();
                while (!pid.onTarget() && mode.equals(DriverstationInfo.getGamePeriod())) {
                    Timer.delay(0.02);
                }
                pid.disable();
            }
        };
    }
}
