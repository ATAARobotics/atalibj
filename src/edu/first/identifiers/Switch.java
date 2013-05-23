package edu.first.identifiers;

/**
 * A switch is a manipulatable state with 2 possible values: on and off. A
 * position can be virtually anything that can be set to a boolean in some
 * sense, but should make sense in the context of a "position". It should be
 * capable of being in either position at any time.
 *
 * @since May 22 13
 * @author Joel Gallant
 */
public interface Switch {

    /**
     * Changes the position to the designated value. In general, there should be
     * no delay between the time this method is called, and when the value is
     * actually set.
     *
     * @param pos new position to set the switch to
     * @throws IllegalStateException when value cannot be set
     */
    public void setPosition(boolean pos);
}
