package edu.first.module.speedcontroller;

import edu.wpi.first.wpilibj.Relay;

/**
 * Spike relay that controls multiple spike relays, doing the same thing to all
 * of them.
 *
 * @author Joel Gallant <joelgallant236@gmail.com>
 */
public class GroupSpikeRelay implements SpikeRelay {

    private final SpikeRelay[] spikeRelays;

    /**
     * Constructs the group using an array of all the spike relays to use. There
     * must be at least one spike relay in the array.
     * ({@link IllegalStateException})
     *
     * @param spikeRelays spike relays to control
     */
    public GroupSpikeRelay(SpikeRelay[] spikeRelays) {
        if (spikeRelays == null || spikeRelays.length == 0) {
            throw new IllegalStateException("GroupSpikeRelay with no spike relays");
        }
        this.spikeRelays = spikeRelays;
    }

    /**
     * Sets the value of all of the spike relays.
     *
     * @param value setting of the spike relays
     */
    public final void set(Relay.Value value) {
        for (int x = 0; x < spikeRelays.length; x++) {
            spikeRelays[x].set(value);
        }
    }

    /**
     * Sets the direction of all the spike relays.
     *
     * @param direction direction of the spike relays
     */
    public final void setDirection(Relay.Direction direction) {
        for (int x = 0; x < spikeRelays.length; x++) {
            spikeRelays[x].setDirection(direction);
        }
    }

    /**
     * Returns the unified setting of all the spike relays. If any of them are
     * not the same as any other, an {@link IllegalStateException} is thrown.
     *
     * @return the setting of all the spike relays
     */
    public final Relay.Value get() {
        Relay.Value value = null;
        for (int x = 0; x < spikeRelays.length; x++) {
            Relay.Value v = value;
            value = spikeRelays[x].get();
            if (v != null && value != null) {
                if (!v.equals(value)) {
                    throw new IllegalStateException("Relays in GroupSpikeRelay are out of sync");
                }
            }
        }
        return value;
    }
}