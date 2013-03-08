package edu.ata.commands;

import edu.first.module.driving.RobotDriveModule;
import edu.first.module.sensor.GyroModule;
import edu.first.utils.DriverstationInfo;
import edu.wpi.first.wpilibj.Timer;

/**
 * Command to turn the robot to a certain angle using a gyro and drivetrain.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public final class TurnToAngle extends ThreadableCommand {

    private final double left, right;
    private final double setpoint;
    private final GyroModule gyro;
    private final RobotDriveModule drivetrain;

    /**
     * Constructs the command using the values and components to move it.
     *
     * @param left left speed
     * @param right right speed
     * @param setpoint angle setpoint
     * @param gyro gyroscope object
     * @param drivetrain drivetrain to turn with
     * @param newThread if command should run in a new thread
     */
    public TurnToAngle(double left, double right, double setpoint, GyroModule gyro, RobotDriveModule drivetrain, boolean newThread) {
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
                while (gyro.getAngle() > 1) {
                    Timer.delay(0.02);
                }
                final double prev = gyro.getAngle();
                final String mode = DriverstationInfo.getGamePeriod();
                while (Math.abs(gyro.getAngle() - prev) < Math.abs(setpoint) && mode.equals(DriverstationInfo.getGamePeriod())) {
                    drivetrain.tankDrive(left, right);
                    Timer.delay(0.02);
                }
                drivetrain.stopMotors();
            }
        };
    }
}
