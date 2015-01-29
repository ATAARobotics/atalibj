package edu.first.module.actuators;

import edu.first.lang.OutOfSyncException;

/**
 * A group of spike relays. Performs actions on all of them.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class SpikeRelayGroup implements SpikeRelay {

    private final SpikeRelay[] group;

    /**
     * Constructs the group using an array of all the elements to use.
     *
     * @throws NullPointerException when array is null
     * @param group all elements to apply things to
     */
    public SpikeRelayGroup(SpikeRelay[] group) {
        this.group = group;
    }

    /**
     * Sets the direction of every relay.
     *
     * @param d direction to set every spike relay
     * @see SpikeRelay#set(edu.first.module.actuators.SpikeRelay.Direction)
     */
    @Override
    public void set(Direction d) {
        for (SpikeRelay group1 : group) {
            group1.set(d);
        }
    }

    /**
     * Returns the direction that <i>every</i> relay is in.
     *
     * @throws OutOfSyncException when all relays are not in the same state
     * @return state of all relays
     * @see SpikeRelay#getDirection()
     */
    @Override
    public Direction getDirection() throws OutOfSyncException {
        Direction pos = null;
        for (int x = 0; x < group.length; x++) {
            if (x == 0) {
                pos = group[x].getDirection();
            } else {
                Direction b = group[x].getDirection();
                if (b != pos) {
                    throw new OutOfSyncException("Group was out of sync (" + b + " != " + pos + ")");
                }
            }
        }
        return pos;
    }
}
