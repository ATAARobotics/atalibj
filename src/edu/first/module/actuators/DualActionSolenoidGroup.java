package edu.first.module.actuators;

import edu.first.lang.OutOfSyncException;

/**
 * A group of dual action solenoids. Performs actions on all solenoids.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class DualActionSolenoidGroup implements DualActionSolenoid {

    private final DualActionSolenoid[] group;

    /**
     * Constructs the group using an array of all the elements to use.
     *
     * @throws NullPointerException when array is null
     * @param group all elements to apply things to
     */
    public DualActionSolenoidGroup(DualActionSolenoid[] group) {
        if (group == null) {
            throw new NullPointerException("Null group provided");
        }
        this.group = group;
    }

    /**
     * Sets the direction of all the solenoids.
     *
     * @param direction which direction to set the solenoids
     * @see
     * DualActionSolenoid#set(edu.first.module.actuators.DualActionSolenoid.Direction)
     */
    public void set(Direction direction) {
        for (int x = 0; x < group.length; x++) {
            group[x].set(direction);
        }
    }

    /**
     * Returns the state that <i>every</i> solenoid is in. They must be in the
     * same state.
     *
     * @throws OutOfSyncException when all solenoids are not in the same state
     * @return state of all solenoids
     * @see DualActionSolenoid#get()
     */
    public Direction get() {
        Direction d = null;
        for (int x = 0; x < group.length; x++) {
            Direction b = group[x].get();
            if (d == null) {
                d = b;
            } else {
                if (!d.equals(b)) {
                    throw new OutOfSyncException("Group was out of sync (" + d + " != " + b + ")");
                }
            }
        }
        return d;
    }

    /**
     * Reverses the direction of all the solenoids.
     *
     * @throws OutOfSyncException when all solenoids are not in the same state
     * @see DualActionSolenoid#reverse()
     */
    public void reverse() {
        Direction current = get();
        if (current.equals(Direction.LEFT)) {
            set(Direction.RIGHT);
        } else if (current.equals(Direction.RIGHT)) {
            set(Direction.LEFT);
        }
    }

    /**
     * Turns off every solenoid in the group.
     *
     * @see DualActionSolenoid#turnOff()
     */
    public void turnOff() {
        for (int x = 0; x < group.length; x++) {
            group[x].turnOff();
        }
    }
}
