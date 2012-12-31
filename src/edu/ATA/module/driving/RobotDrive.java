package edu.ATA.module.driving;

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
     * stopped and not turning. The algorithm for adding in the direction
     * attempts to provide a constant turn radius for differing speeds.
     *
     * <p> This function will most likely be used in an autonomous routine.
     *
     * @param outputMagnitude the forward component of the output magnitude to
     * send to the motors.
     * @param curve the rate of turn, constant for different forward speeds.
     */
    public void drive(double outputMagnitude, double curve);

    /**
     * Provide tank steering using the stored robot configuration. This function
     * lets you directly provide joystick values from any source.
     *
     * @param leftValue the value of the left stick.
     * @param rightValue the value of the right stick.
     * @param squaredInputs setting this parameter to true decreases the
     * sensitivity at lower speeds
     */
    public void tankDrive(double leftValue, double rightValue, boolean squaredInputs);

    /**
     * Provide tank steering using the stored robot configuration. This function
     * lets you directly provide joystick values from any source.
     *
     * @param leftValue the value of the left stick.
     * @param rightValue the value of the right stick.
     */
    public void tankDrive(double leftValue, double rightValue);

    /**
     * Arcade drive implements single stick driving. This function lets you
     * directly provide joystick values from any source.
     *
     * @param moveValue the value to use for forwards/backwards
     * @param rotateValue the value to use for the rotate right/left
     * @param squaredInputs if set, decreases the sensitivity at low speeds
     */
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs);

    /**
     * Arcade drive implements single stick driving. This function lets you
     * directly provide joystick values from any source.
     *
     * @param moveValue the value to use for forwards/backwards
     * @param rotateValue the value to use for the rotate right/left
     */
    public void arcadeDrive(double moveValue, double rotateValue);

    /**
     * A method for driving with Mecanum wheeled robots. There are 4 wheels on
     * the robot, arranged so that the front and back wheels are toed in 45
     * degrees. When looking at the wheels from the top, the roller axles should
     * form an X across the robot.
     *
     * <p> This is designed to be directly driven by joystick axes.
     *
     * @param x the speed that the robot should drive in the X direction.
     * [-1.0..1.0]
     * @param y the speed that the robot should drive in the Y direction. This
     * input is inverted to match the forward == -1.0 that joysticks produce.
     * [-1.0..1.0]
     * @param rotation the rate of rotation for the robot that is completely
     * independent of the translation. [-1.0..1.0]
     * @param gyroAngle the current angle reading from the gyro. Use this to
     * implement field-oriented controls.
     */
    public void mecanumDrive_Cartesian(double x, double y, double rotation, double gyroAngle);

    /**
     * A method for driving with Mecanum wheeled robots. There are 4 wheels on
     * the robot, arranged so that the front and back wheels are toed in 45
     * degrees. When looking at the wheels from the top, the roller axles should
     * form an X across the robot.
     *
     * @param magnitude the speed that the robot should drive in a given
     * direction.
     * @param direction the direction the robot should drive in degrees. The
     * direction and magnitude are independent of the rotation rate.
     * @param rotation the rate of rotation for the robot that is completely
     * independent of the magnitude or direction. [-1.0..1.0]
     */
    public void mecanumDrive_Polar(double magnitude, double direction, double rotation);

    /**
     * Set the speed of the right and left motors. This is used once an
     * appropriate drive setup function is called such as twoWheelDrive(). The
     * motors are set to "leftSpeed" and "rightSpeed" and includes flipping the
     * direction of one side for opposing motors.
     *
     * @param leftOutput the speed to send to the left side of the robot.
     * @param rightOutput the speed to send to the right side of the robot.
     */
    public void setLeftRightMotorOutputs(double leftOutput, double rightOutput);

    /**
     * Configure the scaling factor for using RobotDrive with motor controllers
     * in a mode other than PercentVbus.
     *
     * @param maxOutput multiplied with the output percentage computed by the
     * drive functions.
     */
    public void setMaxOutput(double maxOutput);

    /**
     * Set the expiration time for the corresponding motor safety object.
     *
     * @param timeout the timeout value in seconds.
     */
    public void setExpiration(double timeout);

    /**
     * Retrieve the timeout value for the corresponding motor safety object.
     *
     * @return the timeout value in seconds.
     */
    public double getExpiration();

    /**
     * Return the state of the motor safety enabled flag. Return if the motor
     * safety is currently enabled for this device.
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
