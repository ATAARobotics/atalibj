package edu.first.module.actuators;

import edu.first.identifiers.Position;
import edu.first.identifiers.Switch;

/**
 * General interface that signifies that the class controls a solenoid.
 * Solenoids should control pneumatic parts with the solenoid breakout.
 *
 * @since June 01 13
 * @author Joel Gallant
 */
public interface Solenoid extends Switch, Position {

    /**
     * Sets the position of the solenoid. Sets to either "on" or "off".
     *
     * @param pos the new position of the solenoid
     */
    @Override
    public void setPosition(boolean pos);

    /**
     * Returns the position of the solenoid. {@code true} is "on" and
     * {@code false} is "off".
     *
     * @return current position of solenoid
     */
    @Override
    public boolean getPosition();
}
