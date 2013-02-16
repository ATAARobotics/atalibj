package edu.first.module.actuator;

/**
 * Interface representing solenoids. Is capable of setting the solenoid on or
 * off, and seeing what it is currently set to.
 *
 * @author Team 4334
 */
public interface Solenoid {

    /**
     * Sets the solenoid to a state. True typically means to let air through,
     * but is not always.
     *
     * @param on whether to send signal to solenoid
     */
    void set(boolean on);

    /**
     * Returns whether or not the solenoid is being turned on.
     *
     * @return the current state of the solenoid
     */
    boolean get();
}
