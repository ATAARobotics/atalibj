package edu.first.module.joysticks;

import edu.wpi.first.wpilibj.Joystick;

/**
 * The Xbox 360 controller. Is bindable and has custom axises.
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
 *     Left: Negative; Right: Positive
 * 2: Left Stick Y Axis
 *     Up: Positive; Down: Negative
 * 3: Triggers
 *     Left: Negative; Right: Positive
 * 4: Right Stick X Axis
 *     Left: Negative; Right: Positive
 * 5: Right Stick Y Axis
 *     Up: Positive; Down: Negative
 * 6: Directional Pad (Not recommended, buggy)
 *     Left: -1; Right: +1
 * 7: Right distance from the middle
 *     Up: Positive; Down: Negative
 * 8: Left distance from the middle
 *     Up: Positive; Down: Negative
 * </pre>
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public class XboxController extends BindingJoystick {

    /**
     * Port for button.
     */
    public static final int A = 1, B = 2, X = 3,
            Y = 4, LEFT_BUMPER = 5, RIGHT_BUMPER = 6,
            BACK = 7, START = 8, LEFT_STICK = 9, RIGHT_STICK = 10;
    /**
     * Port for axis.
     */
    public static final int LEFT_X = 1, LEFT_Y = 2, TRIGGERS = 3, RIGHT_X = 4,
            RIGHT_Y = 5, DIRECTIONAL_PAD = 6, RIGHT_FROM_MIDDLE = 7, LEFT_FROM_MIDDLE = 8;

    /**
     * Constructs the joystick with the {@link edu.wpi.first.wpilibj.Joystick}
     * object that this joystick gets input from.
     *
     * @param joystick the composing instance to get input from
     */
    public XboxController(Joystick joystick) {
        super(joystick);

        increaseAxisCapacity(2);
        setAxis(RIGHT_FROM_MIDDLE, new FromMiddle(getRightY(), getRightX()));
        setAxis(LEFT_FROM_MIDDLE, new FromMiddle(getLeftY(), getLeftX()));
        invertAxis(LEFT_Y);
        invertAxis(RIGHT_Y);
        invertAxis(TRIGGERS);
    }

    /**
     * Constructs the joystick with the {@link edu.wpi.first.wpilibj.Joystick}
     * object that this joystick gets input from. Adds a deadband to every stick
     * input (left X, left Y, right X, right Y).
     *
     * @param joystick the composing instance to get input from
     * @param stickDeadband threshold of minimum input for stick axises
     * @see #addDeadband(int, double)
     */
    public XboxController(Joystick joystick, double stickDeadband) {
        this(joystick);

        addDeadband(LEFT_X, stickDeadband);
        addDeadband(LEFT_Y, stickDeadband);
        addDeadband(RIGHT_X, stickDeadband);
        addDeadband(RIGHT_Y, stickDeadband);
    }

    /**
     * Constructs the joystick with a port on the DriverStation.
     *
     * @param port channel in configuration of DriverStation
     */
    public XboxController(int port) {
        this(new Joystick(port));
    }

    /**
     * Constructs the joystick with a port on the DriverStation. Adds a deadband
     * to every stick input (left X, left Y, right X, right Y).
     *
     * @param port channel in configuration of DriverStation
     * @param stickDeadband threshold of minimum input for stick axises
     * @see #addDeadband(int, double)
     */
    public XboxController(int port, double stickDeadband) {
        this(new Joystick(port), stickDeadband);
    }

    /**
     * Returns the value of the left stick's X axis.
     *
     * <p> Left: Negative; Right: Positive
     *
     * @return left x axis value
     */
    public final double getLeftXValue() {
        return getRawAxisValue(LEFT_X);
    }

    /**
     * Returns an {@link Axis} that will give values from the axis. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * <p> Left: Negative; Right: Positive
     *
     * @return axis object to receive input from
     */
    public final Axis getLeftX() {
        return getRawAxis(LEFT_X);
    }

    /**
     * Returns the value of the left stick's Y axis.
     *
     * <p> Up: Positive; Down: Negative
     *
     * @return left y axis value
     */
    public final double getLeftYValue() {
        return getRawAxisValue(LEFT_Y);
    }

    /**
     * Returns an {@link Axis} that will give values from the axis. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * <p> Up: Positive; Down: Negative
     *
     * @return axis object to receive input from
     */
    public final Axis getLeftY() {
        return getRawAxis(LEFT_Y);
    }

    /**
     * Returns the value of the triggers' axis.
     *
     * <p> Each trigger goes from 0 to 1. Triggers value is equal to
     * {@code right - left}.
     *
     * <p> Left: Negative; Right: Positive
     *
     * @return trigger axis value
     */
    public final double getTriggerValue() {
        return getRawAxisValue(TRIGGERS);
    }

    /**
     * Returns an {@link Axis} that will give values from the axis. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * <p> Left: Negative; Right: Positive
     *
     * @return axis object to receive input from
     */
    public final Axis getTrigger() {
        return getRawAxis(TRIGGERS);
    }

    /**
     * Returns the value of the right stick's X axis.
     *
     * <p> Left: Negative; Right: Positive
     *
     * @return right x axis value
     */
    public final double getRightXValue() {
        return getRawAxisValue(RIGHT_X);
    }

    /**
     * Returns an {@link Axis} that will give values from the axis. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * <p> Left: Negative; Right: Positive
     *
     * @return axis object to receive input from
     */
    public final Axis getRightX() {
        return getRawAxis(RIGHT_X);
    }

    /**
     * Returns the value of the right stick's Y axis.
     *
     * <p> Up: Positive; Down: Negative
     *
     * @return right y axis value
     */
    public final double getRightYValue() {
        return getRawAxisValue(RIGHT_Y);
    }

    /**
     * Returns an {@link Axis} that will give values from the axis. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * <p> Up: Positive; Down: Negative
     *
     * @return axis object to receive input from
     */
    public final Axis getRightY() {
        return getRawAxis(RIGHT_Y);
    }

    /**
     * Returns the value of the directional pad.
     *
     * <p> Left: -1; Right: +1
     *
     * @return directional pad axis value
     */
    public final double getDirectionalPadValue() {
        return getRawAxisValue(DIRECTIONAL_PAD);
    }

    /**
     * Returns an {@link Axis} that will give values from the axis. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * <p> Left: -1; Right: +1
     *
     * @return axis object to receive input from
     */
    public final Axis getDirectionalPad() {
        return getRawAxis(DIRECTIONAL_PAD);
    }

    /**
     * Returns the distance that the right stick is relative to the absolute
     * centre.
     *
     * <p> Up: Positive; Down: Negative
     *
     * @return right distance from middle
     */
    public final double getRightDistanceFromMiddleValue() {
        return getRawAxisValue(RIGHT_FROM_MIDDLE);
    }

    /**
     * Returns an {@link Axis} that will give values from the axis. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * <p> Up: Positive; Down: Negative
     *
     * @return axis object to receive input from
     */
    public final Axis getRightDistanceFromMiddle() {
        return getRawAxis(RIGHT_FROM_MIDDLE);
    }

    /**
     * Returns the distance that the left stick is relative to the absolute
     * centre.
     *
     * <p> Up: Positive; Down: Negative
     *
     * @return left distance from middle
     */
    public final double getLeftDistanceFromMiddleValue() {
        return getRawAxisValue(LEFT_FROM_MIDDLE);
    }

    /**
     * Returns an {@link Axis} that will give values from the axis. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * <p> Up: Positive; Down: Negative
     *
     * @return axis object to receive input from
     */
    public final Axis getLeftDistanceFromMiddle() {
        return getRawAxis(LEFT_FROM_MIDDLE);
    }

    /**
     * Returns whether the A button is pressed.
     *
     * @return if A is pressed
     */
    public final boolean getAValue() {
        return getRawButtonValue(A);
    }

    /**
     * Returns a {@link Button} that will give values from the button. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * @return button object to receive input from
     */
    public final Button getA() {
        return getRawButton(A);
    }

    /**
     * Returns whether the B button is pressed.
     *
     * @return if B is pressed
     */
    public final boolean getBValue() {
        return getRawButtonValue(B);
    }

    /**
     * Returns a {@link Button} that will give values from the button. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * @return button object to receive input from
     */
    public final Button getB() {
        return getRawButton(B);
    }

    /**
     * Returns whether the X button is pressed.
     *
     * @return if X is pressed
     */
    public final boolean getXValue() {
        return getRawButtonValue(X);
    }

    /**
     * Returns a {@link Button} that will give values from the button. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * @return button object to receive input from
     */
    public final Button getX() {
        return getRawButton(X);
    }

    /**
     * Returns whether the Y button is pressed.
     *
     * @return if Y is pressed
     */
    public final boolean getYValue() {
        return getRawButtonValue(Y);
    }

    /**
     * Returns a {@link Button} that will give values from the button. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * @return button object to receive input from
     */
    public final Button getY() {
        return getRawButton(Y);
    }

    /**
     * Returns whether the left bumper button is pressed.
     *
     * @return if left bumper is pressed
     */
    public final boolean getLeftBumperValue() {
        return getRawButtonValue(LEFT_BUMPER);
    }

    /**
     * Returns a {@link Button} that will give values from the button. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * @return button object to receive input from
     */
    public final Button getLeftBumper() {
        return getRawButton(LEFT_BUMPER);
    }

    /**
     * Returns whether the right bumper button is pressed.
     *
     * @return if right bumper is pressed
     */
    public final boolean getRightBumperValue() {
        return getRawButtonValue(RIGHT_BUMPER);
    }

    /**
     * Returns a {@link Button} that will give values from the button. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * @return button object to receive input from
     */
    public final Button getRightBumper() {
        return getRawButton(RIGHT_BUMPER);
    }

    /**
     * Returns whether the back button is pressed.
     *
     * @return if back is pressed
     */
    public final boolean getBackValue() {
        return getRawButtonValue(BACK);
    }

    /**
     * Returns a {@link Button} that will give values from the button. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * @return button object to receive input from
     */
    public final Button getBack() {
        return getRawButton(BACK);
    }

    /**
     * Returns whether the start button is pressed.
     *
     * @return if start is pressed
     */
    public final boolean getStartValue() {
        return getRawButtonValue(START);
    }

    /**
     * Returns a {@link Button} that will give values from the button. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * @return button object to receive input from
     */
    public final Button getStart() {
        return getRawButton(START);
    }

    /**
     * Returns whether the left stick button is pressed.
     *
     * @return if left stick is pressed
     */
    public final boolean getLeftStickValue() {
        return getRawButtonValue(LEFT_STICK);
    }

    /**
     * Returns a {@link Button} that will give values from the button. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * @return button object to receive input from
     */
    public final Button getLeftStick() {
        return getRawButton(LEFT_STICK);
    }

    /**
     * Returns whether the right stick button is pressed.
     *
     * @return if back is pressed
     */
    public final boolean getRightStickValue() {
        return getRawButtonValue(RIGHT_STICK);
    }

    /**
     * Returns a {@link Button} that will give values from the button. Changed
     * settings of the controller will not affect this object after it has
     * already been created.
     *
     * @return button object to receive input from
     */
    public final Button getRightStick() {
        return getRawButton(RIGHT_STICK);
    }

    // Uses A^2 + B^2 = C^2 to solve distance from the middle of the joystick
    private static final class FromMiddle implements Axis {

        private final Axis X;
        private final Axis Y;

        public FromMiddle(Axis Y, Axis X) {
            this.Y = Y;
            this.X = X;
        }

        public double get() {
            double x = X.get();
            double y = Y.get();

            double distance = Math.sqrt((x * x) + (y * y));
            return (y > 0) ? distance : -distance;
        }
    }
}
