package edu.ATA.module.driving;

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.RobotDrive}.
 *
 * @author Joel Gallant
 */
class ForwardingRobotDrive implements RobotDrive {

    private final edu.wpi.first.wpilibj.RobotDrive drive;

    /**
     * Constructs the object by using composition, using the given robot drive
     * object to control methods in this class.
     *
     * @param drive actual underlying object used
     */
    ForwardingRobotDrive(edu.wpi.first.wpilibj.RobotDrive drive) {
        this.drive = drive;
    }

    /**
     * Returns the instance of the underlying
     * {@link edu.wpi.first.wpilibj.RobotDrive}.
     *
     * @return composition object under this one
     */
    protected edu.wpi.first.wpilibj.RobotDrive getDrive() {
        return drive;
    }

    /**
     * {@inheritDoc}
     */
    public void drive(double outputMagnitude, double curve) {
        drive.drive(outputMagnitude, curve);
    }

    /**
     * {@inheritDoc}
     */
    public void tankDrive(double leftValue, double rightValue, boolean squaredInputs) {
        drive.tankDrive(leftValue, rightValue, squaredInputs);
    }

    /**
     * {@inheritDoc}
     */
    public void tankDrive(double leftValue, double rightValue) {
        drive.tankDrive(leftValue, rightValue);
    }

    /**
     * {@inheritDoc}
     */
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        drive.arcadeDrive(moveValue, rotateValue, squaredInputs);
    }

    /**
     * {@inheritDoc}
     */
    public void arcadeDrive(double moveValue, double rotateValue) {
        drive.arcadeDrive(moveValue, rotateValue);
    }

    /**
     * {@inheritDoc}
     */
    public void mecanumDrive_Cartesian(double x, double y, double rotation, double gyroAngle) {
        drive.mecanumDrive_Cartesian(x, y, rotation, gyroAngle);
    }

    /**
     * {@inheritDoc}
     */
    public void mecanumDrive_Polar(double magnitude, double direction, double rotation) {
        drive.mecanumDrive_Polar(magnitude, direction, rotation);
    }

    /**
     * {@inheritDoc}
     */
    public void setLeftRightMotorOutputs(double leftOutput, double rightOutput) {
        drive.setLeftRightMotorOutputs(leftOutput, rightOutput);
    }

    /**
     * {@inheritDoc}
     */
    public void setMaxOutput(double maxOutput) {
        drive.setMaxOutput(maxOutput);
    }

    /**
     * {@inheritDoc}
     */
    public void setExpiration(double timeout) {
        drive.setExpiration(timeout);
    }

    /**
     * {@inheritDoc}
     */
    public double getExpiration() {
        return drive.getExpiration();
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSafetyEnabled() {
        return drive.isSafetyEnabled();
    }

    /**
     * {@inheritDoc}
     */
    public void setSafetyEnabled(boolean enabled) {
        drive.setSafetyEnabled(enabled);
    }

    /**
     * {@inheritDoc}
     */
    public void stopMotors() {
        drive.stopMotor();
    }
}
