package edu.first.module.sensor;

/**
 * The class for interacting with hall effect.
 * 
 * @author Team 4334
 */
public interface HallEffect {

    /**
     * Returns the current timer value.
     *
     * @return the current counter value
     */
    int getCount();

    /**
     * Returns the counter rate in rotations per minute.
     *
     * @return the current counter rate
     */
    double getRate();

    /**
     * Returns boolean polarity of the Hall Effect sensor.
     *
     * @return the Hall Effect sensor polarity
     */
    boolean isPolarized();
}