package edu.first.module.controllers;

import edu.first.identifiers.Position;
import edu.first.module.actuators.SpikeRelay;

/**
 * Controller that controls a compressor. Automatically changes the state of the
 * compressor according to the reading of a switch.
 *
 * @since June 06 13
 * @author Joel Gallant
 */
public class Compressor extends Controller implements SpikeRelay {

    private static final double defaultLoopTime = 0.4;
    private final SpikeRelay relay;
    private final Position pressureSwitch;
    private final Direction on;
    private final boolean switchStateAtPressure;

    /**
     * Constructs the compressor using its components and settings.
     *
     * @param relay spike relay that turns the compressor on and off
     * @param pressureSwitch digital switch that checks state of pressure
     * @param on state of the relay that turns the compressor on
     * @param switchStateAtPressure which state the switch is on when it is at
     * full pressure
     * @param loopTime looping time to update the controller
     */
    public Compressor(SpikeRelay relay, Position pressureSwitch, Direction on, boolean switchStateAtPressure, double loopTime) {
        super(loopTime, LoopType.FIXED_DELAY);
        this.relay = relay;
        this.pressureSwitch = pressureSwitch;
        this.on = on;
        this.switchStateAtPressure = switchStateAtPressure;
    }

    /**
     * Constructs the compressor using its components and settings. This assumes
     * a loop time of 0.4.
     *
     * @param relay spike relay that turns the compressor on and off
     * @param pressureSwitch digital switch that checks state of pressure
     * @param on state of the relay that turns the compressor on
     * @param switchStateAtPressure which state the switch is on when it is at
     * full pressure
     */
    public Compressor(SpikeRelay relay, Position pressureSwitch, Direction on, boolean switchStateAtPressure) {
        this(relay, pressureSwitch, on, switchStateAtPressure, defaultLoopTime);
    }

    /**
     * Constructs the compressor using its components and settings. This assumes
     * a loop time of 0.4 and that the switch will return false when at full
     * pressure.
     *
     * @param relay spike relay that turns the compressor on and off
     * @param pressureSwitch digital switch that checks state of pressure
     * @param on state of the relay that turns the compressor on
     */
    public Compressor(SpikeRelay relay, Position pressureSwitch, Direction on) {
        this(relay, pressureSwitch, on, false);
    }

    /**
     * Constructs the compressor using its components and settings. This assumes
     * a loop time of 0.4, that the switch will return false when at full
     * pressure and that the "on" state of the compressor is {@code FORWARDS}.
     *
     * @param relay spike relay that turns the compressor on and off
     * @param pressureSwitch digital switch that checks state of pressure
     */
    public Compressor(SpikeRelay relay, Position pressureSwitch) {
        this(relay, pressureSwitch, Direction.FORWARDS);
    }

    /**
     * Depending on the state of the switch, turns the relay on or off.
     */
    public void run() {
        if (pressureSwitch.getPosition() == switchStateAtPressure) {
            // At full pressure, turn off
            relay.set(Direction.OFF);
        } else {
            relay.set(on);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void set(Direction d) {
        relay.set(d);
    }

    /**
     * {@inheritDoc}
     */
    public Direction getDirection() {
        return relay.getDirection();
    }
}
