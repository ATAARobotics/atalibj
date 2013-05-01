package edu.first.module.sensor;

import edu.first.identifiers.ReturnableBoolean;

/**
 * Interface to represent switches that can be off or on. There are no real
 * expectations of this class because its use should be self-evident.
 *
 * @author Team 4334
 */
public interface DigitalLimitSwitch extends ReturnableBoolean {

    /**
     * Returns whether or not the limit switch is currently pressed.
     *
     * @return if switch is pushed
     */
    boolean isPushed();
}
