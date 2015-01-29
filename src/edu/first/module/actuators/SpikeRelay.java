package edu.first.module.actuators;

/**
 * General interface that signifies that the class controls a VEX spike relay.
 * Has two directions, as well as an off position. The directions change which
 * way the current goes. This allows off, full forward, or full reverse control
 * of motors without variable speed.
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public interface SpikeRelay {

    /**
     * Sets the relay to a direction.
     *
     * @param d the direction of the relay
     */
    public void set(Direction d);

    /**
     * Returns which direction that the relay is set to.
     *
     * @return the direction of the relay
     */
    public Direction getDirection();

    /**
     * Enum with the three different kinds of directions.
     */
    public static enum Direction {

        FORWARDS, BACKWARDS, OFF;
    }
}
