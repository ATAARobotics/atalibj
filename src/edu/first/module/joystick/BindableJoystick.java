package edu.first.module.joystick;

import edu.first.bindings.AxisBind;
import edu.first.binding.Bindable;
import edu.first.command.Command;
import edu.first.identifiers.Function;
import edu.first.module.Module;
import edu.first.module.joystick.BindableJoystick.Axis;

/**
 * Class meant to describe joysticks that are capable of "binding", as described
 * in {@link Bindable}. Acts in the same way that a {@link JoystickModule} does,
 * but implements features that allow buttons and axises.
 *
 * @author Joel Gallant
 */
public class BindableJoystick extends Bindable implements Joystick, Module.DisableableModule {

    /**
     * The composition object used to get input.
     */
    private final JoystickModule joystick;

    /**
     * Constructs the joystick using composition to operate the class using the
     * wpi joystick object.
     *
     * @param joystick object used to get input
     */
    public BindableJoystick(edu.wpi.first.wpilibj.Joystick joystick) {
        this.joystick = new JoystickModule(joystick);
    }

    /**
     * Turns off the joystick module, making all input return 0.
     *
     * @return whether module successfully disabled
     */
    public final boolean disable() {
        return joystick.disable();
    }

    /**
     * Enables the module so that it can give input from the joystick.
     *
     * @return whether module is enabled correctly
     */
    public final boolean enable() {
        return joystick.enable();
    }

    /**
     * Returns whether or not the joystick will return valid results.
     *
     * @return whether joystick is enabled
     */
    public final boolean isEnabled() {
        return joystick.isEnabled();
    }

    /**
     * Removes all binds for buttons that are binded on the port number.
     *
     * @param port button port of getRawButton()
     */
    public final void removeButtonBinds(int port) {
        for (int x = 0; x < binds.size(); x++) {
            Object o = binds.get(x);
            if (o instanceof BindsActionWithButton) {
                if (((BindsActionWithButton) o).button instanceof Button) {
                    if (((Button) ((BindsActionWithButton) o).button).port == port) {
                        removeBind((BindAction) o);
                    }
                }
            }
        }
    }

    /**
     * Removes all binds for axises that are binded on the port number.
     *
     * @param port axis port of getRawAxis()
     */
    public final void removeAxisBinds(int port) {
        for (int x = 0; x < binds.size(); x++) {
            Object o = binds.get(x);
            if (o instanceof BindsActionWithAxis) {
                if (((BindsActionWithAxis) o).axis instanceof Axis) {
                    if (((Axis) ((BindsActionWithAxis) o).axis).port == port) {
                        removeBind((BindAction) o);
                    }
                }
            }
        }
    }

    /**
     * If the module is enabled, returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawAxis(int)}. If it is not,
     * returns 0.
     *
     * @param port the port of the axis
     * @return value of where the joystick is (usually -1 to +1)
     */
    public double getRawAxis(int port) {
        return joystick.getRawAxis(port);
    }

    /**
     * If the module is enabled, returns the equivalent of
     * {@link edu.wpi.first.wpilibj.Joystick#getRawButton(int)}. If it is not,
     * returns false.
     *
     * @param button button number defined in windows
     * @return whether the button is pressed
     */
    public boolean getRawButton(int button) {
        return joystick.getRawButton(button);
    }

    /**
     * Returns a button representation of the port selected.
     *
     * @param port button port of getRawButton()
     * @return new button representation for binding
     */
    public final Button getButton(int port) {
        return new Button(port);
    }

    /**
     * Returns a button representation of the port selected.
     *
     * @param port button port of getRawButton()
     * @param inverted if input should be reversed
     * @return new button representation for binding
     */
    public final Button getButton(int port, boolean inverted) {
        return new Button(port, inverted);
    }

    /**
     * Returns a button representation of an axis. Uses a threshold to tell
     * whether the axis is "pressed". Works with absolute value so anything the
     * has an implicit value abs(value) > threshold with return true.
     *
     * @param port axis port of getRawAxis()
     * @param threshold value axis needs to be over to count as pushed
     * @return button representation of axis as a button
     */
    public final Bindable.Button getAxisAsButton(final int port, final double threshold) {
        return new Bindable.Button("Axis " + port + " as button") {
            public boolean isPressed() {
                if(threshold > 0) {
                    return getRawAxis(port) > threshold;
                } else {
                    return getRawAxis(port) < threshold;
                }
            }
        };
    }

    /**
     * Returns an axis representation of an axis on a port.
     *
     * @param port axis port of getRawAxis()
     * @return axis representation of port
     */
    public final Axis getAxis(int port) {
        return new Axis(port);
    }

    /**
     * Returns an axis representation of an axis on a port.
     *
     * @param port axis port of getRawAxis()
     * @param reversed if input should be reversed
     * @return axis representation of port
     */
    public final Axis getAxis(int port, boolean reversed) {
        return new Axis(port, reversed);
    }

    /**
     * Returns an axis representation of an axis. If the button is pressed, it
     * sends +1, if not it sends 0.
     *
     * @param port axis port of getRawAxis()
     * @return axis representation of port
     */
    public final Bindable.Axis getButtonAsAxis(final int port) {
        return new Bindable.Axis("Button " + port + " as axis") {
            public double getValue() {
                return getRawButton(port) ? 1 : 0;
            }
        };
    }

    /**
     * Binds the port to a command. Abstraction of
     * {@code addWhenPressed(new Button(port), command)}.
     *
     * @param port button port of getRawButton()
     * @param command command to run
     */
    public final void bindWhenPressed(int port, Command command) {
        addWhenPressed(new Button(port), command);
    }

    /**
     * Binds the port to a command. Abstraction of
     * {@code addWhilePressed(new Button(port), command)}.
     *
     * @param port button port of getRawButton()
     * @param command command to run
     */
    public final void bindWhilePressed(int port, Command command) {
        addWhilePressed(new Button(port), command);
    }

    /**
     * Binds the port to a command. Abstraction of
     * {@code addWhenReleased(new Button(port), command)}.
     *
     * @param port button port of getRawButton()
     * @param command command to run
     */
    public final void bindWhenReleased(int port, Command command) {
        addWhenReleased(new Button(port), command);
    }

    /**
     * Binds the port to a command. Abstraction of
     * {@code addWhileReleased(new Button(port), command)}.
     *
     * @param port button port of getRawButton()
     * @param command command to run
     */
    public final void bindWhileReleased(int port, Command command) {
        addWhileReleased(new Button(port), command);
    }

    /**
     * Binds the port to a command. Abstraction of
     * {@code addAxis(new Axis(port), command)}.
     *
     * @param port axis port of getRawAxis()
     * @param axisBind binding to set
     */
    public final void bindAxis(int port, AxisBind axisBind) {
        addAxis(new Axis(port), axisBind);
    }
    
    public final void bindAxis(int port, AxisBind axisBind, Function function) {
        addAxis(new Axis(port), axisBind, function);
    }

    /**
     * Representation of a button to bind.
     */
    public final class Button extends Bindable.Button {

        private final int port;
        private final boolean inverted;

        /**
         * Creates button based on its port on the joystick.
         *
         * @param port button port of getRawButton()
         */
        public Button(int port) {
            this(port, false);
        }

        /**
         * Creates button based on its port on the joystick.
         *
         * @param port button port of getRawButton()
         * @param inverted whether input should be reversed
         */
        public Button(int port, boolean inverted) {
            super("Button on port " + port);
            this.port = port;
            this.inverted = inverted;
        }

        /**
         * Returns whether the button is currently pressed.
         *
         * @return if button is pressed
         */
        public boolean isPressed() {
            if (inverted) {
                return !getRawButton(port);
            } else {
                return getRawButton(port);
            }
        }
    }

    /**
     * Representation of an axis to bind.
     */
    public final class Axis extends Bindable.Axis {

        private final int port;
        private final boolean reversed;

        /**
         * Creates axis based on its port in getRawAxis().
         *
         * @param port axis port of getRawAxis()
         */
        public Axis(int port) {
            this(port, false);
        }

        /**
         * Creates axis based on its port in getRawAxis().
         *
         * @param port axis port of getRawAxis()
         * @param reversed whether the input is reversed
         */
        public Axis(int port, boolean reversed) {
            super("Axis on port " + port);
            this.port = port;
            this.reversed = reversed;
        }

        /**
         * Returns the value of the axis.
         *
         * @return axis value
         */
        public double getValue() {
            if (reversed) {
                return -getRawAxis(port);
            } else {
                return getRawAxis(port);
            }
        }
    }
}
