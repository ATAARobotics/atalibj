package edu.first.module.actuators;

import edu.first.module.Module;
import edu.wpi.first.wpilibj.Relay;

/**
 * Controls a VEX spike relay. Has two directions, as well as an off position.
 * The directions change which way the current goes. This allows off, full
 * forward, or full reverse control of motors without variable speed.
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public class SpikeRelayModule extends Module.StandardModule implements SpikeRelay {

    private final Relay relay;

    /**
     * Constructs the relay with the {@link Relay} object that this relay
     * controls.
     *
     * @throws NullPointerException when relay is null
     * @param relay the composing instance which perform the functions
     */
    protected SpikeRelayModule(Relay relay) {
        if(relay == null) {
            throw new NullPointerException("Null relay given");
        }
        this.relay = relay;
    }

    /**
     * Constructs the relay with the channel on the digital module.
     *
     * @param channel the channel on the digital sidecar
     */
    public SpikeRelayModule(int channel) {
        this(new Relay(channel));
    }

    /**
     * {@inheritDoc}
     */
    protected void enableModule() {
    }

    /**
     * {@inheritDoc}
     *
     * <p> Turns the spike relay off.
     */
    protected void disableModule() {
        set(Direction.OFF);
    }

    /**
     * {@inheritDoc}
     */
    public void init() {
    }

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException when module is not enabled
     */
    public final void set(Direction d) {
        ensureEnabled();
        relay.set(convertValue(d));
    }

    /**
     * {@inheritDoc}
     */
    public Direction getDirection() {
        return convertDirection(relay.get());
    }

    private static Direction convertDirection(Relay.Value v) {
        if (v == Relay.Value.kForward) {
            return Direction.FORWARDS;
        } else if (v == Relay.Value.kReverse) {
            return Direction.BACKWARDS;
        } else {
            return Direction.OFF;
        }
    }

    private static Relay.Value convertValue(Direction d) {
        if (d == Direction.FORWARDS) {
            return Relay.Value.kForward;
        } else if (d == Direction.BACKWARDS) {
            return Relay.Value.kReverse;
        } else {
            return Relay.Value.kOff;
        }
    }
}
