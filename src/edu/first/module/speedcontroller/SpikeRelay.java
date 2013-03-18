package edu.first.module.speedcontroller;

import edu.wpi.first.wpilibj.Relay;

/**
 * Interface meant to represent spike relay voltage controllers. To control the
 * relay, static values are placed in this class to represent which way the
 * relay is set and if it is on or off.
 *
 * @author Joel Gallant
 */
public interface SpikeRelay {

    /**
     *
     */
    Relay.Value ON = Relay.Value.kOn;
    /**
     *
     */
    Relay.Value OFF = Relay.Value.kOff;
    /**
     *
     */
    Relay.Value FORWARD = Relay.Value.kForward;
    /**
     *
     */
    Relay.Value BACKWARD = Relay.Value.kReverse;
    /**
     *
     */
    Relay.Direction BOTH = Relay.Direction.kBoth;
    /**
     *
     */
    Relay.Direction FORWARDS_ONLY = Relay.Direction.kForward;
    /**
     *
     */
    Relay.Direction BACKWARDS_ONLY = Relay.Direction.kReverse;

    /**
     * Sets the orientation and state of the spike relay. Use static members of
     * this interface to specify what to set the relay to.
     *
     * @param value how to set the relay
     */
    public void set(Relay.Value value);

    /**
     * Sets the direction of the relay. Use static members of
     * this interface to specify what to set the relay to.
     *
     * @param direction which way the spire relay can go
     */
    public void setDirection(Relay.Direction direction);

    /**
     * Returns the orientation or state of the spike relay.
     *
     * @return what relay is set to
     */
    public Relay.Value get();
}
