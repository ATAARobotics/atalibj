package edu.ata.commands;

import edu.first.module.target.PIDModule;
import edu.first.utils.DriverstationInfo;
import edu.wpi.first.wpilibj.Timer;

/**
 * Command to move the PID to a setpoint. Enables the PID while running, and
 * disables it immediately after.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class MoveToSetpoint extends ThreadableCommand {

    private final PIDModule pid;
    private final double setpoint;

    /**
     * Constructs the command using the PID module and a setpoint.
     *
     * @param pid PID to move
     * @param setpoint setpoint to go to
     * @param newThread if command should run in a new thread
     */
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
