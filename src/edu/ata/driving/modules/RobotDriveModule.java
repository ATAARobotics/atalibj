package edu.ata.driving.modules;

import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Module that drives the robot. Uses the {@link RobotDrive} object to control.
 *
 * @author Joel Gallant
 */
public class RobotDriveModule extends Module {

    private RobotDrive robotDrive;
    private SpeedControllerModule leftMotor, rightMotor;
    private SpeedControllerModule leftBackMotor, rightBackMotor;

    /**
     * Creates a 2 wheel drive robot drive.
     *
     * @param name name of the driving module
     * @param rightMotor motor on the right of the robot
     * @param leftMotor motor on the left of the robot
     */
    public RobotDriveModule(String name, SpeedControllerModule rightMotor,
            SpeedControllerModule leftMotor) {
        super(name);
        this.rightMotor = rightMotor;
        this.leftMotor = leftMotor;
    }

    /**
     * Creates a 4 wheel drive robot drive.
     *
     * @param name name of the driving module
     * @param rightMotor motor on the front right of the robot
     * @param leftMotor motor on the front left of the robot
     * @param leftBackMotor motor on the back right of the robot
     * @param rightBackMotor motor on the back left of the robot
     */
    public RobotDriveModule(String name, SpeedControllerModule leftMotor,
            SpeedControllerModule rightMotor, SpeedControllerModule leftBackMotor,
            SpeedControllerModule rightBackMotor) {
        super(name);
        this.leftMotor = leftMotor;
        this.rightMotor = rightMotor;
        this.leftBackMotor = leftBackMotor;
        this.rightBackMotor = rightBackMotor;
    }

    public void enable() {
        if (robotDrive == null) {
            if (leftBackMotor != null && rightBackMotor != null) {
                robotDrive = new RobotDrive(
                        leftMotor.getSpeedController(), leftBackMotor.getSpeedController(),
                        rightMotor.getSpeedController(), rightBackMotor.getSpeedController());
            } else {
                robotDrive = new RobotDrive(
                        leftMotor.getSpeedController(), rightMotor.getSpeedController());
            }
        }
    }

    public boolean isEnabled() {
        return robotDrive != null;
    }
}