package edu.first.module.actuators;

import edu.first.lang.OutOfSyncException;
import edu.first.module.Module;
import edu.first.module.subsystems.Subsystem;

/**
 * A group of dual action solenoids. Performs actions on all solenoids. This
 * group is a {@link Subsystem}, meaning that it will perform the {@link Module}
 * operations on all of its elements.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class DualActionSolenoidModuleGroup extends Subsystem implements DualActionSolenoid {

    private final DualActionSolenoidGroup group;

    /**
     * Constructs the group using an array of all the elements to use.
     *
     * @param group all elements to apply things to
     */
    public DualActionSolenoidModuleGroup(DualActionSolenoidModule[] group) {
        super(group);
        this.group = new DualActionSolenoidGroup(group);
    }

    /**
     * Sets the direction of all the solenoids.
     *
     * @param direction which direction to set the solenoids
     * @see
     * DualActionSolenoid#set(edu.first.module.actuators.DualActionSolenoid.Direction)
     */
    @Override
    public void set(Direction direction) {
        group.set(direction);
    }

    /**
     * Returns the state that <i>every</i> solenoid is in. They must be in the
     * same state.
     *
     * @throws OutOfSyncException when all solenoids are not in the same state
     * @return state of all solenoids
     * @see DualActionSolenoid#get()
     */
    @Override
    public Direction get() {
        return group.get();
    }

    /**
     * Reverses the direction of all the solenoids.
     *
     * @throws OutOfSyncException when all solenoids are not in the same state
     * @see DualActionSolenoid#reverse()
     */
    @Override
    public void reverse() {
        group.reverse();
    }

    /**
     * Turns off every solenoid in the group.
     *
     * @see DualActionSolenoid#turnOff()
     */
    @Override
    public void turnOff() {
        group.turnOff();
    }
}
