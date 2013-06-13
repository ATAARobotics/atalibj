package edu.first.module.joysticks;

import edu.first.identifiers.Function;
import edu.first.module.Module;
import edu.first.util.MathUtils;

/**
 * A joystick that the user controls functions with. Attaches through the
 * DriverStation. Has axises and buttons to get input from. Doesn't provide
 * functionality past raw axises and buttons. ({@link #getRawAxisValue(int)} and
 * {@link #getRawButtonValue(int)})
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public class JoystickModule extends Module.StandardModule implements Joystick {

    private final edu.wpi.first.wpilibj.Joystick joystick;
    // stores axises for quick access and not having to instantize everytime
    private Axis[] axises = new Axis[]{
        new RawAxis(1),
        new RawAxis(2),
        new RawAxis(3),
        new RawAxis(4),
        new RawAxis(5),
        new RawAxis(6)
    };
    // stores buttons for quick access and not having to instantize everytime
    private Button[] buttons = new Button[]{
        new RawButton(1),
        new RawButton(2),
        new RawButton(3),
        new RawButton(4),
        new RawButton(5),
        new RawButton(6),
        new RawButton(7),
        new RawButton(8),
        new RawButton(9),
        new RawButton(10),
        new RawButton(11),
        new RawButton(12)
    };

    /**
     * Constructs the joystick with the {@link edu.wpi.first.wpilibj.Joystick}
     * object that this joystick gets input from.
     *
     * @param joystick the composing instance to get input from
     */
    protected JoystickModule(edu.wpi.first.wpilibj.Joystick joystick) {
        if (joystick == null) {
            throw new NullPointerException("Null joystick given");
        }
        this.joystick = joystick;
    }

    /**
     * Constructs the joystick with a port on the DriverStation.
     *
     * @param port channel in configuration of DriverStation
     */
    public JoystickModule(int port) {
        this(new edu.wpi.first.wpilibj.Joystick(port));
    }

    /**
     * {@inheritDoc}
     */
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     */
    protected void disableModule() {
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when port is not available
     */
    public final Axis getRawAxis(int port) {
        checkAxis(port);
        return axises[port - 1];

    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public final double getRawAxisValue(int port) {
        return getRawAxis(port).get();
    }

    /**
     * Returns a button that is triggered by moving an axis past a threshold.
     * The threshold only works in the direction that it is in (negative - axis
     * must be less; positive - axis must be more).
     *
     * @param port the axis to read
     * @param threshold point at which axis is considered "on"
     * @return a button controlled by an axis
     */
    public final Button getRawAxisAsButton(int port, double threshold) {
        return new RawAxisButton(port, threshold);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when port is not available
     */
    public final Button getRawButton(int port) {
        checkButton(port);
        return buttons[port - 1];
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public final boolean getRawButtonValue(int port) {
        return getRawButton(port).getPosition();
    }

    /**
     * Adds a deadband to the port which will prevent values smaller than
     * {@code deadband} from being output (instead it is set to 0).
     *
     * @throws IndexOutOfBoundsException when port is not available
     * @param port the axis
     * @param deadband value that input should be bigger than
     */
    public final void addDeadband(int port, final double deadband) {
        changeAxis(port, new Function() {
            public double F(double in) {
                if (MathUtils.abs(in) < deadband) {
                    return 0;
                } else {
                    return in;
                }
            }
        });
    }

    /**
     * Applies the function to {@link #getRawAxisValue(int)} and
     * {@link #getRawAxis(int)}. All input will have this function (and only
     * this function) applied.
     *
     * @throws IndexOutOfBoundsException when port is not available
     * @param port the axis to read
     * @param function change to apply to input
     */
    public final void changeAxis(int port, final Function function) {
        checkAxis(port);
        final Axis old = axises[port - 1];
        axises[port - 1] = new Axis() {
            public double get() {
                return function.F(old.get());
            }
        };
    }

    /**
     * Inverts the input from the button when called from
     * {@link #getRawAxisValue(int)} and {@link #getRawAxis(int)}. All input
     * from the port will be equal to {@code -input}.
     *
     * @param port the axis to invert
     */
    public final void invertAxis(int port) {
        changeAxis(port, new Function() {
            public double F(double in) {
                return -in;
            }
        });
    }

    /**
     * Changes the axis to a different input when called from
     * {@link #getRawAxisValue(int)} and {@link #getRawAxis(int)}. All input on
     * that port will be from the axis given.
     *
     * @throws IndexOutOfBoundsException when port is not available
     * @param port the axis to change
     * @param axis new axis
     */
    public final void setAxis(int port, final Axis axis) {
        checkAxis(port);
        axises[port - 1] = axis;
    }

    /**
     * Changes the button to a different input when called from
     * {@link #getRawButtonValue(int)} and {@link #getButtonAxis(int)}. All
     * input on that port will be from the button given.
     *
     * @param port the button to change
     * @param button new button
     */
    public final void setButton(int port, final Button button) {
        checkButton(port);
        buttons[port - 1] = button;
    }

    /**
     * Inverts input from the button when called from
     * {@link #getRawButtonValue(int)} and {@link #getRawButton(int)}.
     *
     * @param port the button to invert
     */
    public final void invertButton(int port) {
        checkButton(port);
        final Button old = buttons[port - 1];
        buttons[port - 1] = new Button() {
            public boolean getPosition() {
                return !old.getPosition();
            }
        };
    }

    /**
     * Increases the capacity of the axises on this joystick. The new axises
     * will not function at all until
     * {@link #setAxis(int, edu.first.module.joysticks.Joystick.Axis)} has been
     * called.
     *
     * @param newAxises how many axises to create
     */
    protected final void increaseAxisCapacity(int newAxises) {
        Axis[] b = new Axis[axises.length + newAxises];
        System.arraycopy(axises, 0, b, 0, axises.length);
        axises = b;
    }

    /**
     * Increases the capacity of the buttons on this joystick. The new buttons
     * will not function at all until
     * {@link #setButton(int, edu.first.module.joysticks.Joystick.Button)} has
     * been called.
     *
     * @param newButtons how many buttons to create
     */
    protected final void increaseButtonCapacity(int newButtons) {
        Button[] b = new Button[buttons.length + newButtons];
        System.arraycopy(buttons, 0, b, 0, buttons.length);
        buttons = b;
    }

    private void checkAxis(int port) {
        if (port > axises.length || port < 1) {
            throw new IndexOutOfBoundsException("Axis " + port + " is not a valid axis");
        }
    }

    private void checkButton(int port) {
        if (port > buttons.length || port < 1) {
            throw new IndexOutOfBoundsException("Button " + port + " is not a valid button");
        }
    }

    private class RawAxis implements Axis {

        private final int port;

        private RawAxis(int port) {
            this.port = port;
        }

        public double get() {
            ensureEnabled();
            return joystick.getRawAxis(port);
        }
    }

    private class RawButton implements Button {

        private final int port;

        private RawButton(int port) {
            this.port = port;
        }

        public boolean getPosition() {
            ensureEnabled();
            return joystick.getRawButton(port);
        }
    }

    private class RawAxisButton implements Button {

        private final int port;
        private final double threshold;

        public RawAxisButton(int port, double threshold) {
            this.port = port;
            this.threshold = threshold;
        }

        public boolean getPosition() {
            if (threshold >= 0) {
                return getRawAxisValue(port) > threshold;
            } else {
                return getRawAxisValue(port) < threshold;
            }
        }
    }
}
