package edu.first.module.actuators;

import edu.first.util.Enum;

/**
 * General interface representing dual action solenoids. Dual action solenoids
 * have two sides that change the direction of airflow (left or right).
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public interface DualActionSolenoid {

    /**
     * Sets the direction of the solenoid.
     *
     * @param direction which direction to set the solenoid
     */
    public void set(Direction direction);

    /**
     * Returns in which direction the solenoid is currently on. If both sides
     * are on, this method will return {@link Direction#OFF} because they will
     * have the same effect.
     *
     * @return current direction of solenoid
     */
    public Direction get();

    /**
     * Reverses the direction of the solenoid. This means LEFT -> RIGHT or RIGHT
     * -> LEFT.
     */
    public void reverse();

    /**
     * Turns both ends of the solenoid off.
     */
    public void turnOff();

    /**
     * Enum representing the directions of a dual action solenoid.
     */
    public static final class Direction extends Enum {

        /**
         * The left side of the solenoid is on.
         */
        public static final Direction LEFT = new Direction("LEFT");
        /**
         * The right side of the solenoid is on.
         */
        public static final Direction RIGHT = new Direction("RIGHT");
        /**
         * Both solenoids are off.
         */
        public static final Direction OFF = new Direction("OFF");

        private Direction(String name) {
            super(name);
        }
    }
}
