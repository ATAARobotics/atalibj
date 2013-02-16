package edu.first.module.driving;

/**
 * Outline for objects that drive a robot. Acts as a stable form of
 * {@link edu.wpi.first.wpilibj.RobotDrive}, so that expectations of classes
 * implementing this interface do not change.
 *
 * @author Joel Gallant
 */
interface RobotDrive {

    /**
     * Drive the motors at "speed" and "curve".
     *
     * <p> The speed and curve are -1.0 to +1.0 values where 0.0 represents
     * stopped and not turning.
     *
     * <p> This function will most likely be used in an autonomous routine.
     *
     * @param outputMagnitude the forward component of the output magnitude to
     * send to the motors
     * @param curve the rate of turn, constant for different forward speeds
     */
    public void drive(double outputMagnitude, double curve);

    /**
     * Provides tank steering using left side and right side steering.
     *
     * @param leftValue the value of the left stick
     * @param rightValue the value of the right stick
     * @param squaredInputs whether to square the inputs
     */
    public void tankDrive(double leftValue, double rightValue, boolean squaredInputs);

    /**
     * Provides tank steering using left side and right side steering.
     *
     * @param leftValue the value of the left sides speed
     * @param rightValue the value of the right sides speed
     */
    public void tankDrive(double leftValue, double rightValue);

    /**
     * Drive the motors with forwards/backwards and left/right speed values.
     *
     * @param moveValue the value to use for forwards/backwards
     * @param rotateValue the value to use for the rotate right/left
     * @param squaredInputs whether to square the inputs
     */
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs);

    /**
     * Drive the motors with forwards/backwards and left/right speed values.
     *
     * @param moveValue the value to use for forwards/backwards
     * @param rotateValue the value to use for the rotate right/left
     */
    public void arcadeDrive(double moveValue, double rotateValue);

    /**
     * Sets the forwards/backwards speed. The turn value should stay the same as
     * set previously.
     *
     * @param moveValue the value to use for forwards/backwards
     */
    public void setForwardValue(double moveValue);

    /**
     * Sets the forwards/backwards speed. The turn value should stay the same as
     * set previously.
     *
     * @param moveValue the value to use for forwards/backwards
     * @param squaredInputs if set, decreases the sensitivity at low speeds
     */
    public void setForwardValue(double moveValue, boolean squaredInput);

    /**
     * Sets the forwards/backwards speed. The forward value should stay the same as
     * set previously.
     *
     * @param rotateValue the value to use for the rotate right/left
     */
    public void setRotateValue(double rotateValue);

    /**
     * Sets the forwards/backwards speed. The forward value should stay the same as
     * set previously.
     *
     * @param rotateValue the value to use for the rotate right/left
     * @param squaredInputs if set, decreases the sensitivity at low speeds
     */
    public void setRotateValue(double rotateValue, boolean squaredInput);

    /**
     * A method for driving with Mecanum wheeled robots.
     *
     * @param x the speed that the robot should drive in the X direction.
     * @param y the speed that the robot should drive in the Y direction
     * @param rotation the rate of rotation for the robot that is completely
     * independent of the translation
     * @param gyroAngle the current angle reading from the gyro
     */
    public void mecanumDrive_Cartesian(double x, double y, double rotation, double gyroAngle);

    /**
     * A method for driving with Mecanum wheeled robots.
     *
     * @param magnitude the speed that the robot should drive in a given
     * direction
     * @param direction the direction the robot should drive in degrees
     * @param rotation the rate of rotation for the robot that is completely
     * independent of the magnitude or direction
     */
    public void mecanumDrive_Polar(double magnitude, double direction, double rotation);

    /**
     * Set the speed of the right and left motors.
     *
     * @param leftOutput the speed to send to the left side of the robot
     * @param rightOutput the speed to send to the right side of the robot
     */
    public void setLeftRightMotorOutputs(double leftOutput, double rightOutput);

    /**
     * Set the speed of the left side motors. Other motors should remain at the
     * same speed.
     *
     * @param leftOutput the speed to send to the left side of the robot
     */
    public void setLeftMotorOutput(double leftOutput);

    /**
     * Set the speed of the right side motors. Other motors should remain at the
     * same speed.
     *
     * @param rightOutput the speed to send to the right side of the robot
     */
    public void setRightMotorOutput(double rightOutput);

    /**
     * Configure the scaling factor for using RobotDrive with motor controllers.
     *
     * @param maxOutput multiplied with the output percentage computed by the
     * drive functions
     */
    public void setMaxOutput(double maxOutput);

    /**
     * Set the expiration time for the corresponding motor safety object.
     *
     * @param timeout the timeout value in seconds
     */
    public void setExpiration(double timeout);

    /**
     * Retrieve the timeout value for the corresponding motor safety object.
     *
     * @return the timeout value in seconds
     */
    public double getExpiration();

    /**
     * Returns if the motor safety is currently enabled for this device.
     *
     * @return true if motor safety is enforced for this device
     */
    public boolean isSafetyEnabled();

    /**
     * Enable/disable motor safety for this device. Turn on and off the motor
     * safety option for this PWM object.
     *
     * @param enabled true if motor safety is enforced for this object
     */
    public void setSafetyEnabled(boolean enabled);

    /**
     * Stops all motors that are part of the robot drive.
     */
    public void stopMotors();
}
