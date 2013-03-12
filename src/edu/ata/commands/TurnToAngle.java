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

    private final double setpoint;
    private final double maxSpeed;
    private final GyroModule gyro;
    private final RobotDriveModule drive;

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
    public TurnToAngle(double maxSpeed, double setpoint, GyroModule gyro,
            RobotDriveModule drivetrain, boolean newThread) {
        super(newThread);
        this.gyro = gyro;
        this.drive = drivetrain;
        this.setpoint = setpoint;
        this.maxSpeed = maxSpeed;
    }

    public final Runnable getRunnable() {
        return new Runnable() {
            public void run() {
                final String mode = DriverstationInfo.getGamePeriod();
                final double start = gyro.getAngle();
                while (Math.abs(gyro.getAngle() - start) < Math.abs(setpoint) && DriverstationInfo.getGamePeriod().equals(mode)) {
                    drive.tankDrive((setpoint > 0 ? 1 : -1) * maxSpeed, (setpoint > 0 ? -1 : 1) * maxSpeed);
                    Timer.delay(0.02);
                }
                while (Math.abs(gyro.getAngle() - start) > Math.abs(setpoint - 3) && DriverstationInfo.getGamePeriod().equals(mode)) {
                    drive.tankDrive((setpoint > 0 ? -1 : 1) * maxSpeed, (setpoint > 0 ? 1 : -1) * maxSpeed);
                    Timer.delay(0.02);
                }
                while (Math.abs(gyro.getAngle() - start) < Math.abs(setpoint - 3) && DriverstationInfo.getGamePeriod().equals(mode)) {
                    drive.tankDrive((setpoint > 0 ? 1 : -1) * maxSpeed, (setpoint > 0 ? -1 : 1) * maxSpeed);
                    Timer.delay(0.02);
                }
                drive.stopMotors();
            }
        };
    }
}
