package edu.ATA.commands;

import edu.ATA.twolf.subsystems.ShiftingDrivetrain;
import edu.first.module.sensor.GyroModule;
import edu.first.utils.DriverstationInfo;
import edu.first.utils.Logger;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class TurnToAngle extends ThreadableCommand {

    private final double left, right;
    private final double setpoint;
    private final GyroModule gyro;
    private final ShiftingDrivetrain drivetrain;

    public TurnToAngle(double left, double right, double setpoint, GyroModule gyro, ShiftingDrivetrain drivetrain, boolean newThread) {
        super(newThread);
        this.left = left;
        this.right = right;
        this.setpoint = setpoint;
        this.gyro = gyro;
        this.drivetrain = drivetrain;
    }

    public Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                gyro.reset();
                while(gyro.getAngle() > 1) {
                    Timer.delay(0.02);
                }
                final double prev = gyro.getAngle();
                final String mode = DriverstationInfo.getGamePeriod();
                while (Math.abs(gyro.getAngle() - prev) < Math.abs(setpoint) && mode.equals(DriverstationInfo.getGamePeriod())) {
                    drivetrain.tankDrive(left, right);
                    Timer.delay(0.02);
                }
                drivetrain.stop();
            }
        };
    }
}
