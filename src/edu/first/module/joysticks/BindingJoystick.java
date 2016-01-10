package edu.first.module.joysticks;

import edu.first.command.Command;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import edu.first.identifiers.Output;

/**
 * A {@link JoystickModule} that can bind axises and buttons to commands and
 * outputs respectively. To check and perform the binds given through the
 * various methods, use {@link #doBinds()}.
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public class BindingJoystick extends JoystickModule {

    private final List<Bind> binds = new ArrayList<>();

    /**
     * Constructs the joystick with the {@link edu.wpi.first.wpilibj.Joystick}
     * object that this joystick gets input from.
     *
     * @param joystick the composing instance to get input from
     */
    protected BindingJoystick(edu.wpi.first.wpilibj.Joystick joystick) {
        super(joystick);
    }

    /**
     * Constructs the joystick with a port on the DriverStation.
     *
     * @param port channel in configuration of DriverStation
     */
    public BindingJoystick(int port) {
        super(port);
    }

    /**
     * Adds a single bind that binds one port to an output.
     *
     * @param port the axis to read
     * @param output binded output to set from axis
     */
    public final void addAxisBind(int port, Output output) {
        addAxisBind(getRawAxis(port), output);
    }

    /**
     * Adds a single bind that binds one axis to an output.
     *
     * @param axis the axis to read
     * @param output binded output to set from axis
     */
    public final void addAxisBind(Joystick.Axis axis, Output output) {
        addAxisBind(new AxisBind(axis, output));
    }

    /**
     * Adds a single bind that binds one axis to an output.
     *
     * @param axisBind bind to add
     */
    public final void addAxisBind(AxisBind axisBind) {
        addBind(axisBind);
    }

    /**
     * Adds a double bind that binds two axises to two outputs.
     *
     * @param axisBind bind to add
     */
    public final void addAxisBind(DualAxisBind axisBind) {
        addBind(axisBind);
    }

    /**
     * Adds a triple bind that binds three axises to three outputs.
     *
     * @param axisBind bind to add
     */
    public final void addAxisBind(TripleAxisBind axisBind) {
        addBind(axisBind);
    }

    /**
     * Adds a quadrupal bind that binds four axises to four outputs.
     *
     * @param axisBind bind to add
     */
    public final void addAxisBind(QuadrupalAxisBind axisBind) {
        addBind(axisBind);
    }

    /**
     * Adds a quintuple bind that binds five axises to five outputs.
     *
     * @param axisBind bind to add
     */
    public final void addAxisBind(QuintupleAxisBind axisBind) {
        addBind(axisBind);
    }

    /**
     * Adds a bind that will run in a loop as long as the button is pressed.
     *
     * @param port the button to read
     * @param runnable runs while button is pressed
     */
    public final void addWhilePressed(int port, Runnable runnable) {
        addWhilePressed(getRawButton(port), runnable);
    }

    /**
     * Adds a bind that will run in a loop as long as the button is pressed.
     *
     * @param button the button to read
     * @param runnable runs while button is pressed
     */
    public final void addWhilePressed(Button button, Runnable runnable) {
        addButtonBind(new WhilePressed(button, runnable));
    }

    /**
     * Adds a bind that will run in a loop as long as the button is not pressed.
     *
     * @param port the button to read
     * @param runnable runs while button is not pressed
     */
    public final void addWhileReleased(int port, Runnable runnable) {
        addWhileReleased(getRawButton(port), runnable);
    }

    /**
     * Adds a bind that will run in a loop as long as the button is not pressed.
     *
     * @param button the button to read
     * @param runnable runs while button is not pressed
     */
    public final void addWhileReleased(Button button, Runnable runnable) {
        addButtonBind(new WhileReleased(button, runnable));
    }

    /**
     * Adds a bind that will run once every time the button is pressed.
     *
     * @param port the button to read
     * @param runnable runs when button is pressed
     */
    public final void addWhenPressed(int port, Runnable runnable) {
        addWhenPressed(getRawButton(port), runnable);
    }

    /**
     * Adds a bind that will run once every time the button is pressed.
     *
     * @param button the button to read
     * @param runnable runs when button is pressed
     */
    public final void addWhenPressed(Button button, Runnable runnable) {
        addButtonBind(new WhenPressed(button, runnable));
    }

    /**
     * Adds a bind that will run once every time the button is released from a
     * pressed position.
     *
     * @param port the button to read
     * @param runnable runs when button is released
     */
    public final void addWhenReleased(int port, Runnable runnable) {
        addWhenReleased(getRawButton(port), runnable);
    }

    /**
     * Adds a bind that will run once every time the button is released from a
     * pressed position.
     *
     * @param button the button to read
     * @param runnable runs when button is released
     */
    public final void addWhenReleased(Button button, Runnable runnable) {
        addButtonBind(new WhenReleased(button, runnable));
    }

    /**
     * Adds a general button bind that takes input from a button.
     *
     * @param buttonBind bind to add
     */
    public final void addButtonBind(ButtonBind buttonBind) {
        addBind(buttonBind);
    }

    /**
     * Removes the specific bind from this joystick.
     *
     * @param b bind to remove
     */
    public final void removeBind(Bind b) {
        binds.remove(b);
    }

    /**
     * Removes all active binds from the joystick.
     */
    public final void clearBinds() {
        binds.clear();
    }

    /**
     * Adds any bind to the joystick. Although there is no restriction on what
     * can be added here, you should not be adding things that have nothing to
     * do with the joystick.
     *
     * @param b bind to add
     */
    public final void addBind(Bind b) {
        binds.add(b);
    }

    /**
     * Performs every bind, in the order they were given in the adding methods.
     */
    public final void doBinds() {
        Iterator<Bind> i = binds.iterator();
        while (i.hasNext()) {
            ((Bind) i.next()).doBind();
        }
    }

    /**
     * Returns a command that will add a bind to this joystick.
     *
     * @param bind the bind to add
     * @return command that does the operation
     */
    public final Command addBindCommand(final Bind bind) {
        return new Command() {
            @Override
            public void run() {
                addBind(bind);
            }
        };
    }
    
    /**
     * Adds a single bind that binds one port to an output.
     *
     * @param port the axis to read
     * @param output binded output to set from axis
     * @return command that does the operation
     */
    public final Command addAxisBindCommand(int port, Output output) {
        return addBindCommand(new AxisBind(getRawAxis(port), output));
    }

    /**
     * Adds a single bind that binds one axis to an output.
     *
     * @param axis the axis to read
     * @param output binded output to set from axis
     * @return command that does the operation
     */
    public final Command addAxisBindCommand(Joystick.Axis axis, Output output) {
        return addBindCommand(new AxisBind(axis, output));
    }

    /**
     * Adds a bind that will run in a loop as long as the button is pressed.
     *
     * @param port the button to read
     * @param runnable runs while button is pressed
     * @return command that does the operation
     */
    public final Command addWhilePressedCommand(int port, Runnable runnable) {
        return addBindCommand(new WhilePressed(getRawButton(port), runnable));
    }

    /**
     * Adds a bind that will run in a loop as long as the button is pressed.
     *
     * @param button the button to read
     * @param runnable runs while button is pressed
     * @return command that does the operation
     */
    public final Command addWhilePressedCommand(Button button, Runnable runnable) {
        return addBindCommand(new WhilePressed(button, runnable));
    }

    /**
     * Adds a bind that will run in a loop as long as the button is not pressed.
     *
     * @param port the button to read
     * @param runnable runs while button is not pressed
     * @return command that does the operation
     */
    public final Command addWhileReleasedCommand(int port, Runnable runnable) {
        return addBindCommand(new WhileReleased(getRawButton(port), runnable));
    }

    /**
     * Adds a bind that will run in a loop as long as the button is not pressed.
     *
     * @param button the button to read
     * @param runnable runs while button is not pressed
     * @return command that does the operation
     */
    public final Command addWhileReleasedCommand(Button button, Runnable runnable) {
        return addBindCommand(new WhileReleased(button, runnable));
    }

    /**
     * Adds a bind that will run once every time the button is pressed.
     *
     * @param port the button to read
     * @param runnable runs when button is pressed
     * @return command that does the operation
     */
    public final Command addWhenPressedCommand(int port, Runnable runnable) {
        return addBindCommand(new WhenPressed(getRawButton(port), runnable));
    }

    /**
     * Adds a bind that will run once every time the button is pressed.
     *
     * @param button the button to read
     * @param runnable runs when button is pressed
     * @return command that does the operation
     */
    public final Command addWhenPressedCommand(Button button, Runnable runnable) {
        return addBindCommand(new WhenPressed(button, runnable));
    }

    /**
     * Adds a bind that will run once every time the button is released from a
     * pressed position.
     *
     * @param port the button to read
     * @param runnable runs when button is released
     * @return command that does the operation
     */
    public final Command addWhenReleasedCommand(int port, Runnable runnable) {
        return addBindCommand(new WhenReleased(getRawButton(port), runnable));
    }

    /**
     * Adds a bind that will run once every time the button is released from a
     * pressed position.
     *
     * @param button the button to read
     * @param runnable runs when button is released
     * @return command that does the operation
     */
    public final Command addWhenReleasedCommand(Button button, Runnable runnable) {
        return addBindCommand(new WhenReleased(button, runnable));
    }

    /**
     * Returns a command that will remove a bind from this joystick.
     *
     * @param bind the bind to remove
     * @return command that does the operation
     */
    public final Command removeBindCommand(final Bind bind) {
        return new Command() {
            @Override
            public void run() {
                removeBind(bind);
            }
        };
    }

    /**
     * General interface describing that a bind performs some kind of action to
     * complete the bind, or "do bind". Even though it is not enforced, binds
     * should have <i>something</i> to do with the joystick.
     */
    protected static interface Bind {

        /**
         * Performs the action of the bind.
         */
        public void doBind();
    }

    /**
     * Single-axis bind that binds one axis to one output.
     */
    public static final class AxisBind implements Bind {

        private final Joystick.Axis axis;
        private final Output output;

        /**
         * Construct the bind using its axis and output.
         *
         * @param axis axis to get input from
         * @param output object to set axis values to
         */
        public AxisBind(Axis axis, Output output) {
            this.axis = axis;
            this.output = output;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void doBind() {
            output.set(axis.get());
        }
    }

    /**
     * Dual-axis bind that binds two axises to two outputs.
     */
    public static abstract class DualAxisBind implements Bind {

        private final Joystick.Axis axis1, axis2;

        /**
         * Construct the bind using its axises and outputs.
         *
         * @param axis1 first axis
         * @param axis2 second axis
         */
        public DualAxisBind(Axis axis1, Axis axis2) {
            this.axis1 = axis1;
            this.axis2 = axis2;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public final void doBind() {
            doBind(axis1.get(), axis2.get());
        }

        /**
         * Does the action of the bind using the two axis outputs.
         *
         * @param axis1 first axis
         * @param axis2 second axis
         */
        public abstract void doBind(double axis1, double axis2);
    }

    /**
     * Triple-axis bind that binds three axises to three outputs.
     */
    public static abstract class TripleAxisBind implements Bind {

        private final Joystick.Axis axis1, axis2, axis3;

        /**
         * Construct the bind using its axises and outputs.
         *
         * @param axis1 first axis
         * @param axis2 second axis
         * @param axis3 third axis
         */
        public TripleAxisBind(Axis axis1, Axis axis2, Axis axis3) {
            this.axis1 = axis1;
            this.axis2 = axis2;
            this.axis3 = axis3;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public final void doBind() {
            doBind(axis1.get(), axis2.get(), axis3.get());
        }

        /**
         * Does the action of the bind using the three axis outputs.
         *
         * @param axis1 first axis
         * @param axis2 second axis
         * @param axis3 third axis
         */
        public abstract void doBind(double axis1, double axis2, double axis3);
    }

    /**
     * Quadrupal-axis bind that binds four axises to four outputs.
     */
    public static abstract class QuadrupalAxisBind implements Bind {

        private final Joystick.Axis axis1, axis2, axis3, axis4;

        /**
         * Construct the bind using its axises and outputs.
         *
         * @param axis1 first axis
         * @param axis2 second axis
         * @param axis3 third axis
         * @param axis4 forth axis
         */
        public QuadrupalAxisBind(Axis axis1, Axis axis2, Axis axis3, Axis axis4) {
            this.axis1 = axis1;
            this.axis2 = axis2;
            this.axis3 = axis3;
            this.axis4 = axis4;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public final void doBind() {
            doBind(axis1.get(), axis2.get(), axis3.get(), axis4.get());
        }

        /**
         * Does the action of the bind using the four axis outputs.
         *
         * @param axis1 first axis
         * @param axis2 second axis
         * @param axis3 third axis
         * @param axis4 forth axis
         */
        public abstract void doBind(double axis1, double axis2, double axis3, double axis4);
    }

    /**
     * Quintuple-axis bind that binds five axises to five outputs.
     */
    public static abstract class QuintupleAxisBind implements Bind {

        private final Joystick.Axis axis1, axis2, axis3, axis4, axis5;

        /**
         * Construct the bind using its axises and outputs.
         *
         * @param axis1 first axis
         * @param axis2 second axis
         * @param axis3 third axis
         * @param axis4 forth axis
         * @param axis5 fifth axis
         */
        public QuintupleAxisBind(Axis axis1, Axis axis2, Axis axis3, Axis axis4, Axis axis5) {
            this.axis1 = axis1;
            this.axis2 = axis2;
            this.axis3 = axis3;
            this.axis4 = axis4;
            this.axis5 = axis5;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public final void doBind() {
            doBind(axis1.get(), axis2.get(), axis3.get(), axis4.get(), axis5.get());
        }

        /**
         * Does the action of the bind using the five axis outputs.
         *
         * @param axis1 first axis
         * @param axis2 second axis
         * @param axis3 third axis
         * @param axis4 forth axis
         * @param axis5 fifth axis
         */
        public abstract void doBind(double axis1, double axis2, double axis3, double axis4, double axis5);
    }

    /**
     * General button bind that binds one button to a {@link Runnable}.
     */
    public static abstract class ButtonBind implements Bind {

        private final Joystick.Button button;
        private final Runnable runnable;

        /**
         * Constructs the button bind using the button and the binded
         * {@link Runnable}.
         *
         * @param button the button to read
         * @param runnable runs in accordance to the bind
         */
        public ButtonBind(Button button, Runnable runnable) {
            this.button = button;
            this.runnable = runnable;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public final void doBind() {
            doBind(button.getPosition(), runnable);
        }

        /**
         * Performs the action of the button bind.
         *
         * @param pos current position of the button
         * @param runnable runs in accordance to the bind
         */
        public abstract void doBind(boolean pos, Runnable runnable);
    }

    /**
     * Button bind that runs while the button is pressed.
     */
    public static final class WhilePressed extends ButtonBind {

        /**
         * Constructs the button bind using the button and the binded
         * {@link Runnable}.
         *
         * @param button the button to read
         * @param runnable runs in accordance to the bind
         */
        public WhilePressed(Button button, Runnable runnable) {
            super(button, runnable);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void doBind(boolean pos, Runnable runnable) {
            if (pos) {
                runnable.run();
            }
        }
    }

    /**
     * Button bind that runs while the button is not pressed.
     */
    public static final class WhileReleased extends ButtonBind {

        /**
         * Constructs the button bind using the button and the binded
         * {@link Runnable}.
         *
         * @param button the button to read
         * @param runnable runs in accordance to the bind
         */
        public WhileReleased(Button button, Runnable runnable) {
            super(button, runnable);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void doBind(boolean pos, Runnable runnable) {
            if (!pos) {
                runnable.run();
            }
        }
    }

    /**
     * Button bind that runs once every time the button is pressed.
     */
    public static final class WhenPressed extends ButtonBind {

        private boolean prev;

        /**
         * Constructs the button bind using the button and the binded
         * {@link Runnable}.
         *
         * @param button the button to read
         * @param runnable runs in accordance to the bind
         */
        public WhenPressed(Button button, Runnable runnable) {
            super(button, runnable);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void doBind(boolean pos, Runnable runnable) {
            if (!prev && pos) {
                runnable.run();
                prev = true;
            } else if (!pos) {
                prev = false;
            }
        }
    }

    /**
     * Button bind that runs once every time the button is released from being
     * pressed.
     */
    public static final class WhenReleased extends ButtonBind {

        private boolean prev;

        /**
         * Constructs the button bind using the button and the binded
         * {@link Runnable}.
         *
         * @param button the button to read
         * @param runnable runs in accordance to the bind
         */
        public WhenReleased(Button button, Runnable runnable) {
            super(button, runnable);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void doBind(boolean pos, Runnable runnable) {
            if (prev && !pos) {
                runnable.run();
                prev = false;
            } else if (pos) {
                prev = true;
            }
        }
    }
}
