package edu.ATA.module.joystick;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Joystick module used to represent XboxControllers, which team 4334 usually
 * uses in competition.
 *
 *
 * <p> Raw buttons:
 *
 * <pre>
 * 1: A
 * 2: B
 * 3: X
 * 4: Y
 * 5: Left Bumper
 * 6: Right Bumper
 * 7: Back
 * 8: Start
 * 9: Left Joystick
 * 10: Right Joystick
 * </pre>
 *
 * Raw axes:
 *
 * <pre>
 * 1: Left Stick X Axis
 *     Left:Negative; Right: Positive
 * 2: Left Stick Y Axis
 *     Up: Positive; Down: Negative
 * 3: Triggers
 *     Left: Positive; Right: Negative
 * 4: Right Stick X Axis
 *     Left: Negative; Right: Positive
 * 5: Right Stick Y Axis
 *     Up: Positive; Down: Negative
 * 6: Directional Pad (Not recommended, buggy)
 * </pre>
 *
 *
 * @author Joel Gallant
 */
public final class XboxController extends BindableJoystick {

    /**
     * The absolute smallest value for the all axises to return. If it is
     * smaller than this value, it will revert to 0.
     */
    public static final double DEADZONE = 0.19;
    public static final int A = 1, B = 2, X = 3, Y = 4, LEFT_BUMPER = 5, RIGHT_BUMPER = 6,
            BACK = 7, START = 8, LEFT_STICK = 9, RIGHT_STICK = 10;
    public static final int LEFT_X = 1, LEFT_Y = 2, TRIGGERS = 3, RIGHT_X = 4,
            RIGHT_Y = 5, DIRECTIONAL_PAD = 6;

    /**
     * Constructs the object by using composition, using the given joystick
     * object to control methods in this class.
     *
     * @param joystick actual underlying object used
     */
    public XboxController(Joystick joystick) {
        super(joystick);
    }

    /**
     * If the module is enabled, returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawAxis(int)}. If it is not,
     * returns 0.
     *
     * <p> Takes the {@link XboxController#DEADZONE} into account.
     *
     * @param axis axis number defined in windows
     * @return value of where the joystick is (usually -1 to +1)
     */
    public double getAxis(int axis) {
        double a;
        return Math.abs(a = super.getAxis(axis)) < DEADZONE ? 0 : a;
    }

    /**
     * Returns whether or not the A button is currently being pressed by the
     * user.
     *
     * @return whether A button is pressed
     */
    public boolean getAButton() {
        return getButton(A);
    }

    /**
     * Returns whether or not the B button is currently being pressed by the
     * user.
     *
     * @return whether B button is pressed
     */
    public boolean getBButton() {
        return getButton(B);
    }

    /**
     * Returns whether or not the X button is currently being pressed by the
     * user.
     *
     * @return whether X button is pressed
     */
    public boolean getXButton() {
        return getButton(X);
    }

    /**
     * Returns whether or not the Y button is currently being pressed by the
     * user.
     *
     * @return whether Y button is pressed
     */
    public boolean getYButton() {
        return getButton(Y);
    }

    /**
     * Returns whether or not the left bumper button is currently being pressed
     * by the user.
     *
     * @return whether left bumper button is pressed
     */
    public boolean getLeftBumper() {
        return getButton(LEFT_BUMPER);
    }

    /**
     * Returns whether or not the right bumper button is currently being pressed
     * by the user.
     *
     * @return whether right bumper button is pressed
     */
    public boolean getRightBumper() {
        return getButton(RIGHT_BUMPER);
    }

    /**
     * Returns whether or not the back button is currently being pressed by the
     * user.
     *
     * @return whether back button is pressed
     */
    public boolean getBackButton() {
        return getButton(BACK);
    }

    /**
     * Returns whether or not the start button is currently being pressed by the
     * user.
     *
     * @return whether start button is pressed
     */
    public boolean getStartButton() {
        return getButton(START);
    }

    /**
     * Returns whether or not the left analog stick is currently being pressed
     * by the user.
     *
     * @return whether left analog stick is pressed
     */
    public boolean getLeftJoystickButton() {
        return getButton(LEFT_STICK);
    }

    /**
     * Returns whether or not the right analog stick is currently being pressed
     * by the user.
     *
     * @return whether right analog stick is pressed
     */
    public boolean getRightJoystickButton() {
        return getButton(RIGHT_STICK);
    }

    /**
     * Returns the value from -1 to +1 of the left joystick's X axis.
     *
     * <p> Left = Negative, Right = Positive
     *
     * @return left joystick x axis
     */
    public double getLeftX() {
        return getAxis(LEFT_X);
    }

    /**
     * Returns the value from -1 to +1 of the left joystick's Y axis.
     *
     * <p> Up = Positive, Down = Negative
     *
     * @return left joystick y axis
     */
    public double getLeftY() {
        return -getAxis(LEFT_Y);
    }

    /**
     * Returns the value from -1 to +1 of the right joystick's X axis.
     *
     * <p> Left = Negative, Right = Positive
     *
     * @return right joystick x axis
     */
    public double getRightX() {
        return getAxis(RIGHT_X);
    }

    /**
     * Returns the value from -1 to +1 of the right joystick's Y axis.
     *
     * <p> Up = Positive, Down = Negative
     *
     * @return right joystick y axis
     */
    public double getRightY() {
        return -getAxis(RIGHT_Y);
    }

    /**
     * Returns the value from -1 to +1 of the triggers. (Left value + Right
     * value)
     *
     * <p> Left = Negative, Right = Positive
     *
     * @return sum of both triggers values
     */
    public double getTriggers() {
        return -getAxis(TRIGGERS);
    }

    /**
     * Returns the value from -1 to +1 of directional pad's axis.
     *
     * <p><i> Apparently very buggy - not tested. </i>
     *
     * @return directional pad's axis
     */
    public double getDirectionalPad() {
        return getAxis(DIRECTIONAL_PAD);
    }

    /**
     * Returns the value of how far away the right analog stick is from the
     * center. If it is above y = 0, the value will be positive, and if it is
     * below, the value will be negative.
     *
     * <p> Is equivalent to : <center>
     * {@code squareroot[(|rightX| ^ 2) + (|rightY| ^ 2)] * (rightY > 0 ? 1 : -1)}
     * </center>
     *
     * @return how far away stick is from center
     */
    public double getRightDistanceFromMiddle() {
        double distance = Math.sqrt((getRightX()*getRightX()) + (getRightY()*getRightY()));
        return (getRightY() > 0) ? distance : -distance;
    }

    /**
     *
     * Returns the value of how far away the left analog stick is from the
     * center. If it is above y = 0, the value will be positive, and if it is
     * below, the value will be negative.
     *
     * <p> Is equivalent to : <center>
     * {@code squareroot[(|leftX| ^ 2) + (|leftY| ^ 2)] * (leftY > 0 ? 1 : -1)}
     * </center>
     *
     * @return how far away stick is from center
     */
    public double getLeftDistanceFromMiddle() {
        double distance = Math.sqrt((getLeftX()*getLeftX()) + (getLeftY()*getLeftY()));
        return (getLeftY() > 0) ? distance : -distance;
    }
}
