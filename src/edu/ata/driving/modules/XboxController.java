package edu.ata.driving.modules;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Wrapper class for {@link Joystick}. Is not a {@code Joystick} object itself,
 * but the user can use {@link XboxController#toController()} to convert it into
 * the joystick.
 *
 * <p> Any use of this class before {@link XboxController#enable() enabling}
 * will throw {@link NullPointerException}.
 *
 * <p>
 *
 * Raw buttons:
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
 * @author Joel Gallant
 */
// Does not extend Joystick because joystick should not be created before enable()
public class XboxController extends Module {

    private Joystick controller;
    private final int port;

    /**
     * Creates the Xbox Controller object on the specified port. If another
     * {@link Joystick} object exists on the port, an error will be thrown (very
     * low level).
     *
     * @param port port of joystick on DriverStation
     */
    public XboxController(int port) {
        super("XboxController Port " + port);
        this.port = port;
    }

    /**
     * Enables features of this class. Nothing can be used before calling this
     * method.
     */
    public void enable() {
        if (controller == null) {
            controller = new Joystick(port);
        }
    }

    /**
     * Checks to see if the joystick is usable. If this returns true, you should
     * be safe to use methods in this class (assuming not in concurrent
     * context).
     *
     * @return whether joystick is usable
     */
    public boolean isEnabled() {
        return controller != null;
    }

    /**
     * 'Converts' the controller into the native {@link Joystick} object that
     * lays underneath this class. If the controller has not been enabled yet,
     * this will return null.
     *
     * @return joystick if it exists
     */
    public Joystick toJoystick() {
        return controller;
    }

    /**
     * Returns whether or not the A button is currently being pressed by the
     * user.
     *
     * @return whether A button is pressed
     */
    public boolean getAButton() {
        return controller.getRawButton(1);
    }

    /**
     * Returns whether or not the B button is currently being pressed by the
     * user.
     *
     * @return whether B button is pressed
     */
    public boolean getBButton() {
        return controller.getRawButton(2);
    }

    /**
     * Returns whether or not the X button is currently being pressed by the
     * user.
     *
     * @return whether X button is pressed
     */
    public boolean getXButton() {
        return controller.getRawButton(3);
    }

    /**
     * Returns whether or not the Y button is currently being pressed by the
     * user.
     *
     * @return whether Y button is pressed
     */
    public boolean getYButton() {
        return controller.getRawButton(4);
    }

    /**
     * Returns whether or not the left bumper button is currently being pressed
     * by the user.
     *
     * @return whether left bumper button is pressed
     */
    public boolean getLeftBumper() {
        return controller.getRawButton(5);
    }

    /**
     * Returns whether or not the right bumper button is currently being pressed
     * by the user.
     *
     * @return whether right bumper button is pressed
     */
    public boolean getRightBumper() {
        return controller.getRawButton(6);
    }

    /**
     * Returns whether or not the back button is currently being pressed by the
     * user.
     *
     * @return whether back button is pressed
     */
    public boolean getBackButton() {
        return controller.getRawButton(7);
    }

    /**
     * Returns whether or not the start button is currently being pressed by the
     * user.
     *
     * @return whether start button is pressed
     */
    public boolean getStartButton() {
        return controller.getRawButton(8);
    }

    /**
     * Returns whether or not the left analog stick is currently being pressed
     * by the user.
     *
     * @return whether left analog stick is pressed
     */
    public boolean getLeftJoystickButton() {
        return controller.getRawButton(9);
    }

    /**
     * Returns whether or not the right analog stick is currently being pressed
     * by the user.
     *
     * @return whether right analog stick is pressed
     */
    public boolean getRightJoystickButton() {
        return controller.getRawButton(10);
    }

    /**
     * Returns the value from -1 to +1 of the left joystick's X axis.
     *
     * <p> Left = Negative, Right = Positive
     *
     * @return left joystick x axis
     */
    public double getLeftX() {
        return controller.getRawAxis(1);
    }

    /**
     * Returns the value from -1 to +1 of the left joystick's Y axis.
     *
     * <p> Up = Negative, Down = Positive
     *
     * @return left joystick y axis
     */
    public double getLeftY() {
        return controller.getRawAxis(2);
    }

    /**
     * Returns the value from -1 to +1 of the right joystick's X axis.
     *
     * <p> Left = Negative, Right = Positive
     *
     * @return right joystick x axis
     */
    public double getRightX() {
        return controller.getRawAxis(4);
    }

    /**
     * Returns the value from -1 to +1 of the right joystick's Y axis.
     *
     * <p> Up = Negative, Down = Positive
     *
     * @return right joystick y axis
     */
    public double getRightY() {
        return controller.getRawAxis(5);
    }

    /**
     * Returns the value from -1 to +1 of the triggers. (Left value + Right
     * value)
     *
     * <p> Left = Positive, Right = Negative
     *
     * @return triggers
     */
    public double getTriggers() {
        return controller.getRawAxis(3);
    }

    /**
     * Returns the value from -1 to +1 of directional pad's axis.
     *
     * <p><i> Apparently very buggy - not tested. </i>
     *
     * @return directional pad's axis
     */
    public double getDirectionalPad() {
        return controller.getRawAxis(6);
    }
}