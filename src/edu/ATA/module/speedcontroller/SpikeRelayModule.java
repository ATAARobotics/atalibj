package edu.ATA.module.speedcontroller;

import edu.ATA.module.Module;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 * Module that represents spike relays. When enabled, is capable of setting the
 * value of the relay, but when it is disabled, turns off.
 *
 * @author Joel Gallant
 */
public class SpikeRelayModule extends ForwardingSpikeRelay implements Module.DisableableModule {

    private boolean enabled;

    /**
     * Constructs the object by using composition, using the given relay object
     * to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    public SpikeRelayModule(Relay spikeRelay) {
        super(spikeRelay);
    }

    /**
     * Turns the relay off and prevents it from being turned on.
     *
     * @return if module was successfully disabled
     */
    public boolean disable() {
        super.set(OFF);
        return !(enabled = false);
    }

    /**
     * Enables the module and lets the relay be set to any value.
     *
     * @return if module was successfully enabled
     */
    public boolean enable() {
        return (enabled = true);
    }

    /**
     * Returns whether or not the module is currently enabled. If it is not, the
     * {@code set()} method will not do anything.
     *
     * @return if module is enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * If the module is enabled, sets the relay state.
     *
     * <p> Valid values depend on which directions of the relay are controlled
     * by the object.
     *
     * <p> When set to kBothDirections, the relay can be set to any of the four
     * states: 0v-0v, 12v-0v, 0v-12v, 12v-12v.
     *
     * <p> When set to forwards only or reverse only, you can specify the
     * constant for the direction or you can simply specify off and on. Using
     * only off and on is recommended.
     *
     * @param value the state to set the relay
     */
    public void set(Value value) {
        if (isEnabled()) {
            super.set(value);
        }
    }
}

/**
 * Forwarding class, as described in Effective Java: Second Edition, Item 16.
 * Forwards {@link edu.wpi.first.wpilibj.Relay}.
 *
 * @author Joel Gallant
 */
class ForwardingSpikeRelay implements SpikeRelay {

    private final Relay spikeRelay;

    /**
     * Constructs the object by using composition, using the given relay object
     * to control methods in this class.
     *
     * @param controller actual underlying object used
     */
    ForwardingSpikeRelay(Relay spikeRelay) {
        this.spikeRelay = spikeRelay;
    }

    /**
     * Set the relay state.
     *
     * <p> Valid values depend on which directions of the relay are controlled
     * by the object.
     *
     * <p> When set to kBothDirections, the relay can be set to any of the four
     * states: 0v-0v, 12v-0v, 0v-12v, 12v-12v.
     *
     * <p> When set to forwards only or reverse only, you can specify the
     * constant for the direction or you can simply specify off and on. Using
     * only off and on is recommended.
     *
     * @param value the state to set the relay
     */
    public void set(Value value) {
        spikeRelay.set(value);
    }

    /**
     * Turns the output on or off. This uses the last set direction, and turns
     * the spike on or off in that direction. Is virtually identical to calling
     * {@code set(SpikeRelay.ON)} or {@code set(SpikeRelay.OFF)}.
     *
     * @param on whether spike should be on
     */
    public void set(boolean on) {
        set(on ? ON : OFF);
    }

    /**
     * Set the Relay Direction.
     *
     * <p> Changes which values the relay can be set to depending on which
     * direction is used.
     *
     * @param direction the direction for the relay to operate in
     */
    public void setDirection(Direction direction) {
        spikeRelay.setDirection(direction);
    }

    /**
     * Returns the current state of the relay.
     *
     * When set to forwards only or reverse only, value is returned as on/off
     * not forward/reverse (per the recommendation in {@code set}).
     *
     * @return the current state of the relay as a {@link Value}
     */
    public Value get() {
        return spikeRelay.get();
    }
}
