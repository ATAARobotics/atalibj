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
public final class XboxController extends Module implements Module.Disableable {

    private final Joystick controller;
    private boolean enabled;

    /**
     * Creates the Xbox Controller object on the specified port. If another
     * {@link Joystick} object exists on the port, an error will be thrown (very
     * low level).
     *
     * @param name name of the module
     * @param port port of joystick on DriverStation
     */
    public XboxController(String name, int port) {
        super(name);
        this.controller = new Joystick(port);
    }

    /**
     * Creates the Xbox Controller object on the specified port. If another
     * {@link Joystick} object exists on the port, an error will be thrown (very
     * low level).
     *
     * @param port port of joystick on DriverStation
     */
    public XboxController(int port) {
        this("XboxController Port " + port, port);
    }

    private XboxController(Joystick joystick) {
        super("Converted XboxController");
        this.controller = joystick;
    }

    /**
     * Converts a {@link Joystick} object into an {@link XboxController} for
     * module abilities.
     *
     * @param joystick joystick object to control
     * @return new Xbox controller
     */
    public static XboxController toXboxController(Joystick joystick) {
        return new XboxController(joystick);
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
     * Returns the {@link Joystick} object underneath this class.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return joystick object being used
     */
    public Joystick getJoystick() throws IllegalStateException {
        if (isEnabled()) {
            return controller;
        } else {
            throw new IllegalStateException("XboxController module accessed has not been enabled - " + getName());
        }
    }

    /**
     * Returns whether or not the A button is currently being pressed by the
     * user.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return whether A button is pressed
     */
    public boolean getAButton() throws IllegalStateException {
        return getJoystick().getRawButton(1);
    }

    /**
     * Returns whether or not the B button is currently being pressed by the
     * user.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return whether B button is pressed
     */
    public boolean getBButton() throws IllegalStateException {
        return getJoystick().getRawButton(2);
    }

    /**
     * Returns whether or not the X button is currently being pressed by the
     * user.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return whether X button is pressed
     */
    public boolean getXButton() throws IllegalStateException {
        return getJoystick().getRawButton(3);
    }

    /**
     * Returns whether or not the Y button is currently being pressed by the
     * user.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return whether Y button is pressed
     */
    public boolean getYButton() throws IllegalStateException {
        return getJoystick().getRawButton(4);
    }

    /**
     * Returns whether or not the left bumper button is currently being pressed
     * by the user.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return whether left bumper button is pressed
     */
    public boolean getLeftBumper() throws IllegalStateException {
        return getJoystick().getRawButton(5);
    }

    /**
     * Returns whether or not the right bumper button is currently being pressed
     * by the user.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return whether right bumper button is pressed
     */
    public boolean getRightBumper() throws IllegalStateException {
        return getJoystick().getRawButton(6);
    }

    /**
     * Returns whether or not the back button is currently being pressed by the
     * user.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return whether back button is pressed
     */
    public boolean getBackButton() throws IllegalStateException {
        return getJoystick().getRawButton(7);
    }

    /**
     * Returns whether or not the start button is currently being pressed by the
     * user.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return whether start button is pressed
     */
    public boolean getStartButton() throws IllegalStateException {
        return getJoystick().getRawButton(8);
    }

    /**
     * Returns whether or not the left analog stick is currently being pressed
     * by the user.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return whether left analog stick is pressed
     */
    public boolean getLeftJoystickButton() throws IllegalStateException {
        return getJoystick().getRawButton(9);
    }

    /**
     * Returns whether or not the right analog stick is currently being pressed
     * by the user.
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return whether right analog stick is pressed
     */
    public boolean getRightJoystickButton() throws IllegalStateException {
        return getJoystick().getRawButton(10);
    }

    /**
     * Returns the value from -1 to +1 of the left joystick's X axis.
     *
     * <p> Left = Negative, Right = Positive
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return left joystick x axis
     */
    public double getLeftX() throws IllegalStateException {
        return getJoystick().getRawAxis(1);
    }

    /**
     * Returns the value from -1 to +1 of the left joystick's Y axis.
     *
     * <p> Up = Negative, Down = Positive
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return left joystick y axis
     */
    public double getLeftY() throws IllegalStateException {
        return getJoystick().getRawAxis(2);
    }

    /**
     * Returns the value from -1 to +1 of the right joystick's X axis.
     *
     * <p> Left = Negative, Right = Positive
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return right joystick x axis
     */
    public double getRightX() throws IllegalStateException {
        return getJoystick().getRawAxis(4);
    }

    /**
     * Returns the value from -1 to +1 of the right joystick's Y axis.
     *
     * <p> Up = Negative, Down = Positive
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return right joystick y axis
     */
    public double getRightY() throws IllegalStateException {
        return getJoystick().getRawAxis(5);
    }

    /**
     * Returns the value from -1 to +1 of the triggers. (Left value + Right
     * value)
     *
     * <p> Left = Positive, Right = Negative
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return triggers
     */
    public double getTriggers() throws IllegalStateException {
        return getJoystick().getRawAxis(3);
    }

    /**
     * Returns the value from -1 to +1 of directional pad's axis.
     *
     * <p><i> Apparently very buggy - not tested. </i>
     *
     * <p> <i> Throws an {@link IllegalStateException} if the module has not
     * been enabled ({@link Module#enable()})</i>
     *
     * @throws IllegalStateException thrown when module is not enabled
     * @return directional pad's axis
     */
    public double getDirectionalPad() throws IllegalStateException {
        return getJoystick().getRawAxis(6);
    }
}