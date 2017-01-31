package edu.first.module.joysticks;

import edu.first.identifiers.Function;
import edu.first.identifiers.Output;
import edu.first.module.Module;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;

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
        new RawAxis(0), new RawAxis(1), new RawAxis(2),
        new RawAxis(3), new RawAxis(4), new RawAxis(5)
    };
    // stores buttons for quick access and not having to instantize everytime
    private Button[] buttons = new Button[]{
        new RawButton(0), new RawButton(1), new RawButton(2), new RawButton(3),
        new RawButton(4), new RawButton(5), new RawButton(6), new RawButton(7),
        new RawButton(8), new RawButton(9), new RawButton(10), new RawButton(11)
    };

    /**
     * Constructs the joystick with the {@link edu.wpi.first.wpilibj.Joystick}
     * object that this joystick gets input from.
     *
     * @throws NullPointerException when joystick is null
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
    @Override
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void disableModule() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init() {
    }

    /**
     * {@inheritDoc}
     *
     * @throws IndexOutOfBoundsException when port is not available
     */
    @Override
    public final Axis getRawAxis(int port) {
        checkAxis(port);
        return axises[port];

    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
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
    @Override
    public final Button getRawButton(int port) {
        checkButton(port);
        return buttons[port];
    }

    /**
     * Returns a button that represents two buttons being pressed
     * simultaneously.
     *
     * @param port1 first button
     * @param port2 second button
     * @return button that is both buttons together
     */
    public final Button getComboButton(final int port1, final int port2) {
        checkButton(port1);
        checkButton(port2);
        return new Button() {
            @Override
            public boolean getPosition() {
                return getRawButtonValue(port1) && getRawButtonValue(port2);
            }
        };
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    @Override
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
            @Override
            public double F(double in) {
                if (Math.abs(in) < deadband) {
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
        final Axis old = axises[port];
        axises[port] = new Axis() {
            @Override
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
            @Override
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
        axises[port] = axis;
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
        buttons[port] = button;
    }

    /**
     * Inverts input from the button when called from
     * {@link #getRawButtonValue(int)} and {@link #getRawButton(int)}.
     *
     * @param port the button to invert
     */
    public final void invertButton(int port) {
        checkButton(port);
        final Button old = buttons[port];
        buttons[port] = new Button() {
            @Override
            public boolean getPosition() {
                return !old.getPosition();
            }
        };
    }

    public final Output getRumble(RumbleType type) {
        return new Rumble(type);
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
        if (port > axises.length || port < 0) {
            throw new IndexOutOfBoundsException("Axis " + port
                    + " is not a valid axis");
        }
    }

    private void checkButton(int port) {
        if (port > buttons.length || port < 0) {
            throw new IndexOutOfBoundsException("Button " + port
                    + " is not a valid button");
        }
    }

    private class RawAxis implements Axis {

        private final int port;

        private RawAxis(int port) {
            this.port = port;
        }

        @Override
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

        @Override
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

        @Override
        public boolean getPosition() {
            if (threshold >= 0) {
                return getRawAxisValue(port) > threshold;
            } else {
                return getRawAxisValue(port) < threshold;
            }
        }
    }

    private class Rumble implements Output {

        private final RumbleType type;

        public Rumble(RumbleType type) {
            this.type = type;
        }

        @Override
        public void set(double value) {
            joystick.setRumble(type, (float) value);
        }
    }
}
