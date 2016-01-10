package edu.first.module.actuators;

import edu.first.lang.OutOfSyncException;
import edu.first.module.subsystems.Subsystem;

/**
 * A group of spike relays. Performs actions on all of them. This group is a
 * {@link Subsystem}, meaning that it will perform the {@link Module} operations
 * on all of its elements.
 *
 * @since June 15 13
 * @author Joel Gallant
 */
public class SpikeRelayModuleGroup extends Subsystem implements SpikeRelay {

    private final SpikeRelayGroup group;

    /**
     * Constructs the group using an array of all the elements to use.
     *
     * @throws NullPointerException when array is null
     * @param modules all elements to apply things to
     */
    public SpikeRelayModuleGroup(SpikeRelayModule[] modules) {
        super(modules);
        this.group = new SpikeRelayGroup(modules);
    }

    /**
     * Sets the direction of every relay.
     *
     * @param d direction to set every spike relay
     * @see SpikeRelay#set(edu.first.module.actuators.SpikeRelay.Direction)
     */
    @Override
    public void set(Direction d) {
        group.set(d);
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
        return group.getDirection();
    }
}
