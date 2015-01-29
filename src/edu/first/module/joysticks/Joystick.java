package edu.first.module.joysticks;

import edu.first.identifiers.Input;
import edu.first.identifiers.Position;

/**
 * General interface that signifies that the class is a joystick that the user
 * controls functions with. Joysticks have {@link Axis Axises} and
 * {@link Button Buttons}. Other joystick functions are not part of this
 * interface, and should be implemented in their respective classes.
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public interface Joystick {

    /**
     * Returns an {@link Axis} that will return the distance that the axis is
     * at.
     *
     * @param port the axis to read
     * @return the axis object to get values from
     */
    public Axis getRawAxis(int port);

    /**
     * Returns the actual distance that the axis is at.
     *
     * @param port the axis to read
     * @return the value of the axis
     */
    public double getRawAxisValue(int port);

    /**
     * Returns an {@link Button} that will return the position of the button.
     *
     * @param port the button to read
     * @return the button object to get values from
     */
    public Button getRawButton(int port);

    /**
     * Returns the actual position that the button is in.
     *
     * @param port the button to read
     * @return the value of the button
     */
    public boolean getRawButtonValue(int port);

    /**
     * Axis object to get values from an axis on.
     */
    public static interface Axis extends Input {

        /**
         * Returns the actual distance that the axis is at.
         *
         * @return the value of the axis
         */
        @Override
        public double get();
    }

    /**
     * Button object to get values from a button on.
     */
    public static interface Button extends Position {

        /**
         * Returns the actual position that the button is.
         *
         * @return the value of the button
         */
        @Override
        public boolean getPosition();
    }
}
