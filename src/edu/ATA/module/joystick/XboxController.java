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
 *     Up: Negative; Down: Positive
 * 3: Triggers
 *     Left: Positive; Right: Negative
 * 4: Right Stick X Axis
 *     Left: Negative; Right: Positive
 * 5: Right Stick Y Axis
 *     Up: Negative; Down: Positive
 * 6: Directional Pad (Not recommended, buggy)
 * </pre>
 *
 *
 * @author Joel Gallant
 */
public class XboxController extends JoystickModule {

    /**
     * The absolute smallest value for the all axises to return. If it is
     * smaller than this value, it will revert to 0.
     */
    public static final double DEADZONE = 0.07;

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
        return (a = super.getAxis(axis)) < DEADZONE ? 0 : a;
    }

    /**
     * Returns whether or not the A button is currently being pressed by the
     * user.
     *
     * @return whether A button is pressed
     */
    public boolean getAButton() {
        return getButton(1);
    }

    /**
     * Returns whether or not the B button is currently being pressed by the
     * user.
     *
     * @return whether B button is pressed
     */
    public boolean getBButton() {
        return getButton(2);
    }

    /**
     * Returns whether or not the X button is currently being pressed by the
     * user.
     *
     * @return whether X button is pressed
     */
    public boolean getXButton() {
        return getButton(3);
    }

    /**
     * Returns whether or not the Y button is currently being pressed by the
     * user.
     *
     * @return whether Y button is pressed
     */
    public boolean getYButton() {
        return getButton(4);
    }

    /**
     * Returns whether or not the left bumper button is currently being pressed
     * by the user.
     *
     * @return whether left bumper button is pressed
     */
    public boolean getLeftBumper() {
        return getButton(5);
    }

    /**
     * Returns whether or not the right bumper button is currently being pressed
     * by the user.
     *
     * @return whether right bumper button is pressed
     */
    public boolean getRightBumper() {
        return getButton(6);
    }

    /**
     * Returns whether or not the back button is currently being pressed by the
     * user.
     *
     * @return whether back button is pressed
     */
    public boolean getBackButton() {
        return getButton(7);
    }

    /**
     * Returns whether or not the start button is currently being pressed by the
     * user.
     *
     * @return whether start button is pressed
     */
    public boolean getStartButton() {
        return getButton(8);
    }

    /**
     * Returns whether or not the left analog stick is currently being pressed
     * by the user.
     *
     * @return whether left analog stick is pressed
     */
    public boolean getLeftJoystickButton() {
        return getButton(9);
    }

    /**
     * Returns whether or not the right analog stick is currently being pressed
     * by the user.
     *
     * @return whether right analog stick is pressed
     */
    public boolean getRightJoystickButton() {
        return getButton(10);
    }

    /**
     * Returns the value from -1 to +1 of the left joystick's X axis.
     *
     * <p> Left = Negative, Right = Positive
     *
     * @return left joystick x axis
     */
    public double getLeftX() {
        return getAxis(1);
    }

    /**
     * Returns the value from -1 to +1 of the left joystick's Y axis.
     *
     * <p> Up = Negative, Down = Positive
     *
     * @return left joystick y axis
     */
    public double getLeftY() {
        return getAxis(2);
    }

    /**
     * Returns the value from -1 to +1 of the right joystick's X axis.
     *
     * <p> Left = Negative, Right = Positive
     *
     * @return right joystick x axis
     */
    public double getRightX() {
        return getAxis(4);
    }

    /**
     * Returns the value from -1 to +1 of the right joystick's Y axis.
     *
     * <p> Up = Negative, Down = Positive
     *
     * @return right joystick y axis
     */
    public double getRightY() {
        return getAxis(5);
    }

    /**
     * Returns the value from -1 to +1 of the triggers. (Left value + Right
     * value)
     *
     * <p> Left = Positive, Right = Negative
     *
     * @return sum of both triggers values
     */
    public double getTriggers() {
        return getAxis(3);
    }

    /**
     * Returns the value from -1 to +1 of directional pad's axis.
     *
     * <p><i> Apparently very buggy - not tested. </i>
     *
     * @return directional pad's axis
     */
    public double getDirectionalPad() {
        return getAxis(6);
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
        double xS = (Math.abs(getRightX())) * (Math.abs(getRightX()));
        double yS = (Math.abs(getRightY())) * (Math.abs(getRightY()));
        return (Math.sqrt(xS + yS)) * (getRightY() > 0 ? 1 : -1);
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
        double xS = (Math.abs(getLeftX())) * (Math.abs(getLeftX()));
        double yS = (Math.abs(getLeftY())) * (Math.abs(getLeftY()));
        return (Math.sqrt(xS + yS)) * (getLeftY() > 0 ? 1 : -1);
    }
}
