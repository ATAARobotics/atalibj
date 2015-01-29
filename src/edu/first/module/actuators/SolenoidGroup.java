package edu.first.module.actuators;

import edu.first.lang.OutOfSyncException;

/**
 * A group of solenoids. Performs actions on all of them.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class SolenoidGroup implements Solenoid {

    private final Solenoid[] group;

    /**
     * Constructs the group using an array of all the elements to use.
     *
     * @throws NullPointerException when array is null
     * @param group all elements to apply things to
     */
    public SolenoidGroup(Solenoid[] group) {
        this.group = group;
    }

    /**
     * Sets the position of every solenoid.
     *
     * @param pos position to set
     * @see Solenoid#setPosition(boolean)
     */
    @Override
    public void setPosition(boolean pos) {
        for (Solenoid group1 : group) {
            group1.setPosition(pos);
        }
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
        boolean pos = false;
        for (int x = 0; x < group.length; x++) {
            if (x == 0) {
                pos = group[x].getPosition();
            } else {
                boolean b = group[x].getPosition();
                if (b != pos) {
                    throw new OutOfSyncException("Group was out of sync (" + b + " != " + pos + ")");
                }
            }
        }
        return pos;
    }
}
