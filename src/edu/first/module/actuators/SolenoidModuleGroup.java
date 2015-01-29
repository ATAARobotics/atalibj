package edu.first.module.actuators;

import edu.first.lang.OutOfSyncException;
import edu.first.module.subsystems.Subsystem;

/**
 * A group of solenoids. Performs actions on all of them. This group is a
 * {@link Subsystem}, meaning that it will perform the {@link Module} operations
 * on all of its elements.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class SolenoidModuleGroup extends Subsystem implements Solenoid {

    private final SolenoidGroup group;

    /**
     * Constructs the group using an array of all the elements to use.
     *
     * @throws NullPointerException when array is null
     * @param group all elements to apply things to
     */
    public SolenoidModuleGroup(SolenoidModule[] group) {
        super(group);
        this.group = new SolenoidGroup(group);
    }

    /**
     * Sets the position of every solenoid.
     *
     * @param pos position to set
     * @see Solenoid#setPosition(boolean)
     */
    @Override
    public void setPosition(boolean pos) {
        group.setPosition(pos);
    }

    /**
     * Returns the state that <i>every</i> solenoid is in.
     *
     * @throws OutOfSyncException when all solenoids are not in the same state
     * @return state of all solenoids
     * @see Solenoid#getPosition()
     */
    @Override
    public boolean getPosition() throws OutOfSyncException {
        return group.getPosition();
    }
}
