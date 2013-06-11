package edu.first.module.actuators;

import edu.first.identifiers.Output;
import edu.first.module.Module;
import edu.first.util.MathUtils;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;

/**
 * The implementation to control a standard drivetrain. (Non-Mecanum or Swerve)
 * Has arcade, tank and curving drive modes.
 *
 * @since June 10 13
 * @author Joel Gallant
 */
public class Drivetrain extends Module.StartardModule implements MotorSafety {

    private final Output driveStraight = new Output() {
        public void set(double value) {
            Drivetrain.this.set(value, value);
        }
    };
    private final Output turn = new Output() {
        public void set(double value) {
            Drivetrain.this.arcadeDrive(0, value);
        }
    };
    private final MotorSafetyHelper safetyHelper = new MotorSafetyHelper(this);
    private final SpeedController left, right;
    private boolean reversed = false;
    private boolean reversedTurn = false;
    private double maxSpeed = 1;

    /**
     * Constructs the drivetrain using the motor controllers required to move.
     *
     * @param left left side of the drivetrain
     * @param right right side of the drivetrain
     */
    public Drivetrain(SpeedController left, SpeedController right) {
        this.left = left;
        this.right = right;
        setupMotorSafety();
    }

    /**
     * Constructs the drivetrain using the motor controllers required to move.
     *
     * @param leftFront motor at front left side
     * @param leftBack motor at back left side
     * @param rightFront motor at front right side
     * @param rightBack motor at back right side
     */
    public Drivetrain(SpeedController leftFront, SpeedController leftBack,
            SpeedController rightFront, SpeedController rightBack) {
        this.left = new SpeedControllerGroup().add(leftFront).add(leftBack);
        this.right = new SpeedControllerGroup().add(rightFront).add(rightBack);
        setupMotorSafety();
    }

    private void setupMotorSafety() {
        safetyHelper.setExpiration(0.1);
    }

    /**
     * {@inheritDoc}
     */
    protected void enableModule() {
        safetyHelper.setSafetyEnabled(true);
    }

    /**
     * {@inheritDoc}
     *
     * Turns off all motors.
     */
    protected void disableModule() {
        safetyHelper.setSafetyEnabled(false);
        stopMotor();
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
    }

    /**
     * Sets the absolute maximum speed in either direction that the motors can
     * run at. [-1 to +1]
     *
     * @param maxSpeed maximum speed possible
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * Returns the absolute maximum speed in either direction that the motors
     * can run at. [-1 to +1]
     *
     * @return maximum speed possible
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Sets whether all motor output should be reversed.
     *
     * @param reversed if motors should go in opposite direction
     */
    public void setReversed(boolean reversed) {
        this.reversed = reversed;
    }

    /**
     * Returns whether all motor output should be reversed.
     *
     * @return if motors should go in opposite direction
     */
    public boolean isReversed() {
        return reversed;
    }

    /**
     * Sets whether all turning should be reversed.
     *
     * @param reversedTurn if turning should be opposite
     */
    public void setReversedTurn(boolean reversedTurn) {
        this.reversedTurn = reversedTurn;
    }

    /**
     * Returns whether all turning should be reversed.
     *
     * @return if turning should be opposite
     */
    public boolean isReversedTurn() {
        return reversedTurn;
    }

    /**
     * Sets the left and right side speeds without any math. Usually
     * {@link #tankDrive(double, double)} is preferred to this method.
     *
     * @throws IllegalStateException when module is not enabled
     * @param left speed of left side
     * @param right speed of right side
     */
    public final void set(double left, double right) {
        ensureEnabled();
        safetyHelper.feed();
        this.left.setSpeed(MathUtils.limit(reversed ? -left : left, maxSpeed));
        this.right.setSpeed(MathUtils.limit(reversed ? -right : right, maxSpeed));
    }

    /**
     * Driving function that controls forwards/backwards movement with one
     * value, and left/right movement with a different value.
     *
     * @throws IllegalStateException when module is not enabled
     * @param moveValue forwards/backwards [-1 to +1]
     * @param rotateValue left/right [-1 to +1]
     */
    public void arcadeDrive(double moveValue, double rotateValue) {
        arcadeDrive(moveValue, rotateValue, false);
    }

    /**
     * Driving function that controls forwards/backwards movement with one
     * value, and left/right movement with a different value.
     *
     * @throws IllegalStateException when module is not enabled
     * @param moveValue forwards/backwards [-1 to +1]
     * @param rotateValue left/right [-1 to +1]
     * @param squaredInputs if input should be squared for better response
     */
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        double leftMotorSpeed;
        double rightMotorSpeed;

        if (reversedTurn) {
            rotateValue = -rotateValue;
        }

        moveValue = MathUtils.limit(moveValue, 1);
        rotateValue = MathUtils.limit(rotateValue, 1);

        if (squaredInputs) {
            if (moveValue >= 0.0) {
                moveValue = (moveValue * moveValue);
            } else {
                moveValue = -(moveValue * moveValue);
            }
            if (rotateValue >= 0.0) {
                rotateValue = (rotateValue * rotateValue);
            } else {
                rotateValue = -(rotateValue * rotateValue);
            }
        }

        if (moveValue > 0.0) {
            if (rotateValue > 0.0) {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = Math.max(moveValue, rotateValue);
            } else {
                leftMotorSpeed = Math.max(moveValue, -rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            }
        } else {
            if (rotateValue > 0.0) {
                leftMotorSpeed = -Math.max(-moveValue, rotateValue);
                rightMotorSpeed = moveValue + rotateValue;
            } else {
                leftMotorSpeed = moveValue - rotateValue;
                rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
            }
        }

        set(leftMotorSpeed, rightMotorSpeed);
    }

    /**
     * Tank-style steering that controls both sides separately.
     *
     * @throws IllegalStateException when module is not enabled
     * @param leftValue left side speed [-1 to +1]
     * @param rightValue right side speed [-1 to +1]
     */
    public void tankDrive(double leftValue, double rightValue) {
        tankDrive(leftValue, rightValue, false);
    }

    /**
     * Tank-style steering that controls both sides separately.
     *
     * @throws IllegalStateException when module is not enabled
     * @param leftValue left side speed [-1 to +1]
     * @param rightValue right side speed [-1 to +1]
     * @param squaredInputs if input should be squared for better response
     */
    public void tankDrive(double leftValue, double rightValue, boolean squaredInputs) {
        leftValue = MathUtils.limit(leftValue, 1);
        rightValue = MathUtils.limit(rightValue, 1);

        if (squaredInputs) {
            if (leftValue >= 0.0) {
                leftValue = (leftValue * leftValue);
            } else {
                leftValue = -(leftValue * leftValue);
            }
            if (rightValue >= 0.0) {
                rightValue = (rightValue * rightValue);
            } else {
                rightValue = -(rightValue * rightValue);
            }
        }
        set(leftValue, rightValue);
    }

    /**
     * Drive the motors at "speed" and "curve".
     *
     * <p> The speed and curve are -1.0 to +1.0 values where 0.0 represents
     * stopped and not turning. The algorithm for adding in the direction
     * attempts to provide a constant turn radius for differing speeds.
     *
     * <p> This function will most likely be used in an autonomous routine.
     *
     * @throws IllegalStateException when module is not enabled
     * @param outputMagnitude the forward component of the output magnitude to
     * send to the motors
     * @param curve the rate of turn, constant for different forward speeds
     */
    public void drive(double outputMagnitude, double curve) {
        double leftOutput, rightOutput;

        if (reversedTurn) {
            curve = -curve;
        }

        if (curve < 0) {
            double value = MathUtils.log(-curve);
            double ratio = (value - 0.5) / (value + 0.5);
            if (ratio == 0) {
                ratio = .0000000001;
            }
            leftOutput = outputMagnitude / ratio;
            rightOutput = outputMagnitude;
        } else if (curve > 0) {
            double value = MathUtils.log(curve);
            double ratio = (value - 0.5) / (value + 0.5);
            if (ratio == 0) {
                ratio = .0000000001;
            }
            leftOutput = outputMagnitude;
            rightOutput = outputMagnitude / ratio;
        } else {
            leftOutput = outputMagnitude;
            rightOutput = outputMagnitude;
        }
        set(leftOutput, rightOutput);
    }

    /**
     * Returns an output that drives the drivetrain forwards and backwards.
     *
     * @return output driving straight
     */
    public Output getDriveStraight() {
        return driveStraight;
    }

    /**
     * Returns an output that turns the drivetrain left and right.
     *
     * @return output driving turns
     */
    public Output getTurn() {
        return turn;
    }

    /**
     * Sets the time it is acceptable to wait for input until stopping the
     * robot.
     *
     * @param timeout time in seconds
     */
    public final void setExpiration(double timeout) {
        safetyHelper.setExpiration(timeout);
    }

    /**
     * Returns the time it is acceptable to wait for input until stopping the
     * robot.
     *
     * @return time in seconds
     */
    public final double getExpiration() {
        return safetyHelper.getExpiration();
    }

    /**
     * Returns whether there is sufficient input being given that the safety
     * controller does not time out.
     *
     * @return if drivetrain has input
     * @see #getExpiration()
     */
    public final boolean isAlive() {
        return safetyHelper.isAlive();
    }

    /**
     * Stops the drivetrain cold.
     */
    public final void stopMotor() {
        set(0, 0);
    }

    /**
     * Sets whether the safety controller should keep track of input.
     *
     * @param enabled if input is tracked
     */
    public final void setSafetyEnabled(boolean enabled) {
        safetyHelper.setSafetyEnabled(enabled);
    }

    /**
     * Returns whether the safety controller should keep track of input.
     *
     * @return if input is tracked
     */
    public final boolean isSafetyEnabled() {
        return safetyHelper.isSafetyEnabled();
    }

    /**
     * Returns a short description of the class for the motor safety.
     *
     * @return string representing this module
     */
    public final String getDescription() {
        return "Drivetrain";
    }
}
