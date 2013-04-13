package edu.first.binding;

import edu.first.bindings.AxisBind;
import edu.first.command.Command;
import edu.first.identifiers.Function;
import edu.wpi.first.wpilibj.networktables2.util.List;

/**
 * Object representing a joystick (although not explictly) that can bind axises
 * and buttons to commands and outputs respectively. To check and perform the
 * binds given through the various methods, use {@link Bindable#doBinds()}.
 *
 * @author Joel Gallant
 */
public abstract class Bindable {

    /**
     * A non-type safe (be careful!) list of {@link BindAction bind actions}.
     */
    protected final List binds = new List();

    /**
     * Adds a bind action object to the current binds. Will be run in
     * {@link Bindable#doBinds()}.
     *
     * @param action bind to add to this bindable class
     */
    public final void addBind(BindAction action) {
        binds.add(action);
    }

    /**
     * Adds a bind that will run when the button is pressed. Runs once every
     * time it is pressed. Has to be released to run again.
     *
     * @param button bind button that checks if the button is pressed
     * @param command running command that runs when the button is pressed
     */
    public final void addWhenPressed(Button button, Command command) {
        addBind(new WhenPressed(button, command));
    }

    /**
     * Adds a bind that will run in a loop as long as the button is pressed.
     *
     * @param button bind button that checks if the button is pressed
     * @param command running command that runs while the button is pressed
     */
    public final void addWhilePressed(Button button, Command command) {
        addBind(new WhilePressed(button, command));
    }

    /**
     * Adds a bind that will run when the button is released. Runs once every
     * time it is released. Has to be pressed to run again.
     *
     * @param button bind button that checks if the button is pressed
     * @param command running command that runs when the button is released
     */
    public final void addWhenReleased(Button button, Command command) {
        addBind(new WhenReleased(button, command));
    }

    /**
     * Adds a bind that will run in a loop as long as the button is released.
     *
     * @param button bind button that checks if the button is pressed
     * @param command running command that runs while the button is released
     */
    public final void addWhileReleased(Button button, Command command) {
        addBind(new WhileReleased(button, command));
    }

    /**
     * Adds an axis to set from an {@link Axis} object.
     *
     * @param axis axis to set
     * @param bind object to get values from
     */
    public final void addAxis(Axis axis, AxisBind bind) {
        addBind(new SetAxis(bind, axis));
    }

    /**
     * Adds an axis to set from an {@link Axis} object.
     *
     * @param axis axis to set
     * @param bind object to get values from
     * @param function function to apply to the values
     */
    public final void addAxis(Axis axis, AxisBind bind, Function function) {
        addBind(new SetAxis(bind, axis, function));
    }

    /**
     * Removes a bind from the bind list.
     *
     * @param action bind object
     */
    public final void removeBind(BindAction action) {
        binds.remove(action);
    }

    /**
     * Removes all the binds.
     */
    public final void removeAllBinds() {
        binds.clear();
    }

    /**
     * Performs the actions of all the binds in this object.
     */
    public final void doBinds() {
        for (int x = 0; x < binds.size(); x++) {
            if (binds.get(x) instanceof BindAction) {
                ((BindAction) binds.get(x)).doBind();
            }
        }
    }

    /**
     * Returns a string that contains all of the IDs of the binds in one string
     * separated by spaces.
     *
     * @return all IDs of binds together
     */
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        for (int x = 0; x < binds.size(); x++) {
            buffer.append(binds.get(x).toString()).append(" ");
        }
        return buffer.toString();
    }

    private static class BindingObject {

        private final String id;

        public BindingObject(String id) {
            this.id = id;
        }

        public String toString() {
            return id;
        }
    }

    /**
     * A simple class that has an ID and can be referenced as a "button",
     * meaning it is either pressed or not.
     */
    public static abstract class Button extends BindingObject {

        /**
         * Constructs the button with an ID.
         *
         * @param id unique way to reference the button
         */
        public Button(String id) {
            super(id);
        }

        /**
         * Returns whether the button is currently pressed.
         *
         * @return if button is pressed
         */
        public abstract boolean isPressed();
    }

    /**
     * A simple class that has an ID and can be referenced as an "axis", meaning
     * it returns an axis value as a double.
     */
    public static abstract class Axis extends BindingObject {

        /**
         * Constructs the axis with an ID.
         *
         * @param id unique way to reference the axis
         */
        public Axis(String id) {
            super(id);
        }

        /**
         * Returns the value of the axis.
         *
         * @return value of the axis
         */
        public abstract double getValue();
    }

    /**
     * An action to perform when a button is pressed.
     */
    public static abstract class BindAction {

        private BindAction() {
        }

        /**
         * Performs the action to run when the bind is run.
         */
        public abstract void doBind();
    }

    /**
     * Class representing bind actions that have a button underneath.
     */
    public static abstract class BindsActionWithButton extends BindAction {

        /**
         * Button to use.
         */
        public final Button button;

        private BindsActionWithButton(Button button) {
            this.button = button;
        }

        /**
         * Returns the ID given of the button.
         *
         * @return ID of button
         */
        public String toString() {
            return button.toString();
        }
    }

    /**
     * Class representing bind actions that have an axis underneath.
     */
    public static abstract class BindsActionWithAxis extends BindAction {

        /**
         * Axis to use.
         */
        public final Axis axis;

        private BindsActionWithAxis(Axis axis) {
            this.axis = axis;
        }

        /**
         * Returns the ID given of the axis.
         *
         * @return ID of axis
         */
        public String toString() {
            return axis.toString();
        }
    }

    /**
     * An button bind that runs when the button is pressed, and only once every
     * time it is pressed.
     */
    public static final class WhenPressed extends BindsActionWithButton {

        private final Command bind;
        private boolean wasPressed = false;

        /**
         * Constructs the bind using the button to use and the command to run
         * when it is pressed.
         *
         * @param button button to check if it is pressed
         * @param bind command to run when button is pressed
         */
        public WhenPressed(Button button, Command bind) {
            super(button);
            this.bind = bind;
        }

        public void doBind() {
            if (button.isPressed() && !wasPressed) {
                bind.run();
                wasPressed = true;
            } else if (!button.isPressed()) {
                wasPressed = false;
            }
        }
    }

    /**
     * A button bind that runs every time the button is pressed, no matter if it
     * was run before.
     */
    public static final class WhilePressed extends BindsActionWithButton {

        private final Command bind;

        /**
         * Constructs the bind using the button and the command to run while it
         * is pressed.
         *
         * @param button button to check if it is pressed
         * @param bind command to run while button is pressed
         */
        public WhilePressed(Button button, Command bind) {
            super(button);
            this.bind = bind;
        }

        public void doBind() {
            if (button.isPressed()) {
                bind.run();
            }
        }
    }

    /**
     * An button bind that runs when the button is released, and only once every
     * time it is released.
     */
    public static final class WhenReleased extends BindsActionWithButton {

        private final Command bind;
        private boolean wasPressed = false;

        /**
         * Constructs the bind using the button to use and the command to run
         * when it is released.
         *
         * @param button button to check if it is released
         * @param bind command to run when button is released
         */
        public WhenReleased(Button button, Command bind) {
            super(button);
            this.bind = bind;
        }

        public void doBind() {
            if (!button.isPressed() && wasPressed) {
                bind.run();
                wasPressed = false;
            } else if (button.isPressed()) {
                wasPressed = true;
            }
        }
    }

    /**
     * A button bind that runs every time the button is released, no matter if
     * it was run before.
     */
    public static final class WhileReleased extends BindsActionWithButton {

        private final Command bind;

        /**
         * Constructs the bind using the button and the command to run while it
         * is released.
         *
         * @param button button to check if it is released
         * @param bind command to run while button is released
         */
        public WhileReleased(Button button, Command bind) {
            super(button);
            this.bind = bind;
        }

        public void doBind() {
            if (!button.isPressed()) {
                bind.run();
            }
        }
    }

    /**
     * Axis bind that sets it to a value when run.
     */
    public static final class SetAxis extends BindsActionWithAxis {

        private Function function = new Function.DefaultFunction();
        private final AxisBind axisBind;

        /**
         * Constructs the bind using an {@link AxisBind} object.
         *
         * @param axisBind bind that will be set
         * @param axis axis to get value from
         */
        public SetAxis(AxisBind axisBind, Axis axis) {
            super(axis);
            this.axisBind = axisBind;
        }

        /**
         * Constructs the bind using an {@link AxisBind} object.
         *
         * @param axisBind bind that will be set
         * @param axis axis to get value from
         * @param function function to apply to the value
         */
        public SetAxis(AxisBind axisBind, Axis axis, Function function) {
            super(axis);
            this.axisBind = axisBind;
            this.function = function;
        }

        public void doBind() {
            axisBind.set(function.apply(axis.getValue()));
        }
    }
}
