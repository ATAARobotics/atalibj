package edu.first.commands.common;

import edu.first.command.Command;
import edu.first.module.actuators.SpikeRelay;

/**
 * Switches the state of the spike relay.
 *
 * @see SpikeRelay#set(edu.first.module.actuators.SpikeRelay.Direction)
 * @since June 13 13
 * @author Joel Gallant
 */
public final class SetSpikeRelay implements Command {

    private final SpikeRelay relay;
    private final SpikeRelay.Direction direction;

    /**
     * Constructs the command using the relay to set and which direction to set
     * it to.
     *
     * @param relay the spike relay to set
     * @param direction state to change relay to
     */
    public SetSpikeRelay(SpikeRelay relay, SpikeRelay.Direction direction) {
        this.relay = relay;
        this.direction = direction;
    }

    /**
     * Changes the state of the spike relay.
     */
    public void run() {
        relay.set(direction);
    }
}
