package edu.ata.driving.modules;

import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Module that drives the robot. Uses the {@link RobotDrive} object to control.
 *
 * @author Joel Gallant
 */
public class RobotDriveModule extends Module implements Module.Disableable {

    private final RobotDrive robotDrive;
    private boolean enabled = false;

    /**
     * Creates the module using a name and the {@link RobotDrive} object.
     *
     * @param name name of the module shown to user
     * @param robotDrive robot drive to use
     */
    public RobotDriveModule(String name, RobotDrive robotDrive) {
        super(name);
        this.robotDrive = robotDrive;
    }

    /**
     * Creates the module using a name and 2 motors. Used for 2 wheel drive.
     *
     * @param name name of the module shown to user
     * @param left left speed controller
     * @param right right speed controller
     */
    public RobotDriveModule(String name, SpeedControllerModule left, SpeedControllerModule right) {
        this(name, new RobotDrive(left.getSpeedController(), right.getSpeedController()));
    }

    /**
     * Creates the module using a name and 4 motors. Used for 4 wheel drive.
     *
     * @param name name of the module shown to the user
     * @param left front left speed controller
     * @param right front right speed controller
     * @param leftBack back left speed controller
     * @param rightBack back right speed controller
     */
    public RobotDriveModule(String name, SpeedControllerModule left, SpeedControllerModule right,
            SpeedControllerModule leftBack, SpeedControllerModule rightBack) {
        this(name, new RobotDrive(left.getSpeedController(), leftBack.getSpeedController(),
                right.getSpeedController(), rightBack.getSpeedController()));
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Returns the {@link RobotDrive} object being used underneath this class.
     *
     * <p> <i> Will throw an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()}) </i>
     *
     * @return robot drive being used
     */
    public RobotDrive getRobotDrive() {
        if (isEnabled()) {
            return robotDrive;
        } else {
            throw new IllegalStateException("Robot Drive being accessed is not enabled - " + getName());
        }
    }

    /**
     * Drive the motors at "speed" and "curve".
     *
     * The speed and curve are -1.0 to +1.0 values where 0.0 represents stopped
     * and not turning. The algorithm for adding in the direction attempts to
     * provide a constant turn radius for differing speeds.
     *
     * This function will most likely be used in an autonomous routine.
     *
     * <p> <i> Will throw an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()}) </i>
     *
     * @see RobotDrive#drive(double, double)
     * @param speed speed of the robot (-1 to +1)
     * @param curve curve to apply to path (-1 = Left to +1 = Right)
     */
    public void drive(double speed, double curve) {
        getRobotDrive().drive(speed, curve);
    }

    /**
     * Drive the motors using "speed" and "rotation".
     *
     * <p> <i> Will throw an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()}) </i>
     *
     * @see RobotDrive#arcadeDrive(double, double)
     * @param speed speed of the robot (-1 to +1)
     * @param turn rotation / turn of the robot (-1 = Left to +1 = Right)
     */
    public void arcadeDrive(double speed, double turn) {
        getRobotDrive().arcadeDrive(speed, turn);
    }

    /**
     * Drive the motors using different speeds for the right and left side.
     *
     * <p> <i> Will throw an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()}) </i>
     *
     * @see RobotDrive#tankDrive(double, double)
     * @param left speed of the left motor / motors
     * @param right speed of the right motor / motors
     */
    public void tankDrive(double left, double right) {
        getRobotDrive().tankDrive(left, right);
    }

    /**
     * Stops all of the motors.
     *
     * <p> <i> Will throw an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()}) </i>
     */
    public void brake() {
        getRobotDrive().stopMotor();
    }
}